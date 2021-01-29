package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.mapper.EmployeeInfoMapper;
import jp.co.lyc.cms.mapper.EnterPeriodSearchMapper;
import jp.co.lyc.cms.mapper.ExpensesInfoMapper;
import jp.co.lyc.cms.mapper.SiteInfoMapper;
import jp.co.lyc.cms.mapper.WagesInfoMapper;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.EnterPeriodSearchModel;
import jp.co.lyc.cms.model.ExpensesInfoModel;
import jp.co.lyc.cms.model.SiteModel;
import jp.co.lyc.cms.model.WagesInfoModel;
import jp.co.lyc.cms.service.EmployeeInfoService;
import jp.co.lyc.cms.service.WagesInfoService;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.validation.WagesInfoValidation;

@Controller
@RequestMapping(value = "/wagesInfo")
public class WagesInfoController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	String errorsMessage = "";

	@Autowired
	WagesInfoService wagesInfoService;

	@Autowired
	WagesInfoMapper wagesInfoMapper;

	@Autowired
	ExpensesInfoMapper expensesInfoMapper;

	@Autowired
	EmployeeInfoService employeeInfoService;

	@Autowired
	EmployeeInfoController employeeInfoController;

	@Autowired
	EnterPeriodSearchMapper enterPeriodSearchMapper;

	@Autowired
	SiteInfoMapper siteInfoMapper;
	/**
	 * 
	 * @return
	 * @throws CloneNotSupportedException
	 */
	@RequestMapping(value = "/getWagesInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWagesInfo(@RequestBody WagesInfoModel wagesInfoMod)
			throws CloneNotSupportedException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (wagesInfoMod.getEmployeeNo() == null) {
			result.put("errorsMessage", "");
			return result;
		}
		if (!wagesInfoMod.getEmployeeNo().substring(0, 3).equals("LYC")) {
			result.put("errorsMessage", "本社社員（LYCXXX）を選択してください！");
			return result;
		}
		boolean kadouCheck = true;// true稼动，false非稼动
		boolean jigyosyaFlag = true;// true事业主，false其他
		boolean jijutsuFlag = true;// true技术者，false非技术者
		boolean hatsunyubaFlag = true;//true初入場または非技術者、false技術者かつ入場したことある
		ArrayList<SiteModel> kadouList = wagesInfoMapper.kadouCheck(wagesInfoMod.getEmployeeNo());
		if (kadouList.size() != 0) {
			kadouCheck = false;
			boolean leaderCheck = false;
			if (kadouList.get(0) != null) {
				if (kadouList.get(0).getSiteRoleCode().equals("0") || kadouList.get(0).getSiteRoleCode().equals("1")) {
					leaderCheck = true;
				}
			}
			result.put("kadouList", kadouList);
			result.put("leaderCheck", leaderCheck);
		}
		EmployeeModel b = new EmployeeModel();
		b.setEmployeeNo(wagesInfoMod.getEmployeeNo());
		b = employeeInfoService.getEmployeeByEmployeeNo(employeeInfoController.getParam(b));
		String nyusyaDate = "";// 入社日
		if (!b.getOccupationCode().equals("3")) {
			// 職種判断
			kadouCheck = false;
			jijutsuFlag = false;
			nyusyaDate = b.getIntoCompanyYearAndMonth();
		}else {
			List<SiteModel> siteMod = new ArrayList<SiteModel>();
			siteMod = siteInfoMapper.getSiteInfo(wagesInfoMod.getEmployeeNo());
			if(siteMod.size() != 0) {
				hatsunyubaFlag = false;
			}
		}
		result.put("hatsunyubaFlag", hatsunyubaFlag);
		result.put("kadouCheck", kadouCheck);
		HashMap<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("employeeNo", wagesInfoMod.getEmployeeNo());
		ArrayList<WagesInfoModel> wagesInfoList = wagesInfoMapper.getWagesInfo(sendMap);
		ArrayList<ExpensesInfoModel> expensesInfoList = expensesInfoMapper.getExpensesInfo(sendMap);
		ArrayList<ExpensesInfoModel> allExpensesInfoList = new ArrayList<ExpensesInfoModel>(); 
		WagesInfoModel a = wagesInfoMapper.getEmployeeForm(wagesInfoMod.getEmployeeNo());
		if (!a.getEmployeeFormCode().equals("2")) {
			// 個人事業主判断
			jigyosyaFlag = false;
		}
		result.put("employeeFormCode", a.getEmployeeFormCode());
		if (wagesInfoList.size() == 0) {
			// 追加の場合（データがない）、T002に社員形式を取得
			result.put("errorsMessage", "該当社員の給料データがない");
		} else if (expensesInfoList.size() != 0) {
			wagesInfoList = dataReset(wagesInfoList, expensesInfoList);
			allExpensesInfoList = resetExpensesInfo(expensesInfoList);
		} else if (expensesInfoList.size() == 0) {
			for (int i = 0; i < wagesInfoList.size(); i++) {
				// 給料期間
				if (i != wagesInfoList.size() - 1) {
					String reflectYearAndMonth = wagesInfoList.get(i + 1).getReflectYearAndMonth();
					int year = Integer.parseInt(reflectYearAndMonth.substring(0,4));
					int month = Integer.parseInt(reflectYearAndMonth.substring(4));
					String yearAndMonth = "";
					month -= 1;
					if(month == 0) {
						yearAndMonth = (year - 1) + "12";
					}else {
						yearAndMonth = year + "" + (month > 9 ? month : "0" + month );
					}
					wagesInfoList.get(i).setPeriod(wagesInfoList.get(i).getReflectYearAndMonth() + "~"
							+ yearAndMonth);
				} else {
					wagesInfoList.get(i).setPeriod(wagesInfoList.get(i).getReflectYearAndMonth() + "~");
				}
			}
		}
		result.put("allExpensesInfoList", allExpensesInfoList);
		result.put("wagesInfoList", wagesInfoList);
		// 社員最初の入場期日を取得
		HashMap<String, String> sendHashMap = new HashMap<String, String>();
		sendHashMap.put("employeeNo", wagesInfoMod.getEmployeeNo());
		sendHashMap.put("wagesFlag", "wages");
		// 最初の現場
		ArrayList<EnterPeriodSearchModel> dateList = enterPeriodSearchMapper.selectAdmissionStartDate(sendHashMap);
		// ボーナスのデータ
		EnterPeriodSearchModel bonusSite = new EnterPeriodSearchModel();
		WagesInfoModel bonus = new WagesInfoModel();
		if (jijutsuFlag && dateList.size() != 0) {
			// 技術者かつ入場したことある
			bonusSite = dateList.get(0);
			if (!jigyosyaFlag) {
				if (wagesInfoList.size() != 0) {// 最初の給料ではない
					bonus = (WagesInfoModel) UtilsCheckMethod.copeObject(wagesInfoList.get(wagesInfoList.size() - 1));
					// 最初の入場年
					int yearAd = Integer.parseInt(bonusSite.getAdmissionStartDate().substring(0, 4));
					// 最初の入場月
					int monthAd = Integer.parseInt(bonusSite.getAdmissionStartDate().substring(4, 6));
					Calendar cal = Calendar.getInstance();
					// 今の年
					int yearPage = cal.get(Calendar.YEAR);
					// 今の月
					int monthPage = cal.get(Calendar.MONTH) + 1;
					// 待機開始年月
					// 待機月数
					int nonSiteMonths = Integer.parseInt(bonusSite.getNonSiteMonths());
					int year = yearPage - yearAd;
					int month = monthAd - monthPage;
					if (month == 0) {
						month = year * 12 - nonSiteMonths;
					} else {
						month = year * 12 + month - nonSiteMonths;
					}
					if (month != 0 && month % 12 == 0 && bonusSite.getEmployeeNo().substring(0, 3).equals("LYC")
							&& bonus != null) {
						String nextBonusMonth = bonus.getNextBonusMonth();
						if (!UtilsCheckMethod.isNullOrEmpty(nextBonusMonth)) {
							int yearBonus = Integer.parseInt(nextBonusMonth.substring(0, 4));
							yearBonus += 1;
							nextBonusMonth = Integer.toString(yearBonus) + nextBonusMonth.substring(4);
							bonus.setNextBonusMonth(nextBonusMonth);
							bonus.setLastTimeBonusAmount(bonus.getScheduleOfBonusAmount());
							bonus.setScheduleOfBonusAmount("");
						} else {
							bonus = null;
						}
					} else if (month != 0 && month % 12 != 0
							&& bonusSite.getEmployeeNo().substring(0, 3).equals("LYC")) {
						String nextBonusMonth = bonus.getNextBonusMonth();
						if (!UtilsCheckMethod.isNullOrEmpty(nextBonusMonth)) {
							ArrayList<String> kadouMonths = wagesInfoMapper
									.getLastKadouPeriod(wagesInfoMod.getEmployeeNo());
							int kadouMonth = 0;
							if (kadouMonths.size() != 0) {
								kadouMonth = Integer.parseInt(kadouMonths.get(kadouMonths.size() - 1));
							}
							int yearBonus = Integer.parseInt(nextBonusMonth.substring(0, 4));
							int monthBonus = Integer.parseInt(nextBonusMonth.substring(4));
							monthBonus += kadouMonth;
							if (monthBonus > 12) {
								monthBonus = monthBonus - 12;
								yearBonus += 1;
							}
							nextBonusMonth = Integer.toString(yearBonus)
									+ (monthBonus > 10 ? monthBonus : "0" + monthBonus);
							bonus.setNextBonusMonth(nextBonusMonth);
							bonus.setLastTimeBonusAmount("");
						} else {
							bonus = null;
						}
					}
				}
			} else {
				bonus = null;
			}

		} else if (jijutsuFlag && dateList.size() == 0) {// 技術者でも、入場したことない
			bonus = null;
		} else if (!jijutsuFlag && wagesInfoList.size() != 0) {// 技術者ではなく、最初の給料ではない
			bonus = (WagesInfoModel) UtilsCheckMethod.copeObject(wagesInfoList.get(wagesInfoList.size() - 1));
			// 最初の入場年
			int yearAd = Integer.parseInt(nyusyaDate.substring(0, 4));
			// 最初の入場月
			int monthAd = Integer.parseInt(nyusyaDate.substring(4, 6));
			Calendar cal = Calendar.getInstance();
			// 今の年
			int yearPage = cal.get(Calendar.YEAR);
			// 今の月
			int monthPage = cal.get(Calendar.MONTH) + 1;
			int year = yearPage - yearAd;
			int month = monthAd - monthPage;
			if (month == 0) {
				month = year * 12;
			} else {
				month = year * 12 + month;
			}
			if (month != 0 && month % 12 == 0) {
				String yearAndMonthString = bonus.getNextBonusMonth();
				if (!UtilsCheckMethod.isNullOrEmpty(yearAndMonthString)) {
					String yearString = Integer.toString(Integer.parseInt(yearAndMonthString.substring(0, 4)) + 1);
					yearAndMonthString = yearString + yearAndMonthString.substring(4);
					bonus.setNextBonusMonth(yearAndMonthString);
				} else {
					bonus = null;
				}
			}
		} else if (!jijutsuFlag && wagesInfoList.size() == 0) {// 技術者ではなく、最初の給料です
			bonus = null;
		}
		result.put("bonus", bonus);
		return result;
	}

	/**
	 * 全部の諸費用データ
	 * @param expensesInfoList
	 * @return
	 */
	private ArrayList<ExpensesInfoModel> resetExpensesInfo(ArrayList<ExpensesInfoModel> expensesInfoList) {
		// TODO Auto-generated method stub
		ArrayList<ExpensesInfoModel> resultList = new ArrayList<ExpensesInfoModel>();
		for (int j = 0; j < expensesInfoList.size(); j++) {
			ExpensesInfoModel e = expensesInfoList.get(j);
			// 諸費用期間
			if (j != expensesInfoList.size() - 1) {
				String reflectYearAndMonth = expensesInfoList.get(j + 1).getExpensesReflectYearAndMonth();
				int year = Integer.parseInt(reflectYearAndMonth.substring(0,4));
				int month = Integer.parseInt(reflectYearAndMonth.substring(4));
				String yearAndMonth = "";
				month -= 1;
				if(month == 0) {
					yearAndMonth = (year - 1) + "12";
				}else {
					yearAndMonth = year + "" + (month > 9 ? month : "0" + month );
				}
				e.setExpensesPeriod(e.getExpensesReflectYearAndMonth() + "~"
						+ yearAndMonth);
			} else {
				e.setExpensesPeriod(e.getExpensesReflectYearAndMonth() + "~");
			}
			resultList.add(e);
		}
		return resultList;
	}

	/**
	 * 検索したデータの再処理
	 * 
	 * @param wagesInfoModels
	 * @param expensesInfoModels
	 * @return
	 */
	@SuppressWarnings("null")
	public ArrayList<WagesInfoModel> dataReset(ArrayList<WagesInfoModel> wagesInfoModels,
			ArrayList<ExpensesInfoModel> expensesInfoModels) {
		ArrayList<WagesInfoModel> resuList = new ArrayList<WagesInfoModel>();
		for (int i = 0; i < wagesInfoModels.size(); i++) {
			WagesInfoModel w = wagesInfoModels.get(i);
			// 給料情報の反映年月
			int wagesDate = Integer.parseInt(w.getReflectYearAndMonth());
			// 前の給料情報の反映年月
			int nextWagesDate = 0;
			// 第一件ではない場合
			// 給料期間
			if (i != wagesInfoModels.size() - 1) {
				nextWagesDate = Integer.parseInt(wagesInfoModels.get(i + 1).getReflectYearAndMonth());
				String reflectYearAndMonth = wagesInfoModels.get(i + 1).getReflectYearAndMonth();
				int year = Integer.parseInt(reflectYearAndMonth.substring(0,4));
				int month = Integer.parseInt(reflectYearAndMonth.substring(4));
				String yearAndMonth = "";
				month -= 1;
				if(month == 0) {
					yearAndMonth = (year - 1) + "12";
				}else {
					yearAndMonth = year + "" + (month > 9 ? month : "0" + month );
				}
				w.setPeriod(w.getReflectYearAndMonth() + "~" + yearAndMonth);
			} else {
				w.setPeriod(w.getReflectYearAndMonth() + "~");
			}
			ArrayList<ExpensesInfoModel> expensesInfoModelsInWages = new ArrayList<ExpensesInfoModel>();
			for (int j = 0; j < expensesInfoModels.size(); j++) {
				ExpensesInfoModel e = expensesInfoModels.get(j);
				// 諸費用期間
				if (j != expensesInfoModels.size() - 1) {
					String reflectYearAndMonth = expensesInfoModels.get(j + 1).getExpensesReflectYearAndMonth();
					int year = Integer.parseInt(reflectYearAndMonth.substring(0,4));
					int month = Integer.parseInt(reflectYearAndMonth.substring(4));
					String yearAndMonth = "";
					month -= 1;
					if(month == 0) {
						yearAndMonth = (year - 1) + "12";
					}else {
						yearAndMonth = year + "" + (month > 9 ? month : "0" + month );
					}
					e.setExpensesPeriod(e.getExpensesReflectYearAndMonth() + "~"
							+ yearAndMonth);
				} else {
					e.setExpensesPeriod(e.getExpensesReflectYearAndMonth() + "~");
				}
				// 諸費用の反映年月
				int expensesDate = Integer.parseInt(expensesInfoModels.get(j).getExpensesReflectYearAndMonth());
				if (i == wagesInfoModels.size() - 1) {
					if (expensesDate >= wagesDate) {
						expensesInfoModelsInWages.add(e);
					}
				} else if (i != wagesInfoModels.size()) {
					if (expensesDate < nextWagesDate && expensesDate >= wagesDate) {
						expensesInfoModelsInWages.add(e);
					}
				}
			}
			// 写入给料明细的最新诸费用
			ExpensesInfoModel monthNewData = new ExpensesInfoModel();
			if (expensesInfoModelsInWages.size() == 0) {
				expensesInfoModelsInWages = null;
			} else {
				monthNewData = expensesInfoModelsInWages.get(expensesInfoModelsInWages.size() - 1);
				w.setTransportationExpenses(monthNewData.getTransportationExpenses());
				w.setLeaderAllowanceAmount(monthNewData.getLeaderAllowanceAmount());
				w.setHousingAllowance(monthNewData.getHousingAllowance());
				w.setOtherAllowanceName(monthNewData.getOtherAllowanceName());
				w.setOtherAllowanceAmount(monthNewData.getOtherAllowanceAmount());
			}
			w.setExpensesInfoModels(expensesInfoModelsInWages);
			resuList.add(w);
		}
		return resuList;
	}

	/**
	 * 登録ボタン
	 * 
	 * @param wagesInfoModel
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toroku(@RequestBody WagesInfoModel wagesInfoModel) {
		logger.info("WagesInfoController.onloadPage:" + "登録開始");
		Map<String, Object> result = new HashMap<String, Object>();
		errorsMessage = "";
		DataBinder binder = new DataBinder(wagesInfoModel);
		binder.setValidator(new WagesInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			logger.info("WagesInfoController.onloadPage:" + "登録終了");
			return result;
		}
		HashMap<String, String> checkMap = new HashMap<String, String>();
		checkMap.put("employeeNo", wagesInfoModel.getEmployeeNo());
		ArrayList<WagesInfoModel> wagesInfoList = wagesInfoMapper.getWagesInfo(checkMap);
		if(wagesInfoList.size() != 0) {
			int lastEnd = Integer.parseInt(wagesInfoList.get(wagesInfoList.size() - 1).getReflectYearAndMonth());
			int nowStart = Integer.parseInt(wagesInfoModel.getReflectYearAndMonth());
			if (nowStart < lastEnd) {
				errorsMessage += "新規給料情報の反映年月は既存データ以降にしてください";
				result.put("errorsMessage", errorsMessage);// エラーメッセージ
				return result;
			}
		}
		// 非稼働の場合、保険は前件の保険を使う
		EmployeeModel b = new EmployeeModel();
		b.setEmployeeNo(wagesInfoModel.getEmployeeNo());
		b = employeeInfoService.getEmployeeByEmployeeNo(employeeInfoController.getParam(b));
		ArrayList<SiteModel> relatedEmployees = wagesInfoMapper.kadouCheck(wagesInfoModel.getEmployeeNo());
		if (relatedEmployees.size() == 0 && b.getOccupationCode().equals("3")) {
			ArrayList<WagesInfoModel> hokenList = wagesInfoMapper.hokenSearch(wagesInfoModel.getEmployeeNo());
			if (hokenList.size() != 0) {
				wagesInfoModel.setWelfarePensionAmount(hokenList.get(hokenList.size() - 1).getWelfarePensionAmount());
				wagesInfoModel.setHealthInsuranceAmount(hokenList.get(hokenList.size() - 1).getHealthInsuranceAmount());
				wagesInfoModel.setInsuranceFeeAmount(hokenList.get(hokenList.size() - 1).getInsuranceFeeAmount());
			}
		}
		wagesInfoModel.setUpdateUser((String) getSession().getAttribute("employeeName"));
		if (wagesInfoModel.getActionType().equals("insert")) {// 插入场合
			HashMap<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("employeeNo", wagesInfoModel.getEmployeeNo());
			sendMap.put("reflectYearAndMonth", wagesInfoModel.getReflectYearAndMonth());
			ArrayList<WagesInfoModel> checkMod = wagesInfoMapper.getWagesInfo(sendMap);
			if (checkMod.size() > 0) {
				result.put("errorsMessage", "データが存在しているため、追加はできません！");
				return result;
			}
			boolean resultBool = wagesInfoService.insert(wagesInfoModel);
			if (resultBool) {
				result.put("message", "登録成功");
			} else {
				result.put("errorsMessage", "登録失败");
			}
		} else if (wagesInfoModel.getActionType().equals("update")) {// 更新场合
			HashMap<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("employeeNo", wagesInfoModel.getEmployeeNo());
			// 更新しておく反映年月の取得
			ArrayList<WagesInfoModel> lastList = wagesInfoMapper.getWagesInfo(sendMap);
			if (!lastList.get(lastList.size() - 1).getReflectYearAndMonth()
					.equals(wagesInfoModel.getReflectYearAndMonth())) {
				sendMap.put("reflectYearAndMonth", wagesInfoModel.getReflectYearAndMonth());
				ArrayList<WagesInfoModel> checkMod = wagesInfoMapper.getWagesInfo(sendMap);
				if (checkMod.size() > 0) {
					result.put("errorsMessage", "更新した反映年月はデータベースにあるため、反映年月を確認してください！");
					return result;
				}
			}
			wagesInfoModel.setUpdatedReflectYearAndMonth(lastList.get(lastList.size() - 1).getReflectYearAndMonth());
			boolean resultBool = wagesInfoService.update(wagesInfoModel);
			if (resultBool) {
				result.put("message", "更新成功");
			} else {
				result.put("errorsMessage", "更新失败");
			}
		}
		logger.info("WagesInfoController.onloadPage:" + "登録終了");
		return result;
	}

	/**
	 * 給料情報削除
	 * @param deleteMod
	 * @return
	 * @throws CloneNotSupportedException
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody WagesInfoModel deleteMod) throws CloneNotSupportedException {
		logger.info("WagesInfoController.onloadPage:" + "登録開始");
		Map<String, Object> result = new HashMap<String, Object>();
		errorsMessage = "";
		DataBinder binder = new DataBinder(deleteMod);
		binder.setValidator(new WagesInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			logger.info("WagesInfoController.onloadPage:" + "登録終了");
			return result;
		}
		boolean deleteResult = wagesInfoService.delete(deleteMod);
		if (!deleteResult) {
			result.put("errorsMessage", "削除失敗");
		}
		return result;
	}
}

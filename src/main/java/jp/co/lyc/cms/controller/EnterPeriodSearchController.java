package jp.co.lyc.cms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import jp.co.lyc.cms.mapper.EnterPeriodSearchMapper;
import jp.co.lyc.cms.model.EnterPeriodSearchModel;
import jp.co.lyc.cms.model.SiteModel;
import jp.co.lyc.cms.service.EnterPeriodSearchService;
import jp.co.lyc.cms.validation.EnterPeriodSearchValidation;

@Controller
@RequestMapping(value = "/enterPeriodSearch")
public class EnterPeriodSearchController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EnterPeriodSearchService enterPeriodSearchService;
	@Autowired
	EnterPeriodSearchMapper enterPeriodSearchMapper;

	String errorsMessage = "";

	/**
	 * 検索ボタン
	 * 
	 * @param enterPeriodSearchModel
	 * @return
	 */
	@RequestMapping(value = "/selectEnterPeriodData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectEnterPeriodData(@RequestBody EnterPeriodSearchModel enterPeriodSearchModel) {
		errorsMessage = "";
		logger.info("EnterPeriodSearchController.selectEnterPeriodData:" + "検索開始");
		DataBinder binder = new DataBinder(enterPeriodSearchModel);
		binder.setValidator(new EnterPeriodSearchValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> result = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			logger.info("EnterPeriodSearchController.selectEnterPeriodData:" + "検索終了");
			return result;
		}
		HashMap<String, String> sendMap = enterPeriodSearchService.getSendMap(enterPeriodSearchModel);
		ArrayList<EnterPeriodSearchModel> resultList = new ArrayList<EnterPeriodSearchModel>();
		if (enterPeriodSearchModel.getEnterPeriodKbn().equals("0")) {
			// 区分は入社の場合
			resultList = enterPeriodSearchService.selectEnterPeriodDataForIntoCompany(sendMap);
		} else if (enterPeriodSearchModel.getEnterPeriodKbn().equals("1")) {
			// 区分は入場の場合
			resultList = enterPeriodSearchService.selectEnterPeriodDataForIntoSite(sendMap);
		} else if (enterPeriodSearchModel.getEnterPeriodKbn().equals("2")) {
			// 区分はボーナスの場合
			resultList = enterPeriodSearchService.selectScheduleOfBonusAmount(sendMap);
		}

		if (resultList == null || resultList.size() == 0) {
			errorsMessage += "今月データがないです";// エラーメッセージ
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
		} else {
			for (int i = 0; i < resultList.size(); i++) {
				if (resultList.get(i).getEmployeeNo() != null
						&& (resultList.get(i).getEmployeeNo().substring(0, 2).equals("BP")
								|| resultList.get(i).getEmployeeNo().substring(0, 2).equals("SP")
								|| resultList.get(i).getEmployeeNo().substring(0, 2).equals("SC"))) {
					resultList.get(i).setEmployeeName(resultList.get(i).getEmployeeName() + "("
							+ resultList.get(i).getEmployeeNo().substring(0, 2) + ")");
				}
			}
			result.put("enterPeriodList", resultList);
		}
		logger.info("EnterPeriodSearchController.selectEnterPeriodData:" + "検索終了");
		return result;
	}

	/**
	 * 検索ボタン
	 * 
	 * @param enterPeriodSearchModel
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/selectEnterPeriodDataNew", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectEnterPeriodDataNew(@RequestBody EnterPeriodSearchModel enterPeriodSearchModel)
			throws ParseException {
		errorsMessage = "";
		logger.info("EnterPeriodSearchController.selectEnterPeriodData:" + "検索開始");
		DataBinder binder = new DataBinder(enterPeriodSearchModel);
		binder.setValidator(new EnterPeriodSearchValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> result = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			logger.info("EnterPeriodSearchController.selectEnterPeriodData:" + "検索終了");
			return result;
		}
		ArrayList<String> employeeList = new ArrayList<String>();
		ArrayList<EnterPeriodSearchModel> resultList = new ArrayList<EnterPeriodSearchModel>();
		ArrayList<SiteModel> siteInfoList = new ArrayList<SiteModel>();

		String selectedYearAndMonth = enterPeriodSearchModel.getYearAndMonth();
		String yearAndMonth = Integer.parseInt(enterPeriodSearchModel.getYearAndMonth().substring(0, 4)) - 1 + String
				.format("%02d", (Integer.parseInt(enterPeriodSearchModel.getYearAndMonth().substring(4, 6)) + 1));
		if (Integer.parseInt(yearAndMonth.substring(4, 6)) > 12) {
			yearAndMonth = (Integer.parseInt(yearAndMonth.substring(0, 4)) + 1) + "01";
		}
		if (enterPeriodSearchModel.getEnterPeriodKbn().equals("0")) {
			// 区分は昇格の場合
			employeeList = enterPeriodSearchService.getWagesInfo(yearAndMonth, selectedYearAndMonth);
		} else if (enterPeriodSearchModel.getEnterPeriodKbn().equals("1")) {
			// 区分は現場の場合
			employeeList = enterPeriodSearchService.getEmployeeSiteInfo(yearAndMonth);
		} else if (enterPeriodSearchModel.getEnterPeriodKbn().equals("2")) {
			// 区分はボーナスの場合
			employeeList = enterPeriodSearchService.getBonusMonthInfo(yearAndMonth);
		}

		if (enterPeriodSearchModel.getEmployeeNo() != null) {
			for (int i = 0; i < employeeList.size(); i++) {
				if (!enterPeriodSearchModel.getEmployeeNo().equals(employeeList.get(i))) {
					employeeList.remove(i);
					i--;
				}
			}
		}

		if (employeeList.size() > 0) {
			resultList = enterPeriodSearchService.getenterPeriodByEmp(employeeList);
		}

		if (resultList.size() > 0) {
			// 非稼働取得
			siteInfoList = enterPeriodSearchService.getSiteInfoByEmp(employeeList, yearAndMonth);
			ArrayList<EnterPeriodSearchModel> periodsList = new ArrayList<EnterPeriodSearchModel>();

			for (int i = 0; i < siteInfoList.size(); i++) {
				if (i == 0) {
					String startTime = siteInfoList.get(i).getAdmissionStartDate();
					String endTime = yearAndMonth + "01";
					int month = getMonthNum(endTime.substring(0, 6), startTime.substring(0, 6));
					if (month >= 2) {
						EnterPeriodSearchModel pl = new EnterPeriodSearchModel();
						pl.setEmployeeNo(siteInfoList.get(i).getEmployeeNo());
						pl.setNonSiteMonths(String.valueOf(month));
						pl.setNonSitePeriod(endTime + "~" + startTime);
						periodsList.add(pl);
					}

					if (periodsList.size() > 0) {
						month = 0;
						for (int j = 0; j < periodsList.size(); j++) {
							month += Integer.parseInt(periodsList.get(j).getNonSiteMonths());
						}
						for (int j = 0; j < resultList.size(); j++) {
							if (resultList.get(j).getEmployeeNo().equals(periodsList.get(0).getEmployeeNo())) {
								resultList.get(j).setNonSitePeriodsList(periodsList);
								resultList.get(j).setNonSiteMonths(String.valueOf(month));
								periodsList = new ArrayList<EnterPeriodSearchModel>();
								break;
							}
						}
					}
				} else {
					if (siteInfoList.get(i).getEmployeeNo().equals(siteInfoList.get(i - 1).getEmployeeNo())) {
						String endTime = siteInfoList.get(i - 1).getAdmissionEndDate();
						String startTime = siteInfoList.get(i).getAdmissionStartDate();
						if (endTime != null) {
							int month = getMonthNum(endTime.substring(0, 6), startTime.substring(0, 6));
							if (month >= 2) {
								EnterPeriodSearchModel pl = new EnterPeriodSearchModel();
								pl.setEmployeeNo(siteInfoList.get(i).getEmployeeNo());
								pl.setNonSiteMonths(String.valueOf(month));
								pl.setNonSitePeriod(endTime + "~" + startTime);
								periodsList.add(pl);
							}
						}

						if (periodsList.size() > 0) {
							int month = 0;
							for (int j = 0; j < periodsList.size(); j++) {
								month += Integer.parseInt(periodsList.get(j).getNonSiteMonths());
							}
							for (int j = 0; j < resultList.size(); j++) {
								if (resultList.get(j).getEmployeeNo().equals(periodsList.get(0).getEmployeeNo())) {
									ArrayList<EnterPeriodSearchModel> nonSitePeriodsList = resultList.get(j)
											.getNonSitePeriodsList();
									if (nonSitePeriodsList != null) {
										for (int z = 0; z < periodsList.size(); z++) {
											nonSitePeriodsList.add(periodsList.get(z));
										}
									} else {
										nonSitePeriodsList = periodsList;
									}
									resultList.get(j).setNonSitePeriodsList(nonSitePeriodsList);
									resultList.get(j).setNonSiteMonths(String.valueOf(month));
									periodsList = new ArrayList<EnterPeriodSearchModel>();
									break;
								}
							}
						}
					} else {
						String startTime = siteInfoList.get(i).getAdmissionStartDate();
						String endTime = yearAndMonth + "01";
						int month = getMonthNum(endTime.substring(0, 6), startTime.substring(0, 6));
						if (month >= 2) {
							EnterPeriodSearchModel pl = new EnterPeriodSearchModel();
							pl.setEmployeeNo(siteInfoList.get(i).getEmployeeNo());
							pl.setNonSiteMonths(String.valueOf(month));
							pl.setNonSitePeriod(endTime + "~" + startTime);
							periodsList.add(pl);
						}

						if (periodsList.size() > 0) {
							month = 0;
							for (int j = 0; j < periodsList.size(); j++) {
								month += Integer.parseInt(periodsList.get(j).getNonSiteMonths());
							}
							for (int j = 0; j < resultList.size(); j++) {
								if (resultList.get(j).getEmployeeNo().equals(periodsList.get(0).getEmployeeNo())) {
									ArrayList<EnterPeriodSearchModel> nonSitePeriodsList = resultList.get(j)
											.getNonSitePeriodsList();
									if (nonSitePeriodsList != null) {
										for (int z = 0; z < periodsList.size(); z++) {
											nonSitePeriodsList.add(periodsList.get(z));
										}
									} else {
										nonSitePeriodsList = periodsList;
									}
									resultList.get(j).setNonSitePeriodsList(periodsList);
									resultList.get(j).setNonSiteMonths(String.valueOf(month));
									periodsList = new ArrayList<EnterPeriodSearchModel>();
									break;
								}
							}
						}
					}
				}
			}

			for (int i = 0; i < resultList.size(); i++) {
				for (int j = 0; j < siteInfoList.size(); j++) {
					if (resultList.get(i).getEmployeeNo().equals(siteInfoList.get(j).getEmployeeNo())) {
						if (siteInfoList.get(j).getTypteOfContractCode() != null
								&& siteInfoList.get(j).getTypteOfContractCode().equals("4")) {
							String startTime = Integer.parseInt(siteInfoList.get(j).getAdmissionStartDate()) < Integer
									.parseInt(yearAndMonth + "01") ? (yearAndMonth + "01")
											: siteInfoList.get(j).getAdmissionStartDate();
							String endTime = siteInfoList.get(j).getAdmissionEndDate() == null
									? (selectedYearAndMonth + "01")
									: siteInfoList.get(j).getAdmissionEndDate();
							int month = getMonthNum(startTime.substring(0, 6), endTime.substring(0, 6)) + 1;
							periodsList = resultList.get(i).getNonSitePeriodsList();
							if (periodsList == null)
								periodsList = new ArrayList<EnterPeriodSearchModel>();
							EnterPeriodSearchModel pl = new EnterPeriodSearchModel();
							pl.setEmployeeNo(siteInfoList.get(i).getEmployeeNo());
							pl.setNonSiteMonths(String.valueOf(month));
							pl.setNonSitePeriod(startTime + "~" + endTime);
							periodsList.add(pl);
							resultList.get(i).setNonSitePeriodsList(periodsList);
							resultList.get(i).setNonSiteMonths(
									Integer.parseInt(resultList.get(i).getNonSiteMonths() == null ? "0"
											: resultList.get(i).getNonSiteMonths()) + month + "");
						}
					}
				}
			}

			for (int i = 0; i < resultList.size(); i++) {
				ArrayList<EnterPeriodSearchModel> tempList = resultList.get(i).getNonSitePeriodsList();
				if (tempList == null) {
					resultList.get(i).setNonSiteMonths(null);
				} else {
					int month = 0;
					for (int j = 0; j < tempList.size(); j++) {
						month += Integer.parseInt(tempList.get(j).getNonSiteMonths());
					}
					resultList.get(i).setNonSiteMonths(String.valueOf(month));
				}
			}

			// 非稼働時間計算
			for (int i = 0; i < resultList.size(); i++) {
				if (resultList.get(i).getNonSiteMonths() != null) {
					int month = getMonthNum(resultList.get(i).getReflectYearAndMonth(), yearAndMonth);
					if (Integer.parseInt(resultList.get(i).getNonSiteMonths()) > month) {
						resultList.remove(i);
						i--;
					} else if (Integer.parseInt(resultList.get(i).getNonSiteMonths()) < month) {
						resultList.get(i).setIsRed("true");
					}
				} else {
					int month = 0;
					if (enterPeriodSearchModel.getEnterPeriodKbn().equals("0")) {
						month = getMonthNum(resultList.get(i).getReflectYearAndMonth(), yearAndMonth);
					} else if (enterPeriodSearchModel.getEnterPeriodKbn().equals("1")) {
						month = getMonthNum(resultList.get(i).getAdmissionStartDate().substring(0, 6), yearAndMonth);
					}
					if (month > 0) {
						resultList.get(i).setIsRed("true");
					}
				}
			}

			for (int i = 0; i < resultList.size(); i++) {
				resultList.get(i).setRowNo(i + 1 + "");
			}
		} else {
			errorsMessage += "今月データがないです";// エラーメッセージ
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
		}

		result.put("enterPeriodList", resultList);
		logger.info("EnterPeriodSearchController.selectEnterPeriodData:" + "検索終了");
		return result;
	}

	public static int getMonthNum(String date1, String date2) throws java.text.ParseException {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));

		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		int month = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
		return result == 0 ? month : (month + result);
	}
}

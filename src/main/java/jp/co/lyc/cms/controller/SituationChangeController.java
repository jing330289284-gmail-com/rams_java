package jp.co.lyc.cms.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

import jp.co.lyc.cms.model.SituationChangesModel;
import jp.co.lyc.cms.service.SituationChangesService;
import jp.co.lyc.cms.validation.SituationChangesValidation;

@Controller
@RequestMapping(value = "/SituationChange")
public class SituationChangeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	String errorsMessage = "";
	@Autowired
	SituationChangesService SituationChangesService;

	@RequestMapping(value = "/searchSituationChange", method = RequestMethod.POST)

	@ResponseBody
	public Map<String, Object> searchSituationChanges(@RequestBody SituationChangesModel situationInfo) {

		errorsMessage = "";
		DataBinder binder = new DataBinder(situationInfo);
		binder.setValidator(new SituationChangesValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> resulterr = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});

			resulterr.put("errorsMessage", errorsMessage);// エラーメッセージ
			return resulterr;
		} else {

			String SocialInsurance = "";
			String ScheduleOfBonusAmount = "";
			String employeeFormNameChange = "";
			List<SituationChangesModel> situationChangeList = new ArrayList<SituationChangesModel>();
			List<SituationChangesModel> situationChangeListFront = new ArrayList<SituationChangesModel>();
			Map<String, Object> sendMap = getDetailParam(situationInfo);

			// QU FEN : RU ZHI TUI ZHI 检索
			if (situationInfo.getClassification().equals("3") || situationInfo.getClassification().equals("4")) {
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "入退社検索開始");
				situationChangeList = SituationChangesService.searchSituationIntoORretirement(sendMap);
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "入退社検索結束");
				if (situationChangeList.size() == 0) {
					String noData = "";
					noData = "条件に該当する結果が存在しない";
					resulterr.put("noData", noData);
					return resulterr;
				}
				for (int i = 0; i < situationChangeList.size(); i++) {
					if (situationChangeList.get(i).getSocialInsuranceFlag() != null
							&& situationChangeList.get(i).getSocialInsuranceFlag().equals("1")) {
						SocialInsurance = "追加";
					} else {
						SocialInsurance = "なし";
					}
					situationChangeList.get(i).setSocialInsuranceFlag(SocialInsurance);
					situationChangeList.get(i).setSalaryFlag("0");
					situationChangeList.get(i).setBonusFlag("0");
					if (situationInfo.getClassification().equals("3")) {
						situationChangeList.get(i).setStatus("入職");
					}
					if (situationInfo.getClassification().equals("4")) {
						situationChangeList.get(i).setStatus("退職");
					}
					if (situationChangeList.get(i).getSalary() != null) {
						String moneyF = moneyFormat(Integer.parseInt(situationChangeList.get(i).getSalary()));
						situationChangeList.get(i).setSalary(moneyF);
					}
					if (situationChangeList.get(i).getScheduleOfBonusAmount() != null) {
						String moneyF = moneyFormat(
								Integer.parseInt(situationChangeList.get(i).getScheduleOfBonusAmount()));
						situationChangeList.get(i).setScheduleOfBonusAmount(moneyF);
					}

				}
				// FAN HAO :ROWNO
				int count = 1;
				for (int i = 0; i < situationChangeList.size(); i++) {
					situationChangeList.get(i).setRowNo(count);
					count++;
				}

				Map<String, Object> resultdata = new HashMap<>();
				resultdata.put("data", situationChangeList);
				return resultdata;
			}

			// bouns検索
			else if (situationInfo.getClassification().equals("2")) {
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "bonus検索開始");
				situationChangeList = SituationChangesService.searchSitautionChangesBonus(sendMap);
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "bonus検索結束");
				if (situationChangeList.size() == 0) {
					String noData = "";
					noData = "条件に該当する結果が存在しない";
					resulterr.put("noData", noData);
					return resulterr;
				}
				int count = 1;
				for (int i = 0; i < situationChangeList.size(); i++) {
					if (situationChangeList.get(i).getSocialInsuranceFlag() != null
							&& situationChangeList.get(i).getSocialInsuranceFlag().equals("1")) {
						SocialInsurance = "追加";
					} else {
						SocialInsurance = "なし";
					}
					if (situationChangeList.get(i).getLastTimeBonusAmount()
							.equals(situationChangeList.get(i).getScheduleOfBonusAmount())
							|| situationChangeList.get(i).getLastTimeBonusAmount() == null
							|| situationChangeList.get(i).getLastTimeBonusAmount().equals("0")
							|| situationChangeList.get(i).getLastTimeBonusAmount().equals("")) {
						ScheduleOfBonusAmount = moneyFormat(
								Integer.parseInt(situationChangeList.get(i).getScheduleOfBonusAmount()));
					} else if (situationChangeList.get(i).getLastTimeBonusAmount()
							.equals(situationChangeList.get(i).getScheduleOfBonusAmount())
							|| situationChangeList.get(i).getScheduleOfBonusAmount() == null
							|| situationChangeList.get(i).getScheduleOfBonusAmount().equals("0")
							|| situationChangeList.get(i).getLastTimeBonusAmount().equals("")) {
						ScheduleOfBonusAmount = moneyFormat(
								Integer.parseInt(situationChangeList.get(i).getLastTimeBonusAmount()));
					}

					else {
						ScheduleOfBonusAmount = moneyFormat(
								Integer.parseInt(situationChangeList.get(i).getLastTimeBonusAmount())) + "~"
								+ moneyFormat(Integer.parseInt(situationChangeList.get(i).getScheduleOfBonusAmount()));
					}
					situationChangeList.get(i)
							.setSalary(moneyFormat(Integer.parseInt(situationChangeList.get(i).getSalary())));
					situationChangeList.get(i).setSocialInsuranceFlag(SocialInsurance);
					situationChangeList.get(i).setScheduleOfBonusAmount(ScheduleOfBonusAmount);
					situationChangeList.get(i).setStatus("ボーナス");
					situationChangeList.get(i).setRowNo(count);
					count++;
				}

				Map<String, Object> resultdata = new HashMap<>();
				resultdata.put("data", situationChangeList);
				return resultdata;
			}

			else {
				// QU FEN :FEI RU TUI ZHI
				logger.info("PersonalSalesSearchController.searchSituationChanges:" + "検索開始");
				situationChangeList = SituationChangesService.searchSituationChanges(sendMap);
				logger.info("PersonalSalesSearchController.searchSituationChanges:" + "検索結束");
				for (int i = 0; i < situationChangeList.size(); i++) {
					if (situationChangeList.get(i).getSocialInsuranceFlag().equals("1")) {
						situationChangeList.get(i).setSocialInsuranceFlag("追加");
					} else {
						situationChangeList.get(i).setSocialInsuranceFlag("なし");
					}
				}
				if (situationChangeList.size() == 0) {
					String noData = "";
					noData = "条件に該当する結果が存在しない";
					resulterr.put("noData", noData);
					return resulterr;
				}
				List<String> getYandM = new ArrayList<String>();
				List<String> getEmpNo = new ArrayList<String>();
				List<Map> tempList = new ArrayList<Map>();
				for (int i = 0; i < situationChangeList.size(); i++) {
					Map<String, String> tempMap = new HashMap<String, String>();
					tempMap.put("getYandM", situationChangeList.get(i).getReflectYearAndMonth());
					tempMap.put("getEmpNo", situationChangeList.get(i).getEmployeeNo());
					getYandM.add(i, situationChangeList.get(i).getReflectYearAndMonth());
					getEmpNo.add(i, situationChangeList.get(i).getEmployeeNo());
					tempList.add(tempMap);
				}
				sendMap.put("tempList", tempList);

				logger.info("PersonalSalesSearchController.searchSituationChangesFront:" + "二回目検索開始");
				situationChangeListFront = SituationChangesService.searchSituationChangesFront(sendMap);
				logger.info("PersonalSalesSearchController.searchSituationChangesFront:" + "二回目検索結束");

				for (int i = 0; i < situationChangeListFront.size(); i++) {
					if (situationChangeListFront.get(i).getSocialInsuranceFlag().equals("1")) {
						situationChangeListFront.get(i).setSocialInsuranceFlag("追加");
					} else {
						situationChangeListFront.get(i).setSocialInsuranceFlag("なし");
					}
				}

				String salaryChange = "";
				String socialInsuranceFlagChange = "";
				int startIndex = situationChangeListFront.size() - 1;

				// FAN HAO :ROWNO
				for (int i = situationChangeList.size() - 1; i >= 0; i--) {
					for (int j = startIndex; j >= 0; j--) {
						String empNo = situationChangeList.get(i).getEmployeeNo();
						String empNoFront = situationChangeListFront.get(j).getEmployeeNo();

						// not入退职
						if (empNo.equals(empNoFront)) {
							// GONGZI合并
							if (situationChangeListFront.get(j).getSalary()
									.equals(situationChangeList.get(i).getSalary())
									|| situationChangeList.get(i).getSalary().equals("0")
									|| situationChangeList.get(i).getSalary().equals("")) {
								situationChangeList.get(i).setSalaryFlag("0");
							} else if (situationChangeListFront.get(j).getSalary().equals("0")
									|| situationChangeListFront.get(j).getSalary().equals("")
											&& situationChangeList.get(i).getSalary() != null
											&& !(situationChangeList.get(i).getSalary().equals("0"))) {
								salaryChange = moneyFormat(Integer.parseInt(situationChangeList.get(i).getSalary()));
								situationChangeList.get(i).setSalaryFlag("1");
								situationChangeList.get(i).setStatus("給料変更");
							} else {
								salaryChange = moneyFormat(
										Integer.parseInt(situationChangeListFront.get(j).getSalary())) + "~"
										+ moneyFormat(Integer.parseInt(situationChangeList.get(i).getSalary()));
								situationChangeList.get(i).setSalaryFlag("1");
								situationChangeList.get(i).setStatus("給料変更");
							}

							// BAOXIAN合并
							if (situationChangeListFront.get(j).getSocialInsuranceFlag()
									.equals(situationChangeList.get(i).getSocialInsuranceFlag())) {
								socialInsuranceFlagChange = "なし";
							} else {
								socialInsuranceFlagChange = situationChangeListFront.get(j).getSocialInsuranceFlag()
										+ "~" + situationChangeList.get(i).getSocialInsuranceFlag();
							}


							// SHEYUANXINGSHI合并
							if (situationChangeListFront.get(j).getEmployeeFormName()
									.equals(situationChangeList.get(i).getEmployeeFormName())
									|| situationChangeListFront.get(j).getEmployeeFormName() == null) {
								employeeFormNameChange = situationChangeList.get(i).getEmployeeFormName();
							} else if (situationChangeListFront.get(j).getEmployeeFormName()
									.equals(situationChangeList.get(i).getEmployeeFormName())
									|| situationChangeList.get(i).getEmployeeFormName() == null) {
								employeeFormNameChange = situationChangeListFront.get(j).getEmployeeFormName();
							} else {
								employeeFormNameChange = situationChangeListFront.get(j).getEmployeeFormName() + "~"
										+ situationChangeList.get(i).getEmployeeFormName();
							}

							// setparam
							situationChangeList.get(i).setSalary(salaryChange);
							situationChangeList.get(i).setSocialInsuranceFlag(socialInsuranceFlagChange);
							// situationChangeList.get(i).setScheduleOfBonusAmount(ScheduleOfBonusAmount);
							situationChangeList.get(i).setEmployeeFormName(employeeFormNameChange);
							if (situationChangeList.get(i).getSalaryFlag() == null) {
								situationChangeList.get(i).setSalaryFlag("1");
							}
							startIndex--;
							break;
						}
					}
					if (situationChangeList.get(i).getLastTimeBonusAmount() != null
							&& !(situationChangeList.get(i).getLastTimeBonusAmount()).equals("")) {
						ScheduleOfBonusAmount = moneyFormat(
								Integer.parseInt(situationChangeList.get(i).getLastTimeBonusAmount()));
					} else {
						ScheduleOfBonusAmount = "";
					}

					situationChangeList.get(i).setScheduleOfBonusAmount(ScheduleOfBonusAmount);
					if (situationChangeList.get(i).getSalaryFlag() == null) {
						situationChangeList.get(i).setSalaryFlag("1");
					}
				}
				// QU FEN: 給料変更
				if (situationInfo.getClassification().equals("1")) {
					List<SituationChangesModel> salaryChangeList = new ArrayList<SituationChangesModel>();
					for (int n = 0; n < situationChangeList.size(); n++) {
						if (situationChangeList.get(n).getSalaryFlag() == null) {
							situationChangeList.get(n).setSalaryFlag("0");
						}
						if (situationChangeList.get(n).getSalaryFlag().equals("1")) {
							salaryChangeList.add(situationChangeList.get(n));
						}
					}

					int count = 1;
					for (int i = 0; i < salaryChangeList.size(); i++) {
						salaryChangeList.get(i).setRowNo(count);
						count++;
					}
					for (int i = 0; i < situationChangeList.size(); i++) {
						if (situationChangeList.get(i).getStatus() == null
								&& situationChangeList.get(i).getSalary() != null) {
							situationChangeList.get(i).setStatus("給料変更");
							if (situationChangeList.get(i).getSalary() != null
									&& !situationChangeList.get(i).getSalary().contains(",")) {
								String moneyF = moneyFormat(Integer.parseInt(situationChangeList.get(i).getSalary()));
								situationChangeList.get(i).setSalary(moneyF);
							}
						}
					}
					Map<String, Object> resultdata = new HashMap<>();
					resultdata.put("data", salaryChangeList);
					return resultdata;
				}

				// qufen: bouns検索
				List<SituationChangesModel> situationChangeListbonus = new ArrayList<SituationChangesModel>();
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "bonus検索開始");
				situationChangeListbonus = SituationChangesService.searchSitautionChangesBonus(sendMap);
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "bonus検索結束");
				for (int i = 0; i < situationChangeListbonus.size(); i++) {
					if (situationChangeListbonus.get(i).getSocialInsuranceFlag() != null
							&& situationChangeListbonus.get(i).getSocialInsuranceFlag().equals("1")) {
						SocialInsurance = "追加";
					} else {
						SocialInsurance = "なし";
					}
					if (situationChangeListbonus.get(i).getLastTimeBonusAmount()
							.equals(situationChangeListbonus.get(i).getScheduleOfBonusAmount())
							|| situationChangeListbonus.get(i).getLastTimeBonusAmount() == null
							|| situationChangeListbonus.get(i).getLastTimeBonusAmount().equals("0")
							|| situationChangeListbonus.get(i).getLastTimeBonusAmount().equals("")) {
						ScheduleOfBonusAmount = moneyFormat(
								Integer.parseInt(situationChangeListbonus.get(i).getScheduleOfBonusAmount()));
					} else if (situationChangeListbonus.get(i).getLastTimeBonusAmount()
							.equals(situationChangeListbonus.get(i).getScheduleOfBonusAmount())
							|| situationChangeListbonus.get(i).getScheduleOfBonusAmount() == null
							|| situationChangeListbonus.get(i).getScheduleOfBonusAmount().equals("0")
							|| situationChangeListbonus.get(i).getLastTimeBonusAmount().equals("")) {
						ScheduleOfBonusAmount = moneyFormat(
								Integer.parseInt(situationChangeListbonus.get(i).getLastTimeBonusAmount()));
					}

					else {
						ScheduleOfBonusAmount = moneyFormat(
								Integer.parseInt(situationChangeListbonus.get(i).getLastTimeBonusAmount())) + "~"
								+ moneyFormat(
										Integer.parseInt(situationChangeListbonus.get(i).getScheduleOfBonusAmount()));
					}
					situationChangeListbonus.get(i)
							.setSalary(moneyFormat(Integer.parseInt(situationChangeListbonus.get(i).getSalary())));
					situationChangeListbonus.get(i).setSocialInsuranceFlag(SocialInsurance);
					situationChangeListbonus.get(i).setScheduleOfBonusAmount(ScheduleOfBonusAmount);
					situationChangeListbonus.get(i).setStatus("ボーナス");
					situationChangeList.add(situationChangeListbonus.get(i));
				}

				// QU FEN : 入社检索
				List<SituationChangesModel> situationChangeListTwice = new ArrayList<SituationChangesModel>();
				sendMap.put("classification", 3);
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "入社検索開始");
				situationChangeListTwice = SituationChangesService.searchSituationIntoORretirement(sendMap);
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "入社検索結束");
				for (int i = 0; i < situationChangeListTwice.size(); i++) {
					if (situationChangeListTwice.get(i).getSocialInsuranceFlag() != null
							&& situationChangeListTwice.get(i).getSocialInsuranceFlag().equals("1")) {
						SocialInsurance = "追加";
					} else {
						SocialInsurance = "なし";
					}
					situationChangeListTwice.get(i).setSocialInsuranceFlag(SocialInsurance);
					situationChangeListTwice.get(i).setSalaryFlag("0");
					situationChangeListTwice.get(i).setBonusFlag("0");
					situationChangeListTwice.get(i).setStatus("入職");

				}
				for (int i = 0; i < situationChangeListTwice.size(); i++) {
					if (situationChangeListTwice.get(i).getSalary() != null) {
						String moneyF = moneyFormat(Integer.parseInt(situationChangeListTwice.get(i).getSalary()));
						situationChangeListTwice.get(i).setSalary(moneyF);
					}
					if (situationChangeListTwice.get(i).getScheduleOfBonusAmount() != null) {
						String moneyF = moneyFormat(
								Integer.parseInt(situationChangeListTwice.get(i).getScheduleOfBonusAmount()));
						situationChangeListTwice.get(i).setScheduleOfBonusAmount(moneyF);
					}
					situationChangeList.add(situationChangeListTwice.get(i));
				}
				// QU FEN : 退社检索
				List<SituationChangesModel> situationChangeListThird = new ArrayList<SituationChangesModel>();
				sendMap.put("classification", 4);
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "退社検索開始");
				situationChangeListThird = SituationChangesService.searchSituationIntoORretirement(sendMap);
				logger.info("PersonalSalesSearchController.searchSituationIntoORretirement:" + "退社検索結束");
				for (int i = 0; i < situationChangeListThird.size(); i++) {
					if (situationChangeListThird.get(i).getSocialInsuranceFlag() != null
							&& situationChangeListThird.get(i).getSocialInsuranceFlag().equals("1")) {
						SocialInsurance = "追加";
					} else {
						SocialInsurance = "なし";
					}
					situationChangeListThird.get(i).setSocialInsuranceFlag(SocialInsurance);
					situationChangeListThird.get(i).setSalaryFlag("0");
					situationChangeListThird.get(i).setBonusFlag("0");
					situationChangeListThird.get(i).setStatus("退職");

				}
				for (int i = 0; i < situationChangeListThird.size(); i++) {
					if (situationChangeListThird.get(i).getSalary() != null) {
						String moneyF = moneyFormat(Integer.parseInt(situationChangeListThird.get(i).getSalary()));
						situationChangeListThird.get(i).setSalary(moneyF);
					}
					if (situationChangeListThird.get(i).getScheduleOfBonusAmount() != null) {
						String moneyF = moneyFormat(
								Integer.parseInt(situationChangeListThird.get(i).getScheduleOfBonusAmount()));
						situationChangeListThird.get(i).setScheduleOfBonusAmount(moneyF);
					}
					situationChangeList.add(situationChangeListThird.get(i));
				}
				int count = 1;
				for (int i = 0; i < situationChangeList.size(); i++) {
					if (situationChangeList.get(i).getStatus() == null
							&& situationChangeList.get(i).getSalary() != null) {
						situationChangeList.get(i).setStatus("給料変更");
						if (situationChangeList.get(i).getSalary() != null
								&& !situationChangeList.get(i).getSalary().contains(",")) {
							String moneyF = moneyFormat(Integer.parseInt(situationChangeList.get(i).getSalary()));
							situationChangeList.get(i).setSalary(moneyF);
						}
					}

					situationChangeList.get(i).setRowNo(count);
					count++;
				}
				Map<String, Object> resultdata = new HashMap<>();
				resultdata.put("data", situationChangeList);
				return resultdata;
			}
		}
	}

	public Map<String, Object> getDetailParam(SituationChangesModel situationInfo) {
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String startYandM = situationInfo.getStartYandM();
		String endYandM = situationInfo.getEndYandM();
		String classification = situationInfo.getClassification();
		sendMap.put("startYandM", startYandM);
		sendMap.put("endYandM", endYandM);
		sendMap.put("classification", classification);
		return sendMap;

	}

	public static String moneyFormat(long number) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(number);
	}
}

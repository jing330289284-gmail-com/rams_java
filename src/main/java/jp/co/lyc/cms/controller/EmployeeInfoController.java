package jp.co.lyc.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.AccountInfoModel;
import jp.co.lyc.cms.model.CostInfoModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.BpInfoModel;
import jp.co.lyc.cms.service.EmployeeInfoService;
import jp.co.lyc.cms.util.UtilsController;
import jp.co.lyc.cms.validation.EmployeeInfoValidation;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/employee")
public class EmployeeInfoController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EmployeeInfoService employeeInfoService;

	@Autowired
	UtilsController utilsController;

	/**
	 * データを取得
	 * 
	 * @param emp
	 * @return List
	 */
	String errorsMessage = "";;

	@RequestMapping(value = "/getEmployeeInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEmployeeInfo(@RequestBody EmployeeModel emp) {
		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索開始");
		errorsMessage = "";
		DataBinder binder = new DataBinder(emp);
		binder.setValidator(new EmployeeInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> result = new HashMap<>();
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			result.put("errorsMessage", errorsMessage);// エラーメッセージ
			return result;
		}

		List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
		try {
			Map<String, Object> sendMap = getParam(emp);
			employeeList = employeeInfoService.getEmployeeInfo(sendMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("data", employeeList);
		logger.info("GetEmployeeInfoController.getEmployeeInfo:" + "検索結束");
		return result;
	}

	/**
	 * 社員情報を追加
	 * 
	 * @param emp
	 * @return boolean
	 */
	@RequestMapping(value = "/insertEmployee", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertEmployee(@RequestParam(value = "emp", required = false) String JSONEmp,
			@RequestParam(value = "resumeInfo1", required = false) MultipartFile resumeInfo1,
			@RequestParam(value = "resumeInfo2", required = false) MultipartFile resumeInfo2,
			@RequestParam(value = "residentCardInfo", required = false) MultipartFile residentCardInfo,
			@RequestParam(value = "passportInfo", required = false) MultipartFile passportInfo,
			@RequestParam(value = "pictures", required = false) MultipartFile pictures) throws Exception {
		logger.info("GetEmployeeInfoController.insertEmployee:" + "追加開始");
		errorsMessage = "";
		JSONObject jsonObject = JSON.parseObject(JSONEmp);
		EmployeeModel emp = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<EmployeeModel>() {
		});
		DataBinder binder = new DataBinder(emp);
		binder.setValidator(new EmployeeInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> resultMap = new HashMap<>();// 戻す
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			resultMap.put("errorsMessage", errorsMessage);// エラーメッセージ
			return resultMap;
		}

		Map<String, Object> sendMap = getParam(emp);// パラメータ
		boolean result = true;
		try {
			sendMap = utilsController.upload(resumeInfo1, sendMap, "resumeInfo1", "履歴書1");
			sendMap = utilsController.upload(resumeInfo2, sendMap, "resumeInfo2", "履歴書2");
			sendMap = utilsController.upload(residentCardInfo, sendMap, "residentCardInfo", "在留カード");
			sendMap = utilsController.upload(passportInfo, sendMap, "passportInfo", "パスポート");
		    sendMap = utilsController.upload(pictures, sendMap, "picInfo", "写真");
			employeeInfoService.insertEmployee((HashMap<String, Object>) sendMap);
		} catch (Exception e) {
			resultMap.put("result", false);
			return resultMap;
		}
		resultMap.put("result", result);
		logger.info("GetEmployeeInfoController.insertEmployee:" + "追加結束");
		return resultMap;
	}

	/**
	 * 社員情報を削除
	 * 
	 * @param emp
	 * @return boolean
	 */
	@RequestMapping(value = "/deleteEmployeeInfo", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteEmployeeInfo(@RequestBody EmployeeModel emp) throws Exception {
		logger.info("GetEmployeeInfoController.addEmployeeInfo:" + "削除開始");

		Map<String, Object> sendMap = getParam(emp);
		boolean result = true;
		result = employeeInfoService.deleteEmployeeInfo(sendMap);
		if (result) {
			// 自分のファイルを削除
			// utilsController.deleteDir(emp.getResidentCardInfo());
		}
		logger.info("GetEmployeeInfoController.addEmployeeInfo:" + "削除結束");
		return result;
	}

	/**
	 * EmployeeNoによると、社員情報を取得
	 * 
	 * @param emp
	 * @return EmployeeModel
	 */
	@RequestMapping(value = "/getEmployeeByEmployeeNo", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeModel getEmployeeByEmployeeNo(@RequestBody EmployeeModel emp) throws Exception {
		logger.info("GetEmployeeInfoController.addEmployeeInfo:" + "EmployeeNoによると、社員情報を取得開始");
		Map<String, Object> sendMap = getParam(emp);
		EmployeeModel model;
		model = employeeInfoService.getEmployeeByEmployeeNo(sendMap);
		logger.info("GetEmployeeInfoController.addEmployeeInfo:" + "EmployeeNoによると、社員情報を取得結束");
		return model;
	}

	/**
	 * 社員情報を修正
	 * 
	 * @param emp
	 * @return boolean
	 */

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateEmployee(@RequestParam(value = "emp", required = false) String JSONEmp,
			@RequestParam(value = "resumeInfo1", required = false) MultipartFile resumeInfo1,
			@RequestParam(value = "resumeInfo2", required = false) MultipartFile resumeInfo2,
			@RequestParam(value = "residentCardInfo", required = false) MultipartFile residentCardInfo,
			@RequestParam(value = "passportInfo", required = false) MultipartFile passportInfo) throws Exception {
		logger.info("GetEmployeeInfoController.updateEmployee:" + "修正開始");

		JSONObject jsonObject = JSON.parseObject(JSONEmp);
		EmployeeModel emp = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<EmployeeModel>() {
		});
		DataBinder binder = new DataBinder(emp);
		binder.setValidator(new EmployeeInfoValidation());
		binder.validate();
		BindingResult results = binder.getBindingResult();
		Map<String, Object> resultMap = new HashMap<>();// 繰り返し
		if (results.hasErrors()) {
			results.getAllErrors().forEach(o -> {
				FieldError error = (FieldError) o;
				errorsMessage += error.getDefaultMessage();// エラーメッセージ
			});
			resultMap.put("errorsMessage", errorsMessage);// エラーメッセージ
			return resultMap;
		}

		Map<String, Object> sendMap = getParam(emp);
		boolean result = true;
		try {
			sendMap = utilsController.upload(resumeInfo1, sendMap, "resumeInfo1", "履歴書1");
			sendMap = utilsController.upload(resumeInfo2, sendMap, "resumeInfo2", "履歴書2");
			sendMap = utilsController.upload(residentCardInfo, sendMap, "residentCardInfo", "在留カード");
			sendMap = utilsController.upload(passportInfo, sendMap, "passportInfo", "パスポート");
			result = employeeInfoService.updateEmployee(sendMap);
		} catch (Exception e) {
			resultMap.put("result", false);
			return resultMap;
		}
		resultMap.put("result", result);
		logger.info("GetEmployeeInfoController.updateEmployee:" + "修正結束");
		return resultMap;
	}

	/**
	 * 条件を取得
	 * 
	 * @param emp
	 * @return
	 */
	public Map<String, Object> getParam(EmployeeModel emp) {
		HttpSession loginSession = getSession();
		Map<String, Object> sendMap = new HashMap<String, Object>();
		String employeeNo = emp.getEmployeeNo();// 社員番号
		String employeeFristName = emp.getEmployeeFristName();// 社員氏
		String employeeLastName = emp.getEmployeeLastName();// 社員名
		String furigana = (emp.getFurigana1() != null ? emp.getFurigana1() : "") + " "
				+ (emp.getFurigana2() != null ? emp.getFurigana2() : "");// カタカナ
		String alphabetName = emp.getAlphabetName();// ローマ字
		String birthday = emp.getBirthday();// 年齢
		String genderStatus = emp.getGenderStatus();// 性別ステータス
		String intoCompanyCode = emp.getIntoCompanyCode();// 入社区分
		String employeeFormCode = emp.getEmployeeFormCode();// 社員形式
		String japaneseCalendar = emp.getJapaneseCalendar();// 和暦
		String occupationCode = emp.getOccupationCode();// 職種コード
		String departmentCode = emp.getDepartmentCode();// 部署
		String companyMail = emp.getCompanyMail();// 社内メール
		String graduationUniversity = emp.getGraduationUniversity();// 卒業学校
		String major = emp.getMajor();// 専門
		String graduationYearAndMonth = emp.getGraduationYearAndMonth();// 卒業年月
		String intoCompanyYearAndMonth = emp.getIntoCompanyYearAndMonth();// 入社年月
		String retirementYearAndMonth = emp.getRetirementYearAndMonth();// 退職年月
		String comeToJapanYearAndMonth = emp.getComeToJapanYearAndMonth();// 来日年月
		String nationalityCode = emp.getNationalityCode();// 出身地コード(国)
		String birthplace = emp.getBirthplace();// 出身地(県)
		String phoneNo = emp.getPhoneNo();// 携帯電話
		String japaneseLevelCode = emp.getJapaneseLevelCode();// 日本語のレベル
		String englishLevelCode = emp.getEnglishLevelCode();// 英語
		String certification1 = emp.getCertification1();// 資格1
		String certification2 = emp.getCertification2();// 資格2

		String developLanguage1 = emp.getDevelopLanguage1();// 技術语言1
		String developLanguage2 = emp.getDevelopLanguage2();// 技術语言2
		String developLanguage3 = emp.getDevelopLanguage3();// 技術语言3
		String developLanguage4 = emp.getDevelopLanguage4();// 技術语言4
		String developLanguage5 = emp.getDevelopLanguage5();// 技術语言5
		String residenceCode = emp.getResidenceCode();// 在留資格
		String residenceCardNo = emp.getResidenceCardNo();// 在留カード
		String stayPeriod = emp.getStayPeriod();// 在留期間
		String employmentInsuranceNo = emp.getEmploymentInsuranceNo();// 雇用保険番号
		String myNumber = emp.getMyNumber();// マイナンバー
		String resumeRemark1 = emp.getResumeRemark1();// 備考１
		String resumeRemark2 = emp.getResumeRemark2();// 備考２
		String passportNo = emp.getPassportNo();// パスポート
		String ageFrom = emp.getAgeFrom();// 開始年齢
		String ageTo = emp.getAgeTo();// 終了年齢
		String customer = emp.getCustomer();// お客様先
		String unitPriceFrom = emp.getUnitPriceFrom();// 単価範囲from
		String unitPriceTo = emp.getUnitPriceTo();// 単価範囲to
		String kadou = emp.getKadou();// 稼働
		String intoCompanyYearAndMonthFrom = emp.getIntoCompanyYearAndMonthFrom();// 入社年月元
		String intoCompanyYearAndMonthTo = emp.getIntoCompanyYearAndMonthTo();// 入社年月先
		String authorityCode = emp.getAuthorityCode();// 権限
		String employeeStatus = emp.getEmployeeStatus();// 社員ステータス
		// String picInfo = emp.getPicInfo();// 写真
		String yearsOfExperience = emp.getYearsOfExperience();// 経験年数

		AccountInfoModel accountInfoModel = emp.getAccountInfo();// 口座情報

		CostInfoModel costModel = emp.getCostModel();// 諸費用
		BpInfoModel bpInfoModel = emp.getBpInfoModel();// bp情報
		String password = emp.getPassword();// パスワード
		//　　住所情報開始
		String postcode = emp.getPostcode();// 郵便番号
		String firstHalfAddress = emp.getFirstHalfAddress();//住所前半
		String lastHalfAddress = emp.getLastHalfAddress();//住所後半
		String stationCode = emp.getStationCode();//　　最寄駅1
		if (stationCode != null && stationCode.length() != 0) {
			sendMap.put("stationCode", stationCode);
		}
		//　　住所情報終了

		if (employeeNo != null && employeeNo.length() != 0) {
			sendMap.put("employeeNo", employeeNo);
		}
		if (employeeFristName != null && employeeFristName.length() != 0) {
			sendMap.put("employeeFristName", employeeFristName);
		}
		if (employeeLastName != null && employeeLastName.length() != 0) {
			sendMap.put("employeeLastName", employeeLastName);
		}
		if (furigana != null && furigana.length() != 0) {
			sendMap.put("furigana", furigana);
		}
		if (alphabetName != null && alphabetName.length() != 0) {
			sendMap.put("alphabetName", alphabetName);
		}
		if (birthday != null && birthday.length() != 0) {
			sendMap.put("birthday", birthday);
		}
		if (japaneseCalendar != null && japaneseCalendar.length() != 0) {
			sendMap.put("japaneseCalendar", japaneseCalendar);
		}

		if (occupationCode != null && occupationCode.length() != 0) {
			sendMap.put("occupationCode", occupationCode);
		}
		if (departmentCode != null && departmentCode.length() != 0) {
			sendMap.put("departmentCode", departmentCode);
		}
		if (companyMail != null && companyMail.length() != 0) {
			sendMap.put("companyMail", companyMail);
		}
		if (graduationUniversity != null && graduationUniversity.length() != 0) {
			sendMap.put("graduationUniversity", graduationUniversity);
		}
		if (major != null && major.length() != 0) {
			sendMap.put("major", major);
		}
		if (graduationYearAndMonth != null && graduationYearAndMonth.length() != 0) {
			sendMap.put("graduationYearAndMonth", graduationYearAndMonth);
		}
		if (intoCompanyYearAndMonth != null && intoCompanyYearAndMonth.length() != 0) {
			sendMap.put("intoCompanyYearAndMonth", intoCompanyYearAndMonth);
		}
		if (retirementYearAndMonth != null && retirementYearAndMonth.length() != 0) {
			sendMap.put("retirementYearAndMonth", retirementYearAndMonth);
		}
		if (comeToJapanYearAndMonth != null && comeToJapanYearAndMonth.length() != 0) {
			sendMap.put("comeToJapanYearAndMonth", comeToJapanYearAndMonth);
		}
		if (birthplace != null && birthplace.length() != 0) {
			sendMap.put("birthplace", birthplace);
		}
		if (phoneNo != null && phoneNo.length() != 0) {
			sendMap.put("phoneNo", phoneNo);
		}

		if (residenceCode != null && residenceCode.length() != 0) {
			sendMap.put("residenceCode", residenceCode);
		}
		if (residenceCardNo != null && residenceCardNo.length() != 0) {
			sendMap.put("residenceCardNo", residenceCardNo);
		}
		if (stayPeriod != null && stayPeriod.length() != 0) {
			sendMap.put("stayPeriod", stayPeriod);
		}
		if (employmentInsuranceNo != null && employmentInsuranceNo.length() != 0) {
			sendMap.put("employmentInsuranceNo", employmentInsuranceNo);
		}
		if (myNumber != null && myNumber.length() != 0) {
			sendMap.put("myNumber", myNumber);
		}
		if (resumeRemark2 != null && resumeRemark2.length() != 0) {
			sendMap.put("resumeRemark2", resumeRemark2);
		}
		if (resumeRemark1 != null && resumeRemark1.length() != 0) {
			sendMap.put("resumeRemark1", resumeRemark1);
		}
		if (passportNo != null && passportNo.length() != 0) {
			sendMap.put("passportNo", passportNo);
		}
		if (employeeFormCode != null && employeeFormCode.length() != 0) {
			sendMap.put("employeeFormCode", employeeFormCode);
		}
		if (customer != null && customer.length() != 0) {
			sendMap.put("customer", customer);
		}
		if (intoCompanyCode != null && intoCompanyCode.length() != 0) {
			sendMap.put("intoCompanyCode", intoCompanyCode);
		}
		if (nationalityCode != null && nationalityCode.length() != 0) {
			sendMap.put("nationalityCode", nationalityCode);
		}

		if (genderStatus != null && genderStatus.length() != 0) {
			sendMap.put("genderStatus", genderStatus);
		}
		if (ageFrom != null && ageFrom.length() != 0) {
			sendMap.put("ageFrom", ageFrom);
		}
		if (ageTo != null && ageTo.length() != 0) {
			sendMap.put("ageTo", ageTo);
		}

		if (unitPriceFrom != null && unitPriceFrom.length() != 0) {
			sendMap.put("unitPriceFrom", unitPriceFrom);
		}
		if (unitPriceTo != null && unitPriceTo.length() != 0) {
			sendMap.put("unitPriceTo", unitPriceTo);
		}
		if (japaneseLevelCode != null && japaneseLevelCode.length() != 0) {
			sendMap.put("japaneseLevelCode", japaneseLevelCode);
		}
		if (englishLevelCode != null && englishLevelCode.length() != 0) {
			sendMap.put("englishLevelCode", englishLevelCode);
		}
		if (certification1 != null && certification1.length() != 0) {
			sendMap.put("certification1", certification1);
		}
		if (certification2 != null && certification2.length() != 0) {
			sendMap.put("certification2", certification2);
		}
		if (postcode != null && postcode.length() != 0) {
			sendMap.put("postcode", postcode);
		}
		if (firstHalfAddress != null && firstHalfAddress.length() != 0) {
			sendMap.put("firstHalfAddress", firstHalfAddress);
		}
		if (lastHalfAddress != null && lastHalfAddress.length() != 0) {
			sendMap.put("lastHalfAddress", lastHalfAddress);
		}
		if (developLanguage4 != null && developLanguage4.length() != 0) {
			sendMap.put("developLanguage4", developLanguage4);
		}
		if (developLanguage5 != null && developLanguage5.length() != 0) {
			sendMap.put("developLanguage5", developLanguage5);
		}
		if (kadou != null && kadou.length() != 0) {
			sendMap.put("kadou", kadou);
		}

		if (developLanguage1 != null && developLanguage1.length() != 0) {
			sendMap.put("developLanguage1", developLanguage1);
		}
		if (developLanguage2 != null && developLanguage2.length() != 0) {
			sendMap.put("developLanguage2", developLanguage2);
		}
		if (developLanguage3 != null && developLanguage3.length() != 0) {
			sendMap.put("developLanguage3", developLanguage3);
		}
		if (intoCompanyYearAndMonthFrom != null && intoCompanyYearAndMonthFrom.length() != 0) {
			sendMap.put("intoCompanyYearAndMonthFrom", intoCompanyYearAndMonthFrom);
		}
		if (intoCompanyYearAndMonthTo != null && intoCompanyYearAndMonthTo.length() != 0) {
			sendMap.put("intoCompanyYearAndMonthTo", intoCompanyYearAndMonthTo);
		}
		if (authorityCode != null && authorityCode.length() != 0) {
			sendMap.put("authorityCode", authorityCode);
		}
		sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
		if (employeeStatus != null && employeeStatus.length() != 0) {
			sendMap.put("employeeStatus", employeeStatus);
		}
		if (yearsOfExperience != null && yearsOfExperience.length() != 0) {
			sendMap.put("yearsOfExperience", yearsOfExperience);
		}
		sendMap.put("bankInfoModel", accountInfoModel);
		sendMap.put("costModel", costModel);
		sendMap.put("bpInfoModel", bpInfoModel);
		if (password != null && password.length() != 0) {
			sendMap.put("password", password);
		}
		
		return sendMap;
	}

}

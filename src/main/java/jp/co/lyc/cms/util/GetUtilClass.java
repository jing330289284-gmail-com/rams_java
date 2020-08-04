package jp.co.lyc.cms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.ModelClass;
import jp.co.lyc.cms.service.GetSelectInfoUtilService;
import net.sf.json.JSONObject;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class GetUtilClass {

	@Autowired
	GetProperties getProperties;

	@Autowired
	GetSelectInfoUtilService getSelectInfoUtilService;

	/**
	 * 国籍を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNationalitys", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getNationalitys() {
		List<ModelClass> list = getSelectInfoUtilService.getNationalitys();
		return list;
	}

	/**
	 * 社員形式を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getStaffForms", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getStaffForms() {
		List<ModelClass> list = getSelectInfoUtilService.getStaffForms();
		return list;
	}

	/**
	 * 在留資格を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getVisa", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getVisa() {
		List<ModelClass> list = getSelectInfoUtilService.getVisa();
		return list;
	}

	/**
	 * 開発言語を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTechnologyType", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getTechnologyType(@RequestBody EmployeeModel emp) {
		Map<String, String> sendMap = getParam(emp);
		List<ModelClass> list = getSelectInfoUtilService.getTechnologyType(sendMap);
		return list;
	}

	/**
	 * レベル
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getLevel() {
		List<ModelClass> list = getSelectInfoUtilService.getLevel();
		return list;
	}
	
	/**
	 * 日本語レベルを取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getJapaneseLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getJapaneseLevel() {
		List<ModelClass> list = getSelectInfoUtilService.getJapaneseLevel();
		return list;
	}
	
	/**
	 * お客様性質を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectCompanyNature", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> selectCompanyNature() {
		List<ModelClass> list = getSelectInfoUtilService.selectCompanyNature();
		return list;
	}
	
	/**
	 * 職位を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectPosition", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> selectPosition() {
		List<ModelClass> list = getSelectInfoUtilService.selectPosition();
		return list;
	}
	
	/**
	 * 上位お客様連想
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectTopCustomer", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> selectTopCustomer(@RequestBody ModelClass model) {
		List<ModelClass> list = getSelectInfoUtilService.selectTopCustomer(model.getName());
		return list;
	}
	
	/**
	 * 部門名前連想
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectDepartmentMaster", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> selectDepartmentMaster(@RequestBody ModelClass model) {
		List<ModelClass> list = getSelectInfoUtilService.selectDepartmentMaster(model.getName());
		return list;
	}
	
	/**
	 * 銀行名検索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> selectBankInfo() {
		List<ModelClass> list = getSelectInfoUtilService.selectBankInfo();
		return list;
	}
	
	/**
	 * 支店情報検索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBankBranchInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getBankBranchInfo(@RequestBody HashMap<String, String> sendMap) {
		List<ModelClass> list = getSelectInfoUtilService.getBankBranchInfo(sendMap);
		return list;
	}

	/**
	 * 性別区別
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getGender", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getGender() {
		Properties properties = getProperties.getProperties();
		String gender = properties.getProperty("gender");
		List<ModelClass> list = getStatus(gender);
		return list;
	}

	/**
	 * 上場
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getListedCompany", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getListedCompany() {
		Properties properties = getProperties.getProperties();
		String listedCompany = properties.getProperty("listedCompany");
		List<ModelClass> list = getStatus(listedCompany);
		return list;
	}

	/**
	 * 住宅
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getHousing", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getHousing() {
		Properties properties = getProperties.getProperties();
		String Housing = properties.getProperty("Housing");
		List<ModelClass> list = getStatus(Housing);
		return list;
	}

	/**
	 * 社員
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getEmployee", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getEmployee() {
		Properties properties = getProperties.getProperties();
		String employee = properties.getProperty("employee");
		List<ModelClass> list = getStatus(employee);
		return list;
	}

	/**
	 * 役割
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getRole", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getRole() {
		Properties properties = getProperties.getProperties();
		String role = properties.getProperty("role");
		List<ModelClass> list = getStatus(role);
		return list;
	}

	/**
	 * 新人
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getNewMember", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getNewMember() {
		Properties properties = getProperties.getProperties();
		String newMember = properties.getProperty("newMember");
		List<ModelClass> list = getStatus(newMember);
		return list;
	}

	/**
	 * 口座種類
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getAccountType", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getAccountType() {
		Properties properties = getProperties.getProperties();
		String accountType = properties.getProperty("accountType");
		List<ModelClass> list = getStatus(accountType);
		return list;
	}

	/**
	 * 口座所属
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getAccountBelongs", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getAccountBelongs() {
		Properties properties = getProperties.getProperties();
		String accountBelongs = properties.getProperty("accountBelongs");
		List<ModelClass> list = getStatus(accountBelongs);
		return list;
	}

	/**
	 * 資格
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getQualificationType", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getQualificationType() {
		Properties properties = getProperties.getProperties();
		String qualificationType = properties.getProperty("qualificationType");
		List<ModelClass> list = getStatus(qualificationType);
		return list;
	}

	/**
	 * 資格
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getBonus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getBonus() {
		Properties properties = getProperties.getProperties();
		String Bonus = properties.getProperty("Bonus");
		List<ModelClass> list = getStatus(Bonus);
		return list;
	}

	/**
	 * ボーナス
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getInsurance", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getInsurance() {
		Properties properties = getProperties.getProperties();
		String Insurance = properties.getProperty("Insurance");
		List<ModelClass> list = getStatus(Insurance);
		return list;
	}

	/**
	 * 社会保険
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getInsuranceStatus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getInsuranceStatus() {
		Properties properties = getProperties.getProperties();
		String InsuranceStatus = properties.getProperty("InsuranceStatus");
		List<ModelClass> list = getStatus(InsuranceStatus);
		return list;
	}

	public List<ModelClass> getStatus(String string) {
		JSONObject sJson = JSONObject.fromObject(string);
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) sJson;
		List<ModelClass> list = new ArrayList<ModelClass>();
		for (Entry<String, String> entry : map.entrySet()) {
			ModelClass statusModel = new ModelClass();
			statusModel.setCode(entry.getKey());
			statusModel.setName(entry.getValue());
			list.add(statusModel);
		}
		return list;
	}

	/**
	 * 入社区分を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getIntoCompany", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getIntoCompany() {
		List<ModelClass> list = getSelectInfoUtilService.getIntoCompany();
		return list;
	}

	/**
	 * 役割を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSiteMaster", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getSiteMaster() {
		List<ModelClass> list = getSelectInfoUtilService.getSiteMaster();
		return list;
	}

	/**
	 * 職種を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getOccupation", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getOccupation() {
		List<ModelClass> list = getSelectInfoUtilService.getOccupation();
		return list;
	}

	/**
	 * 部署を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDepartment", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getDepartment() {
		List<ModelClass> list = getSelectInfoUtilService.getDepartment();
		return list;
	}

	/**
	 * 権限を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAuthority", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getAuthority() {
		List<ModelClass> list = getSelectInfoUtilService.getAuthority();
		return list;
	}

	/**
	 * 英語を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getEnglishLevel", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getEnglishLevel() {
		List<ModelClass> list = getSelectInfoUtilService.getEnglishLevel();
		return list;
	}

	/**
	 * 資格を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getQualification", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getQualification() {
		List<ModelClass> list = getSelectInfoUtilService.getQualification();
		return list;
	}

	/**
	 * 条件を取得
	 * 
	 * @param emp
	 * @return
	 */
	public Map<String, String> getParam(EmployeeModel emp) {
		Map<String, String> sendMap = new HashMap<String, String>();
		String developmentLanguageNo1 = emp.getDevelopmentLanguageNo1();// 開発言語1
		if (developmentLanguageNo1 != null && developmentLanguageNo1.length() != 0) {
			sendMap.put("developmentLanguageNo1", developmentLanguageNo1);
		}
		return sendMap;

	}

	/**　
	 * 採番
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNO", method = RequestMethod.POST)
	@ResponseBody
	public String getNO(@RequestBody ModelClass mo) {
		Map<String, String> sendMap = new HashMap<String, String>();
		// sendMap.put("columnName", "customerNo");
		// sendMap.put("typeName", "C");
		// sendMap.put("table", "employee_site_information");
		String columnName = mo.getColumnName();// 列名は採番番号名です
		String typeName = mo.getTypeName();// 採番番号のタイプ
		String table = mo.getName();// テーブル
		if (columnName != null && columnName.length() != 0) {
			sendMap.put("columnName", columnName);
		}
		if (typeName != null && typeName.length() != 0) {
			sendMap.put("typeName", typeName);
		}
		if (table != null && table.length() != 0) {
			sendMap.put("table", table);
		}
		String no = getSelectInfoUtilService.getNO(sendMap);
		return no;
	}
}

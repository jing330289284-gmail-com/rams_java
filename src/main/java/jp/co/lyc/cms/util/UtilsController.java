package jp.co.lyc.cms.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.ModelClass;
import jp.co.lyc.cms.service.UtilsService;
import net.sf.json.JSONObject;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class UtilsController {

	@Autowired
	UtilsService utilsService;

	/**
	 * 国籍を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNationalitys", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getNationalitys() {
		List<ModelClass> list = utilsService.getNationalitys();
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
		List<ModelClass> list = utilsService.getStaffForms();
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
		List<ModelClass> list = utilsService.getVisa();
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
		List<ModelClass> list = utilsService.getTechnologyType(sendMap);
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
		List<ModelClass> list = utilsService.getLevel();
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
		List<ModelClass> list = utilsService.getJapaneseLevel();
		return list;
	}

	/**
	 * お客様性質を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCompanyNature", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getCompanyNature() {
		List<ModelClass> list = utilsService.getCompanyNature();
		return list;
	}

	/**
	 * 職位を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getPosition", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getPosition() {
		List<ModelClass> list = utilsService.getPosition();
		return list;
	}

	/**
	 * 上位お客様連想
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTopCustomerDrop", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getTopCustomerDrop() {
		List<ModelClass> list = utilsService.getTopCustomerDrop();
		return list;
	}

	/**
	 * 部門名前連想
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDepartmentMasterDrop", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getDepartmentMasterDrop() {
		List<ModelClass> list = utilsService.getDepartmentMasterDrop();
		return list;
	}

	/**
	 * 銀行名検索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getBankInfo() {
		List<ModelClass> list = utilsService.getBankInfo();
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
		List<ModelClass> list = utilsService.getBankBranchInfo(sendMap);
		return list;
	}

	/**
	 * 支払サイト検索
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getPaymentsite", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getPaymentsite() {
		List<ModelClass> list = utilsService.getPaymentsite();
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
		Properties properties = getProperties();
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
		Properties properties = getProperties();
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
		Properties properties = getProperties();
		String housing = properties.getProperty("housing");
		List<ModelClass> list = getStatus(housing);
		return list;
	}

	/**
	 * 精算時間
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getPayOffRange", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getPayOffRange() {
		Properties properties = getProperties();
		String payOffRange = properties.getProperty("payOffRange");
		List<ModelClass> list = getStatus(payOffRange);
		return list;
	}

	/**
	 * マスターを取得
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getMaster", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getMaster() {
		Properties properties = getProperties();
		String master = properties.getProperty("master");
		JSONObject sJson = JSONObject.fromObject(master);
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) sJson;
		List<ModelClass> list = new ArrayList<ModelClass>();
		for (Entry<String, String> entry : map.entrySet()) {
			ModelClass statusModel = new ModelClass();
			statusModel.setValue(entry.getKey());
			statusModel.setLabel(entry.getValue());
			list.add(statusModel);
		}
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
		Properties properties = getProperties();
		String employee = properties.getProperty("employee");
		List<ModelClass> list = getStatus(employee);
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
		Properties properties = getProperties();
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
		Properties properties = getProperties();
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
		Properties properties = getProperties();
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
		Properties properties = getProperties();
		String qualificationType = properties.getProperty("qualificationType");
		List<ModelClass> list = getStatus(qualificationType);
		return list;
	}

	/**
	 * ボーナス
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getBonus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getBonus() {
		Properties properties = getProperties();
		String bonus = properties.getProperty("bonus");
		List<ModelClass> list = getStatus(bonus);
		return list;
	}

	/**
	 * 社会保険
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getInsurance", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getInsurance() {
		Properties properties = getProperties();
		String insurance = properties.getProperty("insurance");
		List<ModelClass> list = getStatus(insurance);
		return list;
	}

	/**
	 * 住宅ステータス
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getHousingStatus", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getHousingStatus() {
		Properties properties = getProperties();
		String HousingStatus = properties.getProperty("housingStatus");
		List<ModelClass> list = getStatus(HousingStatus);
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
		List<ModelClass> list = utilsService.getIntoCompany();
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
		List<ModelClass> list = utilsService.getSiteMaster();
		return list;
	}

	/**
	 * お客様を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCustomer", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getCustomer() {
		List<ModelClass> list = utilsService.getCustomer();
		return list;
	}

	/**
	 * トップお客様を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTopCustomer", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getTopCustomer() {
		List<ModelClass> list = utilsService.getTopCustomer();
		return list;
	}

	/**
	 * 開発言語を取得
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDevelopLanguage", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getDevelopLanguage() {
		List<ModelClass> list = utilsService.getDevelopLanguage();
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
		List<ModelClass> list = utilsService.getOccupation();
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
		List<ModelClass> list = utilsService.getDepartment();
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
		List<ModelClass> list = utilsService.getAuthority();
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
		List<ModelClass> list = utilsService.getEnglishLevel();
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
		List<ModelClass> list = utilsService.getQualification();
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
		String developmentLanguageNo1 = emp.getDevelopLanguage1();// 開発言語1
		if (developmentLanguageNo1 != null && developmentLanguageNo1.length() != 0) {
			sendMap.put("developmentLanguageNo1", developmentLanguageNo1);
		}
		return sendMap;
	}

	/**
	 * パスワードを取得
	 * 
	 * @param emp
	 * @return
	 */
	@RequestMapping(value = "/getPassword", method = RequestMethod.POST)
	@ResponseBody
	public String getPassword(@RequestBody EmployeeModel emp) {
		return utilsService.getPassword(emp.getEmployeeNo());
	}

	/**
	 * パスワードリセット
	 * 
	 * @param emp
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean resetPassword(@RequestBody EmployeeModel emp) {
		HashMap<String, String> sendMap = new HashMap<>();
		sendMap.put("employeeNo", emp.getEmployeeNo());
		sendMap.put("password", emp.getPassword());
		sendMap.put("oldPassword", emp.getOldPassword());
		return utilsService.resetPassword(sendMap);
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
		String no = utilsService.getNO(sendMap);
		return no;
	}

	/**
	 * xmlを読み
	 * 
	 * @return
	 */
	public Properties getProperties() {
		Resource resource = new ClassPathResource("system.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

}

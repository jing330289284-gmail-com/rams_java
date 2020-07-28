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
@CrossOrigin(origins = "http://localhost:3000")
public class GetSelectInfoUtil {

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
	 * 性別区別
	 * 
	 * @return
	 */

	@RequestMapping(value = "/getGender", method = RequestMethod.POST)
	@ResponseBody
	public List<ModelClass> getGender() {
		Properties properties = getProperties.getProperties();
		String gender = properties.getProperty("gender");
		JSONObject genderJson = JSONObject.fromObject(gender);
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) genderJson;
		List<ModelClass> list = new ArrayList<ModelClass>();
		for (Entry<String, String> entry : map.entrySet()) {
			ModelClass intoCompanyModel = new ModelClass();
			intoCompanyModel.setCode(entry.getKey());
			intoCompanyModel.setName(entry.getValue());
			list.add(intoCompanyModel);
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

}

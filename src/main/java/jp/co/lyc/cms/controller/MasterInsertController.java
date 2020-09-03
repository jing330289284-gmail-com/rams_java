package jp.co.lyc.cms.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.MasterModel;
import jp.co.lyc.cms.service.MasterInsertService;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/masterInsert")
public class MasterInsertController extends BaseController {

	@Autowired
	MasterInsertService masterInsertService;

	/**
	 * 登録ボタン
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public boolean toroku(@RequestBody MasterModel masterModel) {
		boolean result = checkHave(masterModel);
		if (result) {
			return insert(masterModel);
		}
		return false;
	}

	/**
	 * インサート
	 * 
	 * @return
	 */
	public boolean insert(MasterModel masterModel) {
		HttpSession loginSession = getSession();
		HashMap<String, Object> sendMap = new HashMap<>();
		sendMap.put("master", masterModel.getMaster());
		sendMap.put("data", masterModel.getData());
		sendMap.put("columnName", masterModel.getMaster().substring(4) + "name");
		sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
		return masterInsertService.insertMaster(sendMap);

	}

	/**
	 * あるかどうかのチェック
	 * 
	 * @return
	 */
	public boolean checkHave(MasterModel masterModel) {
		masterModel.setColumnName(masterModel.getMaster().substring(4) + "name");
		return masterInsertService.checkHave(masterModel);

	}

	/**
	 * nullと空の判断
	 * 
	 * @return
	 */
	public boolean isNullOrEmpty(String aString) {
		boolean result = true;
		if (aString == null || aString.isEmpty()) {
			return result;
		} else {
			return result = false;
		}
	}
}

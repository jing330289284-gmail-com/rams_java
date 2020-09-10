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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.MasterModel;
import jp.co.lyc.cms.service.MasterUpdateService;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/masterUpdate")
public class MasterUpdateController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MasterUpdateService masterUpdateService;

	@RequestMapping(value = "/getMasterInfo", method = RequestMethod.POST)
	@ResponseBody
	// master信息查询
	public List<MasterModel> getMasterInfo(@RequestBody Map master) {
		List<MasterModel> masterList = new ArrayList<MasterModel>();

		try {
			HashMap<String, String> sendMap = new HashMap<>();
			sendMap.put("master", master.get("master").toString());
			sendMap.put("columnName", master.get("master").toString().substring(4) + "name");
			sendMap.put("columnCode", master.get("master").toString().substring(4) + "code");
			masterList = masterUpdateService.getMasterInfo(sendMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return masterList;
	}

	/**
	 * 修正ボタン
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toroku(@RequestBody MasterModel masterModel) {
		logger.info("MasterUpdateController.toroku:" + "追加開始");
		// チェック
		Map<String, Object> result = new HashMap<>();
		if (checkHave(masterModel) == false) {
			result.put("errorsMessage", StatusCodeToMsgMap.getErrMsgbyCode("MSG008"));// エラーメッセージ
			return result;
		}
		// 修正处理
		if (update(masterModel)) {
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		logger.info("MasterUpdateController.toroku:" + "追加結束");
		return result;
	}

	/**
	 * 削除ボタン
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public boolean delete(@RequestBody MasterModel masterModel) {
		HashMap<String, Object> sendMap = new HashMap<>();
		sendMap.put("master", masterModel.getMaster());
		sendMap.put("columnCode", masterModel.getMaster().toString().substring(4) + "code");
		sendMap.put("code", masterModel.getCode());
		return masterUpdateService.deleteMaster(sendMap);
	}

	/**
	 * アップデート
	 * 
	 * @return
	 */
	public boolean update(MasterModel masterModel) {
		HttpSession loginSession = getSession();
		HashMap<String, Object> sendMap = new HashMap<>();
		sendMap.put("master", masterModel.getMaster());
		sendMap.put("code", masterModel.getCode());
		sendMap.put("data", masterModel.getData());
		sendMap.put("columnName", masterModel.getMaster().substring(4) + "name");
		sendMap.put("columnCode", masterModel.getMaster().toString().substring(4) + "code");
		sendMap.put("updateUser", loginSession.getAttribute("employeeName"));
		return masterUpdateService.updateMaster(sendMap);

	}

	/**
	 * あるかどうかのチェック
	 * 
	 * @return
	 */
	public boolean checkHave(MasterModel masterModel) {
		masterModel.setColumnName(masterModel.getMaster().substring(4) + "name");
		return masterUpdateService.checkHave(masterModel);

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

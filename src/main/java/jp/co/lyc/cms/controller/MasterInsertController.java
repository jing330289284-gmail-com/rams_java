package jp.co.lyc.cms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.MasterModel;
import jp.co.lyc.cms.service.MasterInsertService;
import jp.co.lyc.cms.util.StatusCodeToMsgMap;

@Controller
@RequestMapping(value = "/masterInsert")
public class MasterInsertController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MasterInsertService masterInsertService;

	/**
	 * 登録ボタン
	 * 
	 * @return
	 */
	//エラーメッセージ
	String errorsMessage = "";

	@RequestMapping(value = "/toroku", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toroku(@RequestBody MasterModel masterModel) {
		logger.info("MasterInsertController.toroku:" + "追加開始");
		// チェック
		Map<String, Object> result = new HashMap<>();
		if (checkHave(masterModel) == false) {
			result.put("errorsMessage", StatusCodeToMsgMap.getErrMsgbyCode("MSG008"));// エラーメッセージ
			return result;
		}
		//登陆处理
		if (insert(masterModel)) {
			result.put("result", true);
		} else {
			result.put("result", false);
		}
		logger.info("MasterInsertController.toroku:" + "追加結束");
		return result;
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
}

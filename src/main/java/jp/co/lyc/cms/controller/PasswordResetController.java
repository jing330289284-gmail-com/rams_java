package jp.co.lyc.cms.controller;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.PasswordResetModel;
import jp.co.lyc.cms.service.PasswordResetService;

@Controller
@RequestMapping(value = "/passwordReset")
public class PasswordResetController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PasswordResetService passwordResetService;
	
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public boolean init(@RequestBody PasswordResetModel pswMod ) {
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    String passwordResetId = pswMod.getPasswordResetId();
		PasswordResetModel pswModel = passwordResetService.selectPasswordResetInfo(passwordResetId);
		if(pswModel == null) {
			return false;
		}
		Date createTime = pswModel.getIdCreateTime();
		Date nowTime = new Date();
		long diff = nowTime.getTime() - createTime.getTime();
		long hour = diff % nd / nh;
		if(hour > 24) {
			passwordResetService.delete(passwordResetId);
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "/passwordReset", method = RequestMethod.POST)
	@ResponseBody
	public String passwordReset(@RequestBody PasswordResetModel pswMod ) {
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    String passwordResetId = pswMod.getPasswordResetId();
		PasswordResetModel pswModel = passwordResetService.selectPasswordResetInfo(passwordResetId);
		if(pswModel == null) {
			return "2";//2はパスワードリセットIDが失効しました
		}
		HashMap<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("employeeNo", pswModel.getIdForEmployeeNo());
		sendMap.put("password", pswMod.getPassword());
		boolean result = passwordResetService.update(sendMap);
		if(result) {
			passwordResetService.delete(passwordResetId);
		}else {
			return "1";//1は失敗
		}
		return "0";//0は成功
	}
}
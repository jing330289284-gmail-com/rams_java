package jp.co.lyc.cms.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.util.UtilsCheckMethod;
@Controller
@RequestMapping(value = "/subMenu")
public class SubMenuController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 画面初期化
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeModel init() {
		logger.info("LoginController.login:" + "サブメニュー画面の初期化開始");
		if(UtilsCheckMethod.isNullOrEmpty((String)getSession().getAttribute("employeeNo"))) {
			logger.info("LoginController.login:" + "サブメニュー画面の初期化終了");
			return null;
		}else{
			EmployeeModel employeeModel = new EmployeeModel();
			employeeModel.setEmployeeName((String)getSession().getAttribute("employeeName"));
			employeeModel.setEmployeeNo((String)getSession().getAttribute("employeeNo"));
			employeeModel.setAuthorityCode((String)getSession().getAttribute("authorityCode"));
			employeeModel.setAuthorityName((String)getSession().getAttribute("authorityName"));
			logger.info("LoginController.login:" + "サブメニュー画面の初期化終了");
			return employeeModel;
		}
	}
	/**
	 * ログアウト
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public void logout() {
		logger.info("LoginController.login:" + "ログアウト開始");
		HttpSession session = getSession();
		session.invalidate();
		logger.info("LoginController.login:" + "ログアウト終了");
	}
}

package jp.co.lyc.cms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.LoginModel;
import jp.co.lyc.cms.service.EmployeeInfoService;
import jp.co.lyc.cms.service.LoginService;
import net.sf.json.JSONObject;

@Controller
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EmployeeInfoService es;
	@Autowired
	LoginService loginservice;
	/**
	 * ログインボタン
	 * @param loginModel
	 * @param employeeModel
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, EmployeeModel> login(@RequestBody LoginModel loginModel, EmployeeModel employeeModel) {
		logger.info("LoginController.login:" + "ログイン開始");
		Map<String, String> sendMap = new HashMap<String, String>();
		HashMap<String, EmployeeModel> resultMap = new HashMap<String, EmployeeModel>();
		sendMap.put("employeeNo", loginModel.employeeNo);
		sendMap.put("password", loginModel.password);
		employeeModel = loginservice.getEmployeeModel(sendMap);
		resultMap.put("employeeModel", employeeModel);
		logger.info("LoginController.login:" + "ログイン終了");
		return resultMap;
	}
}

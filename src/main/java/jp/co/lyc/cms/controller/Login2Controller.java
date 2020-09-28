/**
 * 
 */
package jp.co.lyc.cms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.mapper.PasswordResetMapper;
import jp.co.lyc.cms.model.EmailModel;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.Login2Model;
import jp.co.lyc.cms.service.Login2Service;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.util.UtilsController;
import jp.co.lyc.cms.validation.Login2Validation;

@Controller
@RequestMapping(value = "/login2")
public class Login2Controller extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	Login2Service es;

	@Autowired
	UtilsController utils;
	
	@Autowired
	PasswordResetMapper passwordResetMapper;
	
	String errorsMessage = "";
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public boolean init() {
		if(UtilsCheckMethod.isNullOrEmpty((String)getSession().getAttribute("employeeNo"))) {
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * ログインボタン
	 * @param loginModel
	 * @param employeeModel
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestBody Login2Model loginModel, EmployeeModel employeeModel ) {
		logger.info("LoginController.login:" + "ログイン開始");
		errorsMessage = "";
		DataBinder binder = new DataBinder(loginModel);
		binder.setValidator(new Login2Validation());
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
		HttpSession loginSession = getSession();
		Map<String, String> sendMap = new HashMap<String, String>();
		HashMap<String, EmployeeModel> resultMap = new HashMap<String, EmployeeModel>();
		sendMap.put("employeeNo", loginModel.employeeNo);
		sendMap.put("password", loginModel.password);
		employeeModel = es.getEmployeeModel(sendMap);
		resultMap.put("employeeModel", employeeModel);
		if(employeeModel != null) {
			loginSession.setAttribute("employeeNo", employeeModel.getEmployeeNo());
			loginSession.setAttribute("authorityName", employeeModel.getAuthorityName());
			loginSession.setAttribute("authorityCode", employeeModel.getAuthorityCode());
			loginSession.setAttribute("employeeName", employeeModel.getEmployeeFristName() +
					"" + employeeModel.getEmployeeLastName());
		}else {
			loginSession.invalidate();//重置session
		}
		logger.info("LoginController.login:" + "ログイン終了");
		return result;
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendMail(@RequestBody Login2Model loginModel) {
		logger.info("LoginController.login:" + "メール発送開始");
		errorsMessage = "";
		DataBinder binder = new DataBinder(loginModel);
		binder.setValidator(new Login2Validation());
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
		EmailModel emailModel = new EmailModel();
		String mail = es.getEmployeeCompanyMail(loginModel.getEmployeeNo());
		if(UtilsCheckMethod.isNullOrEmpty(mail)) {
			errorsMessage += "メール存在してないので、検索条件を確認してください。";
			result.put("errorsMessage", errorsMessage);
			return result;
		}
		passwordResetMapper.deleteAll(loginModel.getEmployeeNo());
		//受信人のメール
		emailModel.setToAddress(mail);
		emailModel.setUserName("mail@lyc.co.jp");
		emailModel.setPassword("Lyc2020-0908-");
		emailModel.setFromAddress("mail@lyc.co.jp");
		emailModel.setSubject("パスワードリセット");
		UUID uuid = UUID.randomUUID();
		String passwordResetId = uuid.toString();
		HashMap<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("passwordResetId", passwordResetId);
		sendMap.put("idForEmployeeNo", loginModel.getEmployeeNo());
		es.insert(sendMap);
		String context = loginModel.getEmployeeNo() + " さん<br/>" + 
				"お疲れ様でした！<br/>" +
				"自動設定メールになります、ご返事しないでください。<br/>"+
				"以下のリンクからパスワードの再設定を行って下さい。<br/>" +
				"<br/>"+
				"[パスワードを再設定する]<br/>" + 
				"http://127.0.0.1:3000/passwordReset?id=" + passwordResetId + "<br/>" +
				"※ご利用の方は上記URLを24時間内クリックしてお願いします。<br/>";
		emailModel.setContext(context);
		emailModel.setContextType("text/html;charset=utf-8");
		utils.EmailSend(emailModel);
		return result;
	}
}

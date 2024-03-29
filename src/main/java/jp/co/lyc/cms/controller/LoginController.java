package jp.co.lyc.cms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import jp.co.lyc.cms.common.BaseController;
import jp.co.lyc.cms.model.EmployeeModel;
import jp.co.lyc.cms.model.LoginModel;
import jp.co.lyc.cms.service.EmployeeInfoService;
import jp.co.lyc.cms.util.UtilsCheckMethod;
import jp.co.lyc.cms.validation.LoginValidation;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EmployeeInfoService es;

	String errorsMessage = "";

	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public boolean init() {
		if (UtilsCheckMethod.isNullOrEmpty((String) getSession().getAttribute("employeeNo"))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * ログインボタン
	 * 
	 * @param loginModel
	 * @param employeeModel
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestBody LoginModel loginModel, EmployeeModel employeeModel) {
		errorsMessage = "";
		DataBinder binder = new DataBinder(loginModel);
		binder.setValidator(new LoginValidation());
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
		logger.info("LoginController.login:" + "ログイン開始");
		HttpSession loginSession = getSession();
		Map<String, String> sendMap = new HashMap<String, String>();
		HashMap<String, EmployeeModel> resultMap = new HashMap<String, EmployeeModel>();
		sendMap.put("employeeNo", loginModel.employeeNo);
		sendMap.put("password", loginModel.password);
		employeeModel = es.getEmployeeModel(sendMap);
		/* (验证码部分 方便测试)
		 * if (!loginModel.getVerificationCode().equals(loginSession.getAttribute(
		 * "verificationCode"))) { errorsMessage +=
		 * "入力した社員番号やパスワードや認証番号が間違いため、ログインできません"; return result; }
		 */
		resultMap.put("employeeModel", employeeModel);
		if (employeeModel != null) {
			if(employeeModel.getAuthorityCode().equals("0") || employeeModel.getAuthorityCode().equals("1")) {
				loginSession.invalidate();// 重置session
				errorsMessage += "権限不足";
				return result;
			}
			loginSession.setAttribute("employeeNo", employeeModel.getEmployeeNo());
			loginSession.setAttribute("authorityName", employeeModel.getAuthorityName());
			loginSession.setAttribute("authorityCode", employeeModel.getAuthorityCode());
			loginSession.setAttribute("employeeName",
					employeeModel.getEmployeeFristName() + "" + employeeModel.getEmployeeLastName());
		} else {
			loginSession.invalidate();// 重置session
		}
		logger.info("LoginController.login:" + "ログイン終了");
		return result;
	}

	@RequestMapping(value = "/sendVerificationCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendVerificationCode(@RequestBody LoginModel loginModel) {
		// 发送短信
		errorsMessage = "";
		DataBinder binder = new DataBinder(loginModel);
		binder.setValidator(new LoginValidation());
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
		sendMap.put("employeeNo", loginModel.employeeNo);
		sendMap.put("password", loginModel.password);
		EmployeeModel employeeModel = es.getEmployeeModel(sendMap);
		String phoneNoInDB = "";
		if (employeeModel != null) {
			phoneNoInDB = es.getEmployeePhoneNo(loginModel.getEmployeeNo());
		} else {
			errorsMessage += "ユーザー名またはパースワード入力が間違いました。";
			result.put("errorsMessage", errorsMessage);
			return result;
		}
		AmazonSNSClient snsClient = new AmazonSNSClient();
		String message = "";// 短信内容
		String str = "0123456789";
		StringBuilder sb = new StringBuilder(4);
		for (int i = 0; i < 4; i++) {
			char ch = str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		String verificationCode = sb.toString();
		message = "認証番号は：" + verificationCode;
		System.out.println("验证码是：" + message);
		String phoneNumber = "+81" + phoneNoInDB;// 目标电话号码
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
//        sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);
		loginSession.setAttribute("verificationCode", verificationCode);
		result.put("verificationCode", verificationCode);
		return result;
	}

	/**
	 * 发送短信
	 * 
	 * @param snsClient
	 * @param message
	 * @param phoneNumber
	 * @param smsAttributes
	 */
	public static void sendSMSMessage(AmazonSNSClient snsClient, String message, String phoneNumber,
			Map<String, MessageAttributeValue> smsAttributes) {
		PublishResult result = snsClient.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber)
				.withMessageAttributes(smsAttributes));
		System.out.println(result); // Prints the message ID.
	}
}

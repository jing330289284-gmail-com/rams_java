package jp.co.lyc.cms.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EmployeeInfoService es;
	@Autowired
	UtilsCheckMethod utilsCheckMethod;
	
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	@ResponseBody
	public boolean init() {
		if(utilsCheckMethod.isNullOrEmpty((String)getRequest().getSession().getAttribute("employeeNo"))) {
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
	public HashMap<String, EmployeeModel> login(@RequestBody LoginModel loginModel, EmployeeModel employeeModel ) {
		HttpSession loginSession = getSession();
		logger.info("LoginController.login:" + "ログイン開始");
		Map<String, String> sendMap = new HashMap<String, String>();
		HashMap<String, EmployeeModel> resultMap = new HashMap<String, EmployeeModel>();
		sendMap.put("employeeNo", loginModel.employeeNo);
		sendMap.put("password", loginModel.password);
		employeeModel = es.getEmployeeModel(sendMap);
		if(!loginModel.getVerificationCode().equals( loginSession.getAttribute("verificationCode"))) {
			employeeModel = null;
			resultMap.put("employeeModel", employeeModel);
			return resultMap;
		}
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
		return resultMap;
	}
	@RequestMapping(value = "/sendVerificationCode", method = RequestMethod.POST)
	@ResponseBody
	public boolean sendVerificationCode(@RequestBody LoginModel loginModel) {
		//发送短信
		String phoneNoInDB = es.getEmployeePhoneNo(loginModel.getEmployeeNo());
		if(!loginModel.getPhoneNo().equals(phoneNoInDB)) {
			return false;
		}
		HttpSession loginSession = getSession();
        AmazonSNSClient snsClient = new AmazonSNSClient();
        String message = "";//短信内容
        double a = Math.random()*10000;
        int verificationCode = (int) a;
        message = "認証番号は：" + Integer.toString(verificationCode);
        System.out.println("验证码是：" + message);
        String phoneNumber = "+81" + loginModel.getPhoneNo();//目标电话号码
        Map<String, MessageAttributeValue> smsAttributes = 
                new HashMap<String, MessageAttributeValue>();
        sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);
        loginSession.setAttribute("verificationCode", Integer.toString(verificationCode));
        return true;
	}
	/**
	 * 发送短信
	 * @param snsClient
	 * @param message
	 * @param phoneNumber
	 * @param smsAttributes
	 */
	public static void sendSMSMessage(AmazonSNSClient snsClient, String message, 
			String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
	        PublishResult result = snsClient.publish(new PublishRequest()
	                        .withMessage(message)
	                        .withPhoneNumber(phoneNumber)
	                        .withMessageAttributes(smsAttributes));
	        System.out.println(result); // Prints the message ID.
	}
}

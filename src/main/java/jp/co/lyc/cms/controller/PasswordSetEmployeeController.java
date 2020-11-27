package jp.co.lyc.cms.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import jp.co.lyc.cms.mapper.PasswordSetEmployeeMapper;
import jp.co.lyc.cms.model.PasswordSetEmployeeModel;
import jp.co.lyc.cms.validation.PasswordSetEmployeeValidation;

@Controller
@RequestMapping(value = "/passwordSetEmployee")
public class PasswordSetEmployeeController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PasswordSetEmployeeMapper passwordSetEmployeeMapper;
	
	String errorsMessage = "";
	@RequestMapping(value = "/passwordReset", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> passwordReset(@RequestBody PasswordSetEmployeeModel passwordSetEmployeeModel ) {
		logger.info("PasswordSetEmployeeController.passwordReset:" + "パスワードリセット開始");
		errorsMessage = "";
		DataBinder binder = new DataBinder(passwordSetEmployeeModel);
		binder.setValidator(new PasswordSetEmployeeValidation());
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
		ArrayList<String> checkList = passwordSetEmployeeMapper.selectPassword(
				getMD5String(passwordSetEmployeeModel.getOldPassword()));
		if(checkList.size() == 0) {
			errorsMessage += "既存パスワードが間違いました！";
			result.put("errorMessage", errorsMessage);
			logger.info("PasswordSetEmployeeController.passwordReset:" + "パスワードリセット終了");
			return result;
		}
		HashMap<String, String> sendMap = new HashMap<String, String>();
		sendMap.put("employeeNo", (String)getSession().getAttribute("employeeNo"));
		sendMap.put("updateUser", (String)getSession().getAttribute("employeeName"));
		sendMap.put("password", passwordSetEmployeeModel.getNewPassword());
		passwordSetEmployeeMapper.update(sendMap);
		getSession().invalidate();
		result.put("message", "パスワードリセット成功しました！");
		logger.info("PasswordSetEmployeeController.passwordReset:" + "パスワードリセット終了");
		return result;
	}
	
	/**
	 * MD5暗号化
	 * @param str
	 * @return
	 */
	public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
}

package jp.co.lyc.cms.util;

import java.util.regex.Pattern;

/**
 * 公共方法（数据格式判断）
 * 
 * @author Vin.Young
 *
 */

public abstract class UtilsCheckMethod {

	/**
	 * 判断字符串是否为null或空
	 * 
	 * @param aString
	 * @return
	 */
	public static boolean isNullOrEmpty(String aString) {
		if (aString == null || aString.isEmpty()) {
			return true;
		} else {
			return  false;
		}
	}

	/**
	 * 電話番号をチェック
	 * 
	 * @param phoneNo
	 * @return
	 */
	public static boolean checkPhoneNo(String phoneNo) {
		String pattern = "^\\d{2,4}-\\d{2,4}-\\d{4}$";
		Pattern p = Pattern.compile(pattern);
		if (p.matcher(phoneNo).find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * メールをチェック
	 * 
	 * @param mail
	 * @return
	 */
	public static boolean checkMail(String mail) {
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		if (mail.matches(mailFormat)) {
			return true;
		}
		return false;
	}
}

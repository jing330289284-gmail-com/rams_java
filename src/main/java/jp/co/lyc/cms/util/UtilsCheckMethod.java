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
		String format = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		if (mail.matches(format)) {
			return true;
		}
		return false;
	}
	
	/**
	 * カタカナをチェック
	 * 
	 * @param katakana
	 * @return
	 */
	public static boolean checkKatakana(String katakana) {
		String format = "^[ｦ-ﾟ]*$";
		String format2 = "^[ァ-ヶー]*$";
		if (katakana.matches(format)||katakana.matches(format2)) {
			return true;
		}
		return false;
	}
	
	/**
	 * メールをチェック
	 * 
	 * @param URL
	 * @return
	 */
	public static boolean checkUrl(String URL) {
		String urlFormat = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\\\.)+[A-Za-z]{2,6}$";
		if (URL.matches(urlFormat)) {
			return true;
		}
		return false;
	}
	
	/**
	 * ローマ字をチェック
	 * 
	 * @param URL
	 * @return
	 */
	public static boolean alphabetFormat(String alphabet) {
		String alphabetFormat  = "^[A-Za-z]+$";
		if (alphabet.matches(alphabetFormat)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 数字をチェック
	 * 
	 * @param URL
	 * @return
	 */
	public static boolean numberFormat(String number) {
		String alphabetFormat  = "^[0-9]+$";
		if (number.matches(alphabetFormat)) {
			return true;
		}
		return false;
	}
}

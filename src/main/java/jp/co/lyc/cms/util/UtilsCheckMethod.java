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
		if (aString == null || aString.isEmpty()||aString.equals("")) {
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
		String pattern2 = "^\\d{2,4}\\d{2,4}\\d{4}$";
		Pattern p = Pattern.compile(pattern);
		Pattern p2 = Pattern.compile(pattern2);
		if (p.matcher(phoneNo).find()||p2.matcher(phoneNo).find()) {
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
		return Pattern.matches("^[ァ-ヶー]*$", katakana);
	}
	
	/**
	 * urlをチェック
	 * 
	 * @param URL
	 * @return
	 */
	public static boolean checkUrl(String URL) {
		URL = URL.toLowerCase();
		String urlFormat = "^((https|http|ftp|rtsp|mms)?://)"  //https、http、ftp、rtsp、mms
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
               + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184  
                 + "|" // 允许IP和DOMAIN（域名）
                 + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.  
                 + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名  
                + "[a-z]{2,6})" // first level domain- .com or .museum  
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name  
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";  
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
	 * 郵便番号をチェック
	 * 
	 * @param URL
	 * @return
	 */
	public static boolean postCodeFormat(String postCode) {
		String postCodeFormat  = "^[0-9]{7}$";
		if (postCode.matches(postCodeFormat)) {
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
	
	/**
	 * 数字、大小文字、記号いずれ存在チェック
	 * @param password
	 * @return
	 */
	public static boolean passwordCheck(String password) {
		String alphabetFormat  = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
		if (password.matches(alphabetFormat)) {
			return true;
		}
		return false;
	}
}

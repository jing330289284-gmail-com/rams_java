package jp.co.lyc.cms.util;


/**
 * 公共方法（数据格式判断）
 * @author Vin.Young
 *
 */

public class UtilsCheckMethod {
	// 判断字符串是否为null或空
	public static boolean isNullOrEmpty(String aString) {
		boolean result = true;
		if (aString == null || aString.isEmpty()) {
			return result;
		} else {
			return result = false;
		}
	}
}

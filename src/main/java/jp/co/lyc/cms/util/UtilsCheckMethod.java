package jp.co.lyc.cms.util;

import org.springframework.stereotype.Component;

/**
 * 公共方法（数据格式判断）
 * @author Vin.Young
 *
 */
@Component
public class UtilsCheckMethod {
	// 判断字符串是否为null或空
	public boolean isNullOrEmpty(String aString) {
		boolean result = true;
		if (aString == null || aString.isEmpty()) {
			return result;
		} else {
			return result = false;
		}
	}
}

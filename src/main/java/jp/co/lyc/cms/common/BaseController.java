package jp.co.lyc.cms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {

	public HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
			session.setMaxInactiveInterval(60 * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	public HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		return request;
	}
}

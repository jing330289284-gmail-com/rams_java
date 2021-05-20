package jp.co.lyc.cms;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 //拦截器
public class Myfilter implements Filter{

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	 
	    }
	 
	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        HttpServletRequest  request = (HttpServletRequest)servletRequest;
	        HttpServletResponse response  = (HttpServletResponse) servletResponse;
	        String requestURI = request.getRequestURI();
	        System.out.println(requestURI);
			response.setCharacterEncoding("UTF-8");
		    response.setContentType("application/json; charset=utf-8");
		    response.addHeader("Access-Control-Allow-Origin","http://127.0.0.1:3000");// * 允许所有域名跨域访问
	        //response.addHeader("Access-Control-Allow-Origin","http://13.58.173.66:3000");// * 允许所有域名跨域访问
		    //response.addHeader("Access-Control-Allow-Origin","http://54.201.204.105:3000");// * 允许所有域名跨域访问
		    response.setHeader("Access-Control-Allow-Credentials","true");
		    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		    response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
	        // 是否让请求通过过滤器。这行代码不能漏掉。如果漏掉请求会访问不到URL
	        if(!requestURI.contains("initializationPage") && 
	        		!requestURI.contains("login") &&
	        			!requestURI.contains("login2") &&
	        				!requestURI.contains("subMenuEmployee") &&
	        					!requestURI.contains("subMenu")) {
	        	if(request.getSession().getAttribute("employeeNo") == null) {
//	        	    response.setStatus(401);
	                request.getRequestDispatcher("/subMenu/logout").forward(request, response);
	        	    response.getWriter().append("server error");
	        		return;
	        	}
	        }
	        filterChain.doFilter(request,response);
	    }
	 
	    @Override
	    public void destroy() {
	 
	    }
}

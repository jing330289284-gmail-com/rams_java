package jp.co.lyc.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class ApplicationMain {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}
	@Bean
	public FilterRegistrationBean registFilter(){
		FilterRegistrationBean bean = new FilterRegistrationBean();
		// 定义filter的过滤路径规则。
		bean.addUrlPatterns("/*");
		bean.setFilter(new Myfilter());
        return bean;
	}
}

package jp.co.lyc.cms.util;

import java.io.IOException;

import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

@Component
public class GetProperties {

	/**
	 * xmlを読み
	 * 
	 * @return
	 */
	public Properties getProperties() {
		Resource resource = new ClassPathResource("system.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;

	}

}

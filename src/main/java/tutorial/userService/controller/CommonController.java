package tutorial.userService.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tutorial.userService.configMessageConverter.AppDetails;

@RestController
@RequestMapping("v1")
public class CommonController {

	@Value("${spring.application.name}")
	private String applicationName;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AppDetails appDetails;
	
	@GetMapping("app-info")
	public Map<String, String> appInfo() {
		Map<String, String> appInfo = new LinkedHashMap<String, String>();
		appInfo.put("name", env.getProperty("app.name"));
		appInfo.put("version", env.getProperty("app.version"));
		appInfo.put("description", env.getProperty("app.description"));
		return appInfo;
	}
	
	@GetMapping("random-values")
	public Map<String, String> appRandom() {
		Map<String, String> appRandom = new LinkedHashMap<String, String>();
		appRandom.put("random", env.getProperty("app.random"));
		appRandom.put("random.integer", env.getProperty("app.random.integer"));
		appRandom.put("random.uuid", env.getProperty("app.random.uuid"));
		appRandom.put("random.less.than.hundred", env.getProperty("app.random.less.than.hundred"));
		appRandom.put("random.within.range", env.getProperty("app.random.within.range"));
		return appRandom;
	}
	
	@GetMapping("app-name")
	public String appName() {
		//return applicationName;
		return env.getProperty("spring.application.name");
	}
	
	@GetMapping("app-details")
	public AppDetails appDetails() {
		return appDetails;
	}
	
}

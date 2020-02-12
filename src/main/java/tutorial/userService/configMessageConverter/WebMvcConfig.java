package tutorial.userService.configMessageConverter;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new UserMessageConverter());
	}

}

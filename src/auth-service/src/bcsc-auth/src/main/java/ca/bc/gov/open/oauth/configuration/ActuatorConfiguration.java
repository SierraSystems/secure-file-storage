package ca.bc.gov.open.oauth.configuration;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Actuator endpoint security configuration
 * 
 * @author sivakaruna
 *
 */
@Configuration
@Order(200)
public class ActuatorConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.requestMatcher(EndpointRequest.toAnyEndpoint())
				.authorizeRequests(requests -> requests.anyRequest().permitAll());
	}

}

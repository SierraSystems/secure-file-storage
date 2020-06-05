package ca.bc.gov.open.oauth.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ca.bc.gov.open.oauth.configuration.OauthProperties;
import ca.bc.gov.open.oauth.model.OIDCConfiguration;


/**
 * 
 * Service Returns a JAVA object which represents OpenID Connect configuration values from the provider's 
 * Well-Known Configuration Endpoint. 
 * 
 * @author shaunmillargov
 *
 */
@Service
@Configuration
@EnableConfigurationProperties(OauthProperties.class)
public class OIDCConfigurationService {
	
	private Logger logger = LoggerFactory.getLogger(OIDCConfigurationService.class);
	
	private OIDCConfiguration config = null; 
	
	@Autowired
	private OauthProperties oauthProps;
	
	/**
	 * 
	 * Loads the OIDC Well-known config endpoint if not already loaded. 
	 * 
	 * @return
	 */
    public OIDCConfiguration getConfig() {
        if ( null == config ) 
        	loadConfig();
        return config;
    }

    /**
     * 
     * Loads config object. 
     * 
     */
	private void loadConfig() {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		URI uri = null;
		try {
			uri = new URI(oauthProps.getWellKnown());
			config = restTemplate.getForObject(uri, OIDCConfiguration.class);
		} catch (URISyntaxException e2) {
			logger.error("Unable to load remote server well-known configuration endpoint. Check Oauth2 well-known endpoint configuration. ", e2);
		}
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory
				= new SimpleClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(oauthProps.getBcscTimeout());
		clientHttpRequestFactory.setReadTimeout(oauthProps.getBcscTimeout());
		return clientHttpRequestFactory;
	}
}


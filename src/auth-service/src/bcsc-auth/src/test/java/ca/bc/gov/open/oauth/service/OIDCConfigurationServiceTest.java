package ca.bc.gov.open.oauth.service;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.bc.gov.open.oauth.configuration.OauthProperties;
import ca.bc.gov.open.oauth.exception.OauthServiceException;

/**
 * Tests for oidc Configuration Service
 * 
 * @author sivakaruna
 *
 */
class OIDCConfigurationServiceTest {

	@InjectMocks
	OIDCConfigurationService oidcConfigurationService = new OIDCConfigurationService();

	@Mock
	OauthProperties oauthProperties;

	@BeforeEach
	void initialize() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(oauthProperties.getWellKnown()).thenReturn("${uri}");
		Mockito.when(oauthProperties.getBcscTimeout()).thenReturn(60000);
	}

	//Cannot test success without using the real well-known url
	@DisplayName("Failure - getConfig oidcConfiguration service")
	@Test
	void testGetConfig() throws OauthServiceException {
		Assertions.assertNull(oidcConfigurationService.getConfig());
	}
}

package ca.bc.gov.open.oauth.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for OIDCConfiguration request bean
 * 
 * @author sivakaruna
 *
 */
class OIDCConfigurationTest {

	@DisplayName("Success - OIDCConfiguration bean")
	@Test
	public void objectTest() {
		OIDCConfiguration oidcConfiguration = new OIDCConfiguration();
		oidcConfiguration.setAuthorizationEndpoint("authorizationEndpoint");
		oidcConfiguration.setClaimsParameterSupported(true);
		oidcConfiguration.setGrantTypesSupported(null);
		oidcConfiguration.setIdTokenSigningAlgValuesSupported(null);
		oidcConfiguration.setIntrospectionEndpoint("introspectionEndpoint");
		oidcConfiguration.setIssuer("issuer");
		oidcConfiguration.setJwksUri("jwksUri");
		oidcConfiguration.setRequestParameterSupported(true);
		oidcConfiguration.setRequestUriParameterSupported(true);
		oidcConfiguration.setResponseTypesSupported(null);
		oidcConfiguration.setRevocationEndpoint("revocationEndpoint");
		oidcConfiguration.setScopesSupported(null);
		oidcConfiguration.setSubjectTypesSupported(null);
		oidcConfiguration.setTokenEndpoint("tokenEndpoint");
		oidcConfiguration.setTokenEndpointAuthMethodsSupported(null);
		oidcConfiguration.setTokenEndpointAuthSigningAlgValuesSupported(null);
		oidcConfiguration.setUserinfoEndpoint("userinfoEndpoint");
		oidcConfiguration.setUserinfoSigningAlgValuesSupported(null);

		Assertions.assertEquals("authorizationEndpoint", oidcConfiguration.getAuthorizationEndpoint());
		Assertions.assertEquals(true, oidcConfiguration.getClaimsParameterSupported());
		Assertions.assertEquals(null, oidcConfiguration.getGrantTypesSupported());
		Assertions.assertEquals(null, oidcConfiguration.getIdTokenSigningAlgValuesSupported());
		Assertions.assertEquals("introspectionEndpoint", oidcConfiguration.getIntrospectionEndpoint());
		Assertions.assertEquals("issuer", oidcConfiguration.getIssuer());
		Assertions.assertEquals("jwksUri", oidcConfiguration.getJwksUri());
		Assertions.assertEquals(true, oidcConfiguration.getRequestParameterSupported());
		Assertions.assertEquals(true, oidcConfiguration.getRequestUriParameterSupported());
		Assertions.assertEquals(null, oidcConfiguration.getResponseTypesSupported());
		Assertions.assertEquals("revocationEndpoint", oidcConfiguration.getRevocationEndpoint());
		Assertions.assertEquals(null, oidcConfiguration.getScopesSupported());
		Assertions.assertEquals(null, oidcConfiguration.getSubjectTypesSupported());
		Assertions.assertEquals("tokenEndpoint", oidcConfiguration.getTokenEndpoint());
		Assertions.assertEquals(null, oidcConfiguration.getTokenEndpointAuthMethodsSupported());
		Assertions.assertEquals(null, oidcConfiguration.getTokenEndpointAuthSigningAlgValuesSupported());
		Assertions.assertEquals("userinfoEndpoint", oidcConfiguration.getUserinfoEndpoint());
		Assertions.assertEquals(null, oidcConfiguration.getUserinfoSigningAlgValuesSupported());
	}

}
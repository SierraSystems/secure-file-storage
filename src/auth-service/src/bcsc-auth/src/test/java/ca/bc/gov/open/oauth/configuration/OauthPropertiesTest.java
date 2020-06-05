package ca.bc.gov.open.oauth.configuration;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class OauthPropertiesTest {

	@Autowired
	OauthProperties oauthProperties;

	@DisplayName("Success - ecrcProperties loaded")
	@Test
	void test() {

		oauthProperties.setServerPort("8082");
		Assert.assertEquals("password", oauthProperties.getPassword());
		Assert.assertEquals("user", oauthProperties.getUsername());
		Assert.assertEquals("secret", oauthProperties.getJwtSecret());
		Assert.assertEquals("8082", oauthProperties.getServerPort());
		Assert.assertEquals("/authorize", oauthProperties.getAuthorizePath());
		Assert.assertEquals(60000, oauthProperties.getBcscTimeout());
		Assert.assertEquals("clientId", oauthProperties.getClientId());
		Assert.assertEquals("idp", oauthProperties.getIdp());
		Assert.assertEquals("authorized", oauthProperties.getJwtAuthorizedRole());
		Assert.assertEquals(3000, oauthProperties.getJwtExpiry());
		Assert.assertEquals("secret", oauthProperties.getPerSecret());
		Assert.assertEquals("return", oauthProperties.getReturnUri());
		Assert.assertEquals("scope", oauthProperties.getScope());
		Assert.assertEquals("secret", oauthProperties.getSecret());
		Assert.assertEquals("/token", oauthProperties.getTokenPath());
		Assert.assertEquals("/userInfo", oauthProperties.getUserinfoPath());
		Assert.assertEquals("/wellKnown", oauthProperties.getWellKnown());
	}
}

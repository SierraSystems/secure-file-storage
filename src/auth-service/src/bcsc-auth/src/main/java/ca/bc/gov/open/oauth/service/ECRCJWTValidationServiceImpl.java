package ca.bc.gov.open.oauth.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import ca.bc.gov.open.oauth.configuration.OauthProperties;
import ca.bc.gov.open.oauth.model.ValidationResponse;

/**
 * 
 * BCSC JWT Token Validation Services.  
 * 
 * These services are intended for validation of JWT token(s) received from BCSC.
 * 
 * @author shaunmillargov
 * 
 */
@Service
@Configuration
@EnableConfigurationProperties(OauthProperties.class)
public class ECRCJWTValidationServiceImpl implements ECRCJWTValidationService {
	
	private static final String[] BCSC_ACCESS_TOKEN_CLAIMS =  {"aud", "iss", "exp", "iat", "jti"}; 
	private static final String[] BCSC_ID_TOKEN_CLAIMS =  {"sub", "aud", "acr", "kid", "iss", "exp", "iat", "jti"}; 
	
	@Autowired
	private OauthProperties oauthProps;

	@Autowired
	private OIDCConfigurationService oidcConfigService;
	
	private Logger logger = LoggerFactory.getLogger(ECRCJWTValidationServiceImpl.class);
	
	/**
	 * Validate BCSC Access Token 
	 */
	@Override
	public ValidationResponse validateBCSCAccessToken(String token) { 
		logger.debug("validateBCSCAccessToken called.");
		return validateBCSCToken(token, BCSC_ACCESS_TOKEN_CLAIMS);
	}
	
	/**
	 * Validate BCSC ID Token 
	 */
	@Override
	public ValidationResponse validateBCSCIDToken(String token) {
		logger.debug("validateBCSCIDToken called.");
		return validateBCSCToken(token, BCSC_ID_TOKEN_CLAIMS);
	}
	
   /**
	* validateBCSCToken Performs the following checks: 
	* 
	*   - Algorithm check -- The JWS algorithm specified in the JWT header is
	*     checked whether it matches the agreed / expected one. Signature check
	*   - The digital signature is verified by trying an appropriate public
	*     key from the server JWK set of the remote server. 
	*   - JWT claims check -- The JWT claims set is validated, for example to ensure the token is
	*     not expired and matches the expected issuer, audience and other
	*     claims. 
	*     
	*     Applicable to both access_token and id_token types. 
	*   
	**/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ValidationResponse validateBCSCToken(String token, String[] claims) {

		ValidationResponse val = new ValidationResponse(true, "Successful JWT validation");

		// Create a JWT processor for the access tokens
		ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();

		// Fetch the public RSA keys from the IdP server to validate the signatures
		// Keys are sourced from the IdP server's JWK set, published at a well-known URL.
		// The RemoteJWKSet object caches the retrieved keys to speed up subsequent look-ups and can
		// also handle key-rollovers.
		JWKSource<SecurityContext> keySource = null;
		try {
			keySource = new RemoteJWKSet<>(new URL(oidcConfigService.getConfig().getJwksUri()));
		} catch (MalformedURLException e1) {
			logger.error("Unable to instantiate Remote JWK set from remote server.", e1);
			val.setMessage(e1.getMessage());
		}

		// The expected JWS algorithm of the id_Token received from BCSC
		JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;

		// Configure the JWT processor with a key selector to feed matching public
		// RSA keys sourced from the JWK set URL
		JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(expectedJWSAlg, keySource);

		jwtProcessor.setJWSKeySelector(keySelector);

		// Set the required JWT claims for access tokens issued by the IdP (BCSC) server.
		// This set MUST match those found for the ID_TOKEN rec'd back from BCSC.
		jwtProcessor.setJWTClaimsSetVerifier(new DefaultJWTClaimsVerifier(
				// new JWTClaimsSet.Builder().issuer("https://idtest.gov.bc.ca/oauth2/").build(),
				new JWTClaimsSet.Builder().issuer(oauthProps.getIdp() + "/oauth2/").build(),
				new HashSet<>(Arrays.asList(claims))));

		// Process the token
		SecurityContext ctx = null; // optional context parameter, not required here
		try {
			jwtProcessor.process(token, ctx);
			logger.debug("Token Ok");
		} catch (ParseException | BadJOSEException | JOSEException e) {
			logger.error("Token FAILED validation.", e);
			val.setMessage(e.getMessage());
			val.setValid(false);
		}

		return val;
	}
}

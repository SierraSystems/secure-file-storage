package ca.bc.gov.open.oauth.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Convenience class for generating and Front End JWT tokens.  
 * 
 * @author shaunmillargov
 */
public class JwtTokenGenerator {
	private JwtTokenGenerator() { throw new IllegalStateException("Utility class"); }
	private static Logger logger = LoggerFactory.getLogger(JwtTokenGenerator.class);
	/**
	 * Generates a JWT token for Front end API access.
	 * 
	 * Note: Expiry time is in ms.
	 * 
	 * @param userInfo
	 * @param encryptedToken
	 * @param secret
	 * @param expiryTime
	 * @return
	 */
	public static String generateFEAccessToken(JSONObject userInfo, String encryptedToken, String secret, int expiryTime, String authority) {

		String token = null;
		try {
			// per == persisted IdP token
			token = Jwts.builder()
					.claim("userInfo", userInfo)
					.claim("per", encryptedToken)
					.claim("authorities", Arrays.asList(authority))
					.setExpiration(new Date(System.currentTimeMillis() + expiryTime))
					.signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8)).compact();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return token;

	}
}

package ca.bc.gov.open.oauth.exception;

/**
 * 
 * OauthServiceException class
 * 
 * Custom exception for ECRC Oauth API operation suite. 
 * 
 * @author shaunmillargov
 *
 */
public class OauthServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5873442413088571528L;
	
	public static final String OAUTH_FAILURE_RESPONSE = "BCSC login failed";

	public OauthServiceException(String message) {
		super(message);
	}

	public OauthServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}


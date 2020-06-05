package ca.bc.gov.open.oauth.service;

import java.net.URI;
import java.net.URISyntaxException;

import com.nimbusds.oauth2.sdk.AccessTokenResponse;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;

import ca.bc.gov.open.oauth.exception.OauthServiceException;
import net.minidev.json.JSONObject;

/**
 *
 * Interface for ECRC Oauth Services
 *
 * @author shaunmillargov
 *
 */
public interface OauthServices {

	public URI getIDPRedirect(String returnUrl) throws URISyntaxException;
	
	public AccessTokenResponse getToken(String code, String returnUrl) throws OauthServiceException;

	public JSONObject getUserInfo(BearerAccessToken accessToken) throws OauthServiceException;

}


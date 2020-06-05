package ca.bc.gov.open.oauth.service;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.nimbusds.oauth2.sdk.AccessTokenResponse;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.AuthorizationCodeGrant;
import com.nimbusds.oauth2.sdk.AuthorizationGrant;
import com.nimbusds.oauth2.sdk.AuthorizationRequest;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.TokenErrorResponse;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.openid.connect.sdk.UserInfoRequest;
import com.nimbusds.openid.connect.sdk.UserInfoResponse;

import ca.bc.gov.open.oauth.configuration.OauthProperties;
import ca.bc.gov.open.oauth.exception.OauthServiceException;


/**
 *
 * Oauth API Services Implementation class.
 *
 * @author shaunmillargov
 *
 */
@Service
@Configuration
@EnableConfigurationProperties(OauthProperties.class)
public class OauthServicesImpl implements OauthServices {

	@Autowired
	private OauthProperties oauthProps;

	private final Logger logger = LoggerFactory.getLogger(OauthServicesImpl.class);

	public URI getIDPRedirect(String returnUrl) throws URISyntaxException {
		
		logger.debug("Calling getIDPRedirect");
		
		// The authorisation endpoint of IDP the server
		URI authzEndpoint = new URI(oauthProps.getIdp() + oauthProps.getAuthorizePath());

		// The client identifier provisioned by the server
		ClientID clientID = new ClientID(oauthProps.getClientId());

		// The requested scope values for the token
		Scope scope = new Scope(oauthProps.getScope());

		// The client callback URI, typically pre-registered with the server
		URI callback = new URI((returnUrl != null) ? returnUrl : oauthProps.getReturnUri());

		// Generate random state string for pairing the response to the request
		State state = new State();

		// Build the request
		AuthorizationRequest request = new AuthorizationRequest.Builder(
		    new ResponseType(ResponseType.Value.CODE), clientID)
		    .scope(scope)
		    .state(state)
		    .redirectionURI(callback)
		    .endpointURI(authzEndpoint)
		    .build();

		// Use this URI to send the end-user's browser to the server
		return request.toURI();	
			
	}

	public AccessTokenResponse getToken(String authCode, String returnUrl) throws OauthServiceException {
		
		logger.debug("Calling getToken");
		
		AuthorizationCode code = new AuthorizationCode(authCode);
		try {
			
			URI callback = new URI((returnUrl != null) ? returnUrl : oauthProps.getReturnUri());
			
			// The credentials to authenticate the client at the token endpoint
			ClientID clientID = new ClientID(oauthProps.getClientId());
			Secret clientSecret = new Secret(oauthProps.getSecret());
			ClientAuthentication clientAuth = new ClientSecretBasic(clientID, clientSecret);
			
			AuthorizationGrant codeGrant = new AuthorizationCodeGrant(code, callback);
			
			// The IDP token endpoint
			URI tokenEndpoint = new URI(oauthProps.getIdp() + oauthProps.getTokenPath());
			
			//authorization_code == grant_type

			// Make the token request
			TokenRequest request = new TokenRequest(tokenEndpoint, clientAuth, codeGrant);
			
			TokenResponse response = TokenResponse.parse(request.toHTTPRequest().send());

			if (!response.indicatesSuccess()) {
			    TokenErrorResponse errorResponse = response.toErrorResponse();
				throw new OauthServiceException("Token Error Response from IdP server: " + errorResponse.toJSONObject().toJSONString());
			}

			// Respond with the complete token returned from the IdP.
			return response.toSuccessResponse();
			
		} catch (URISyntaxException e) {
			throw new OauthServiceException(e.getMessage(), e);
		} catch (ParseException e) {
			throw new OauthServiceException("Parse Exception", e);
		} catch (IOException e) {
			throw new OauthServiceException("Error comunicating with IdP server", e);
		}
		
	}

	public JSONObject getUserInfo(BearerAccessToken accessToken) throws OauthServiceException {
	
		try {

			// Build the IdP endpoint for user info data
			HTTPResponse httpResponse = new UserInfoRequest(new URI(oauthProps.getIdp() + oauthProps.getUserinfoPath()),
					accessToken).toHTTPRequest().send();

			// Parse the response
			UserInfoResponse userInfoResponse = UserInfoResponse.parse(httpResponse);

			// The request failed, e.g. due to invalid or expired token
			if (!userInfoResponse.indicatesSuccess()) {
				throw new OauthServiceException("Invalid response returned from server for userinfo request.");
			}

			// Extract the claims
			return userInfoResponse.toSuccessResponse().getUserInfoJWT().getJWTClaimsSet().toJSONObject();
		} catch (ParseException e) {
			throw new OauthServiceException("Error parsing userinfo data returned from server. ", e);
		} catch (Exception e) {
			throw new OauthServiceException(e.getMessage(), e);
		}

	}
}

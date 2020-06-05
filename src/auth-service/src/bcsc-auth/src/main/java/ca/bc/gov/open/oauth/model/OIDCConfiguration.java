package ca.bc.gov.open.oauth.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * OIDC Configuration - Used for serializing https://[myOauthServer]/.well-known/openid-configuration response. 
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "response_types_supported", "request_parameter_supported", "request_uri_parameter_supported",
		"claims_parameter_supported", "introspection_endpoint", "grant_types_supported", "revocation_endpoint",
		"scopes_supported", "issuer", "authorization_endpoint", "userinfo_endpoint",
		"token_endpoint_auth_signing_alg_values_supported", "userinfo_signing_alg_values_supported", "jwks_uri",
		"subject_types_supported", "id_token_signing_alg_values_supported", "token_endpoint_auth_methods_supported",
		"token_endpoint" })
public class OIDCConfiguration {

	@JsonProperty("response_types_supported")
	private List<String> responseTypesSupported = null;
	@JsonProperty("request_parameter_supported")
	private Boolean requestParameterSupported;
	@JsonProperty("request_uri_parameter_supported")
	private Boolean requestUriParameterSupported;
	@JsonProperty("claims_parameter_supported")
	private Boolean claimsParameterSupported;
	@JsonProperty("introspection_endpoint")
	private String introspectionEndpoint;
	@JsonProperty("grant_types_supported")
	private List<String> grantTypesSupported = null;
	@JsonProperty("revocation_endpoint")
	private String revocationEndpoint;
	@JsonProperty("scopes_supported")
	private List<String> scopesSupported = null;
	@JsonProperty("issuer")
	private String issuer;
	@JsonProperty("authorization_endpoint")
	private String authorizationEndpoint;
	@JsonProperty("userinfo_endpoint")
	private String userinfoEndpoint;
	@JsonProperty("token_endpoint_auth_signing_alg_values_supported")
	private List<String> tokenEndpointAuthSigningAlgValuesSupported = null;
	@JsonProperty("userinfo_signing_alg_values_supported")
	private List<String> userinfoSigningAlgValuesSupported = null;
	@JsonProperty("jwks_uri")
	private String jwksUri;
	@JsonProperty("subject_types_supported")
	private List<String> subjectTypesSupported = null;
	@JsonProperty("id_token_signing_alg_values_supported")
	private List<String> idTokenSigningAlgValuesSupported = null;
	@JsonProperty("token_endpoint_auth_methods_supported")
	private List<String> tokenEndpointAuthMethodsSupported = null;
	@JsonProperty("token_endpoint")
	private String tokenEndpoint;

	@JsonProperty("response_types_supported")
	public List<String> getResponseTypesSupported() {
		return responseTypesSupported;
	}

	@JsonProperty("response_types_supported")
	public void setResponseTypesSupported(List<String> responseTypesSupported) {
		this.responseTypesSupported = responseTypesSupported;
	}

	@JsonProperty("request_parameter_supported")
	public Boolean getRequestParameterSupported() {
		return requestParameterSupported;
	}

	@JsonProperty("request_parameter_supported")
	public void setRequestParameterSupported(Boolean requestParameterSupported) {
		this.requestParameterSupported = requestParameterSupported;
	}

	@JsonProperty("request_uri_parameter_supported")
	public Boolean getRequestUriParameterSupported() {
		return requestUriParameterSupported;
	}

	@JsonProperty("request_uri_parameter_supported")
	public void setRequestUriParameterSupported(Boolean requestUriParameterSupported) {
		this.requestUriParameterSupported = requestUriParameterSupported;
	}

	@JsonProperty("claims_parameter_supported")
	public Boolean getClaimsParameterSupported() {
		return claimsParameterSupported;
	}

	@JsonProperty("claims_parameter_supported")
	public void setClaimsParameterSupported(Boolean claimsParameterSupported) {
		this.claimsParameterSupported = claimsParameterSupported;
	}

	@JsonProperty("introspection_endpoint")
	public String getIntrospectionEndpoint() {
		return introspectionEndpoint;
	}

	@JsonProperty("introspection_endpoint")
	public void setIntrospectionEndpoint(String introspectionEndpoint) {
		this.introspectionEndpoint = introspectionEndpoint;
	}

	@JsonProperty("grant_types_supported")
	public List<String> getGrantTypesSupported() {
		return grantTypesSupported;
	}

	@JsonProperty("grant_types_supported")
	public void setGrantTypesSupported(List<String> grantTypesSupported) {
		this.grantTypesSupported = grantTypesSupported;
	}

	@JsonProperty("revocation_endpoint")
	public String getRevocationEndpoint() {
		return revocationEndpoint;
	}

	@JsonProperty("revocation_endpoint")
	public void setRevocationEndpoint(String revocationEndpoint) {
		this.revocationEndpoint = revocationEndpoint;
	}

	@JsonProperty("scopes_supported")
	public List<String> getScopesSupported() {
		return scopesSupported;
	}

	@JsonProperty("scopes_supported")
	public void setScopesSupported(List<String> scopesSupported) {
		this.scopesSupported = scopesSupported;
	}

	@JsonProperty("issuer")
	public String getIssuer() {
		return issuer;
	}

	@JsonProperty("issuer")
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	@JsonProperty("authorization_endpoint")
	public String getAuthorizationEndpoint() {
		return authorizationEndpoint;
	}

	@JsonProperty("authorization_endpoint")
	public void setAuthorizationEndpoint(String authorizationEndpoint) {
		this.authorizationEndpoint = authorizationEndpoint;
	}

	@JsonProperty("userinfo_endpoint")
	public String getUserinfoEndpoint() {
		return userinfoEndpoint;
	}

	@JsonProperty("userinfo_endpoint")
	public void setUserinfoEndpoint(String userinfoEndpoint) {
		this.userinfoEndpoint = userinfoEndpoint;
	}

	@JsonProperty("token_endpoint_auth_signing_alg_values_supported")
	public List<String> getTokenEndpointAuthSigningAlgValuesSupported() {
		return tokenEndpointAuthSigningAlgValuesSupported;
	}

	@JsonProperty("token_endpoint_auth_signing_alg_values_supported")
	public void setTokenEndpointAuthSigningAlgValuesSupported(List<String> tokenEndpointAuthSigningAlgValuesSupported) {
		this.tokenEndpointAuthSigningAlgValuesSupported = tokenEndpointAuthSigningAlgValuesSupported;
	}

	@JsonProperty("userinfo_signing_alg_values_supported")
	public List<String> getUserinfoSigningAlgValuesSupported() {
		return userinfoSigningAlgValuesSupported;
	}

	@JsonProperty("userinfo_signing_alg_values_supported")
	public void setUserinfoSigningAlgValuesSupported(List<String> userinfoSigningAlgValuesSupported) {
		this.userinfoSigningAlgValuesSupported = userinfoSigningAlgValuesSupported;
	}

	@JsonProperty("jwks_uri")
	public String getJwksUri() {
		return jwksUri;
	}

	@JsonProperty("jwks_uri")
	public void setJwksUri(String jwksUri) {
		this.jwksUri = jwksUri;
	}

	@JsonProperty("subject_types_supported")
	public List<String> getSubjectTypesSupported() {
		return subjectTypesSupported;
	}

	@JsonProperty("subject_types_supported")
	public void setSubjectTypesSupported(List<String> subjectTypesSupported) {
		this.subjectTypesSupported = subjectTypesSupported;
	}

	@JsonProperty("id_token_signing_alg_values_supported")
	public List<String> getIdTokenSigningAlgValuesSupported() {
		return idTokenSigningAlgValuesSupported;
	}

	@JsonProperty("id_token_signing_alg_values_supported")
	public void setIdTokenSigningAlgValuesSupported(List<String> idTokenSigningAlgValuesSupported) {
		this.idTokenSigningAlgValuesSupported = idTokenSigningAlgValuesSupported;
	}

	@JsonProperty("token_endpoint_auth_methods_supported")
	public List<String> getTokenEndpointAuthMethodsSupported() {
		return tokenEndpointAuthMethodsSupported;
	}

	@JsonProperty("token_endpoint_auth_methods_supported")
	public void setTokenEndpointAuthMethodsSupported(List<String> tokenEndpointAuthMethodsSupported) {
		this.tokenEndpointAuthMethodsSupported = tokenEndpointAuthMethodsSupported;
	}

	@JsonProperty("token_endpoint")
	public String getTokenEndpoint() {
		return tokenEndpoint;
	}

	@JsonProperty("token_endpoint")
	public void setTokenEndpoint(String tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
	}

}


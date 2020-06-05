package ca.bc.gov.open.oauth.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonProcessingException;

import ca.bc.gov.open.oauth.configuration.OauthProperties;
import ca.bc.gov.open.oauth.exception.OauthServiceException;
import ca.bc.gov.open.oauth.model.OIDCConfiguration;
import ca.bc.gov.open.oauth.model.ValidationResponse;
import ca.bc.gov.open.oauth.util.TestTokenGenerator;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * 
 * ECRC Token Validation Service tests.
 * 
 * @author shaunmillargov
 *
 */
public class EcrcJWTValidationServiceImplTest {

	private final String privateKey = "{\r\n"
			+ "    \"p\": \"1v8f0Gk5MODG9o0TqMBD6KBWQ7Z4NSKshHv7dQqGLoayP74qyVdE-PSOYkVTg6OkPMMNxXFEF9X28X7tthxWIpb2wV737CsODvf_UZKrtbDBrXuGpzAbSi5wgzf2mzn6PaKyzXO6ONRPspRWeua6T7H5-dj6yMq_nwHnlGQsn4c\",\r\n"
			+ "    \"kty\": \"RSA\",\r\n"
			+ "    \"q\": \"oym9xGBK9aaag86o1i9KmntEJahzbgt-SkdlO99_JZ9D6AKPquHCVdLAmDB4gLrLsNrAv0mGG_9699Hf76hcTqXmXErfJMw55K6wZ7NfoMAB_QGhVnnnNwHjj8jbdptOxzpJc0_Ky_ekF6KkBzXZzDFLSkg2IkYAJK9bcRFFAsU\",\r\n"
			+ "    \"d\": \"NpnYQPvrk_bpwZZq0pN3TtZ5I-H5ZT-bgouHMqJtdKRobk5JigmjYJmDvZtaS5sWPSNmWv22f-AnCCI6ux-swYCf2mY18dJRrI0DsDQHkwhyhBFvpCe7KUxohnJloILha43BjwBGGRDSKcboO6ggujzd7sPMY77qTa_c21hDXBQ6sU0pUVQKMm40eVZsDumOTs40bvyzsdBOvat2K8xfhuNI7dPrWUpk1rQFWgyI8tq47RIBsfDvQT-ED5qnaKF9tGcM56qZzGRhtBsb0rCkH1Sr7aQAoFNOp4eGFjytne2HOrom9xWUKhVSF_c0UpUKsZzaYAtpA136Yde70G3tMQ\",\r\n"
			+ "    \"e\": \"AQAB\",\r\n" + "    \"use\": \"sig\",\r\n" + "    \"kid\": \"1\",\r\n"
			+ "    \"qi\": \"qU2XyBJdhrxh0gMgWos7SzmH8xZJ0XR94JxfQHOfhETJZDc5o-X5UoXUGbWazTJFiOoiAfWjUGc2q1ZMYvb1qWkRvG5ZCbT054ffqE6NE1QnDA6q1JeEwBGHaxpR5myke11EDUihBE0yEkOzfF1wP9yGFb7lc8sQCWs9EzXIf3w\",\r\n"
			+ "    \"dp\": \"FqzRrilq3rzWPyqh6Cpe_ynp7zpPc0s05sQswSta-405P3_PA20w2RDx82tcY4XezInF6g0IIXtvkAjTCWHzs7I4VCyv-TiRQwLk6rWNZpw90Q42JWvtdOVw-GXunHCA8_803HZYLaY2wZ7jgBLVk5IxyygXSxYXH64TvpLSIgE\",\r\n"
			+ "    \"alg\": \"RS256\",\r\n"
			+ "    \"dq\": \"iwdPRtySIt1SfguC-_aSCC4OtFPj_5nVg69wSGM5cTJTZ4d_KalysTSCnWt30qDYXZsYcCZTGtpHypDP0uNPvEhKkMvPdgh7MCvV7pb-Xfgd-ZsBAbGf3dRDNkp9DoRpzQMSgSo1cDVUYjtYrzgonIBIWkRLtEqXZqrxLNf9SM0\",\r\n"
			+ "    \"n\": \"iQd_fRIQHYMp51ZAl9CSNRyG6ithuuC8PfJU9LP8sm-cHNkUc92LwZ8R0ETvWBDNFaRQ1z00vK2fgBnAaQj_hXlRVk7IGkZryNqm-sMoyqa_XOn6rsPIP-mWlcgpb-X7UX7YJdGpUFsVYHQ_AC4UI6jzE2hbNEiGOx1gKU1Li-6kn1Nc0Ny_NUD1vI-reY_beiZkt1YqVPfbMYItWSysCUkRlsdx5scFEcglZhJQydZoi9E2SL71zRSjIstTBceAuch7s9WhODMNZiZ6iAYwbGtkFeiWKlosmHqi2kMtXcu56SKenKA-nMtD7IbBVwnWgeGlbNSLI6PDnLwvZfjQ4w\"\r\n"
			+ "}";

	private final String jwksResponse = "{\"keys\":[{\"p\":\"1v8f0Gk5MODG9o0TqMBD6KBWQ7Z4NSKshHv7dQqGLoayP74qyVdE-PSOYkVTg6OkPMMNxXFEF9X28X7tthxWIpb2wV737CsODvf_UZKrtbDBrXuGpzAbSi5wgzf2mzn6PaKyzXO6ONRPspRWeua6T7H5-dj6yMq_nwHnlGQsn4c\",\"kty\":\"RSA\",\"q\":\"oym9xGBK9aaag86o1i9KmntEJahzbgt-SkdlO99_JZ9D6AKPquHCVdLAmDB4gLrLsNrAv0mGG_9699Hf76hcTqXmXErfJMw55K6wZ7NfoMAB_QGhVnnnNwHjj8jbdptOxzpJc0_Ky_ekF6KkBzXZzDFLSkg2IkYAJK9bcRFFAsU\",\"d\":\"NpnYQPvrk_bpwZZq0pN3TtZ5I-H5ZT-bgouHMqJtdKRobk5JigmjYJmDvZtaS5sWPSNmWv22f-AnCCI6ux-swYCf2mY18dJRrI0DsDQHkwhyhBFvpCe7KUxohnJloILha43BjwBGGRDSKcboO6ggujzd7sPMY77qTa_c21hDXBQ6sU0pUVQKMm40eVZsDumOTs40bvyzsdBOvat2K8xfhuNI7dPrWUpk1rQFWgyI8tq47RIBsfDvQT-ED5qnaKF9tGcM56qZzGRhtBsb0rCkH1Sr7aQAoFNOp4eGFjytne2HOrom9xWUKhVSF_c0UpUKsZzaYAtpA136Yde70G3tMQ\",\"e\":\"AQAB\",\"use\":\"sig\",\"kid\":\"1\",\"qi\":\"qU2XyBJdhrxh0gMgWos7SzmH8xZJ0XR94JxfQHOfhETJZDc5o-X5UoXUGbWazTJFiOoiAfWjUGc2q1ZMYvb1qWkRvG5ZCbT054ffqE6NE1QnDA6q1JeEwBGHaxpR5myke11EDUihBE0yEkOzfF1wP9yGFb7lc8sQCWs9EzXIf3w\",\"dp\":\"FqzRrilq3rzWPyqh6Cpe_ynp7zpPc0s05sQswSta-405P3_PA20w2RDx82tcY4XezInF6g0IIXtvkAjTCWHzs7I4VCyv-TiRQwLk6rWNZpw90Q42JWvtdOVw-GXunHCA8_803HZYLaY2wZ7jgBLVk5IxyygXSxYXH64TvpLSIgE\",\"alg\":\"RS256\",\"dq\":\"iwdPRtySIt1SfguC-_aSCC4OtFPj_5nVg69wSGM5cTJTZ4d_KalysTSCnWt30qDYXZsYcCZTGtpHypDP0uNPvEhKkMvPdgh7MCvV7pb-Xfgd-ZsBAbGf3dRDNkp9DoRpzQMSgSo1cDVUYjtYrzgonIBIWkRLtEqXZqrxLNf9SM0\",\"n\":\"iQd_fRIQHYMp51ZAl9CSNRyG6ithuuC8PfJU9LP8sm-cHNkUc92LwZ8R0ETvWBDNFaRQ1z00vK2fgBnAaQj_hXlRVk7IGkZryNqm-sMoyqa_XOn6rsPIP-mWlcgpb-X7UX7YJdGpUFsVYHQ_AC4UI6jzE2hbNEiGOx1gKU1Li-6kn1Nc0Ny_NUD1vI-reY_beiZkt1YqVPfbMYItWSysCUkRlsdx5scFEcglZhJQydZoi9E2SL71zRSjIstTBceAuch7s9WhODMNZiZ6iAYwbGtkFeiWKlosmHqi2kMtXcu56SKenKA-nMtD7IbBVwnWgeGlbNSLI6PDnLwvZfjQ4w\"}]}";

	@InjectMocks
	ECRCJWTValidationServiceImpl tokenServices;

	@Mock
	OIDCConfigurationService oidcConfigService;

	public static MockWebServer mockBackEnd;

	@Mock
	OauthProperties oauthProperties;

	@BeforeAll
	static void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@BeforeEach
	void initialize() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		MockitoAnnotations.initMocks(this);
		String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
		Mockito.when(oauthProperties.getIdp()).thenReturn(baseUrl);
		String jwkEndpoint = baseUrl + "/oauth2/jwk";
		OIDCConfiguration oidcConfig = new OIDCConfiguration();
		oidcConfig.setJwksUri(jwkEndpoint);
		Mockito.when(oidcConfigService.getConfig()).thenReturn(oidcConfig);
	}

	@DisplayName("Good test - validateBCSCAccessToken")
	@Test
	public void testAccessToken() throws JsonProcessingException, OauthServiceException {
		MockResponse mockResponse = new MockResponse();
		mockResponse.setBody(jwksResponse);
		mockResponse.addHeader("content-type: application/json;charset=ISO-8859-1");
		mockResponse.setResponseCode(200);

		// Two calls are made to the JWKS endpoint and why we queue up two responses.
		mockBackEnd.enqueue(mockResponse);
		mockBackEnd.enqueue(mockResponse);

		// Calculate expiry for 1 year in the future.
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.YEAR, 1);

		// Generate a JWT based on the same original private key.
		String accessToken = TestTokenGenerator.generateBCSCAccessToken(privateKey,
				oauthProperties.getIdp() + "/oauth2/", exp.getTime());

		System.out.println(accessToken);

		// Uses the JWKS public key to validate the above.
		ValidationResponse resp = tokenServices.validateBCSCAccessToken(accessToken);

		Assertions.assertEquals(true, resp.isValid());
	}

	@DisplayName("Error test - validateBCSCAccessToken (expired)")
	@Test
	public void testAccessTokenExpired() throws JsonProcessingException, OauthServiceException {
		MockResponse mockResponse = new MockResponse();
		mockResponse.setBody(jwksResponse);
		mockResponse.addHeader("content-type: application/json;charset=ISO-8859-1");
		mockResponse.setResponseCode(200);

		// Two calls are made to the JWKS endpoint and why we queue up two responses.
		mockBackEnd.enqueue(mockResponse);
		mockBackEnd.enqueue(mockResponse);

		// Calculate expiry for 1 day prior to today.
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.DAY_OF_WEEK, -1);

		// Generate a JWT based on the same original private key.
		String accessToken = TestTokenGenerator.generateBCSCAccessToken(privateKey,
				oauthProperties.getIdp() + "/oauth2/", exp.getTime());

		System.out.println(accessToken);

		// Uses the JWKS public key to validate the above.
		ValidationResponse resp = tokenServices.validateBCSCAccessToken(accessToken);

		Assertions.assertEquals(false, resp.isValid());
	}

	@DisplayName("Good test - validateBCSCIDToken")
	@Test
	public void testIDToken() throws JsonProcessingException, OauthServiceException {
		MockResponse mockResponse = new MockResponse();
		mockResponse.setBody(jwksResponse);
		mockResponse.addHeader("content-type: application/json;charset=ISO-8859-1");
		mockResponse.setResponseCode(200);

		// Two calls are made to the JWKS endpoint and why we queue up two responses.
		mockBackEnd.enqueue(mockResponse);
		mockBackEnd.enqueue(mockResponse);

		// Calculate expiry for 1 year in the future.
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.YEAR, 1);

		// Generate a JWT based on the same original private key.
		String idToken = TestTokenGenerator.generateBCSCIDToken(privateKey, oauthProperties.getIdp() + "/oauth2/",
				exp.getTime());

		System.out.println(idToken);

		// Uses the JWKS public key to validate the above.
		ValidationResponse resp = tokenServices.validateBCSCIDToken(idToken);

		Assertions.assertEquals(true, resp.isValid());
	}

	@DisplayName("Error test - validateBCSCIDToken (Expired)")
	@Test
	public void testIDTokenExpired() throws JsonProcessingException, OauthServiceException {
		MockResponse mockResponse = new MockResponse();
		mockResponse.setBody(jwksResponse);
		mockResponse.addHeader("content-type: application/json;charset=ISO-8859-1");
		mockResponse.setResponseCode(200);

		// Two calls are made to the JWKS endpoint and why we queue up two responses.
		mockBackEnd.enqueue(mockResponse);
		mockBackEnd.enqueue(mockResponse);

		// Calculate expiry for 1 day prior to today.
		Calendar exp = Calendar.getInstance();
		exp.add(Calendar.DAY_OF_WEEK, -1);

		// Generate a JWT based on the same original private key.
		String idToken = TestTokenGenerator.generateBCSCIDToken(privateKey, oauthProperties.getIdp() + "/oauth2/",
				exp.getTime());

		System.out.println(idToken);

		// Uses the JWKS public key to validate the above.
		ValidationResponse resp = tokenServices.validateBCSCIDToken(idToken);

		Assertions.assertEquals(false, resp.isValid());
	}

}

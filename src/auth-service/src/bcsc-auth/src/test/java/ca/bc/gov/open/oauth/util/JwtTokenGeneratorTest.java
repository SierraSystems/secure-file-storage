package ca.bc.gov.open.oauth.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.minidev.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for JwtTokenGenerator
 * 
 * @author 196916
 *
 */
class JwtTokenGeneratorTest {

	@DisplayName("Success - generateFEAccessToken JwtTokenGenerator")
	@Test
	void testSuccess() {

		String result = JwtTokenGenerator.generateFEAccessToken(new JSONObject(), "abc", "secret", 0, "role");
		Assertions.assertTrue(result.length() > 0);
	}

	@DisplayName("Exception - generateFEAccessToken JwtTokenGenerator")
	@Test
	void testException() {

		String result = JwtTokenGenerator.generateFEAccessToken(null, null, null, -1, null);
		Assertions.assertTrue(result == null);
	}
	@DisplayName("Exception - test private constructor throws exception")
	@Test
	public void testPrivateConstructor() throws Exception {
		assertThrows(
				InvocationTargetException.class,
				() -> {
					Constructor<JwtTokenGenerator> constructor =  JwtTokenGenerator.class.getDeclaredConstructor();
					constructor.setAccessible(true);
					constructor.newInstance((Object[]) null);
				}
		);
	}
}

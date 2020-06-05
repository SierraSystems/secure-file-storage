package ca.bc.gov.open.oauth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "valid", "message" })
public class ValidationResponse {

	@JsonProperty("valid")
	private boolean valid;

	@JsonProperty("message")
	private String message;
	
	public ValidationResponse(boolean valid, String message) {
		this.valid = valid; 
		this.message = message; 
	}

	@JsonProperty("valid")
	public boolean isValid() {
		return valid;
	}

	@JsonProperty("valid")
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

}

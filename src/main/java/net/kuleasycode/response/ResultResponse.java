package net.kuleasycode.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse<T> {

	@JsonProperty("response_code")
	private String responseCode;
	
	@JsonProperty("response_message")
	private String responseMessage;
	
	@JsonProperty("data")
	private T data;

	public ResultResponse(String responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
}

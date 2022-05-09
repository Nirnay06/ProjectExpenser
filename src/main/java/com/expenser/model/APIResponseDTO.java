package com.expenser.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponseDTO {
	private String message;
	private boolean isSuccess;
	public APIResponseDTO(String message, boolean isSuccess) {
		this.message = message;
		this.isSuccess = isSuccess;
	}
}

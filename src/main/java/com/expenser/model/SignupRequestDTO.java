package com.expenser.model;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
	
	@NotNull
	@Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "Invalid username entered")
	private String username;
	
	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Pasword should have minimum 8 characters, at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character")
	private String password;
	
	@NotBlank
	@Pattern(regexp = "^.([A-Za-z0-9])+$", message="Name should be alphanumeric")
	private String firstname;
	
	@Pattern(regexp = "^.([A-Za-z0-9])*$", message="Name should be alphanumeric")
	private String lastname;
	
	public boolean isValidUsername() {
		if(this.username !=null && java.util.regex.Pattern.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", this.username)) {
			return true;
		}
		return false;
	}
}

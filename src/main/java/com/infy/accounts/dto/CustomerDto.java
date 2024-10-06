package com.infy.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDto {

	@NotEmpty(message = "Name can not be null or empty")
	@Size(min = 3, max = 30, message = "The length of name should be between 3 and 30")
	private String name;

	@NotEmpty(message = "Email should  not be empty")
	@Email(message = "Email address should be valid value")
	private String email;

	@Pattern(regexp = "(^[6-9]\\d{9}$)", message = "Mobile number should be of 10 digits")
	private String mobileNumber;

	private AccountsDto accountsDto;

	public CustomerDto() {
		super();
	}

	public CustomerDto(String name, String email, String mobileNumber, AccountsDto accountsDto) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.accountsDto = accountsDto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public AccountsDto getAccountsDto() {
		return accountsDto;
	}

	public void setAccountsDto(AccountsDto accountsDto) {
		this.accountsDto = accountsDto;
	}

	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + ", accountsDto="
				+ accountsDto + "]";
	}

}

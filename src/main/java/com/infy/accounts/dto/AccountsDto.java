package com.infy.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class AccountsDto {

	@NotEmpty(message = "Account Number can not be null or empty")
	@Pattern(regexp = "(^[0-9]{10}$)", message = "Account number should be of 10 digits")
	private Long accountNumber;

	@NotEmpty(message = "Account type can not be null or empty")
	private String accountType;

	@NotEmpty(message = "Branch Address  can not be null or empty")
	private String branchAddress;

	public AccountsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountsDto(Long accountNumber, String accountType, String branchAddress) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public String toString() {
		return "AccountsDto [accountNumber=" + accountNumber + ", accountType=" + accountType + ", branchAddress="
				+ branchAddress + "]";
	}

}

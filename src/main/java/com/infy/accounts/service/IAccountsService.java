package com.infy.accounts.service;

import com.infy.accounts.dto.CustomerDto;

public interface IAccountsService {

	void createAccount(CustomerDto customerDto);

	CustomerDto fetchDetails(String mobileNumber);
}

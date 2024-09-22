package com.infy.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.accounts.constants.AccountsConstants;
import com.infy.accounts.dto.AccountsDto;
import com.infy.accounts.dto.CustomerDto;
import com.infy.accounts.entity.Accounts;
import com.infy.accounts.entity.Customer;
import com.infy.accounts.exception.CustomerAlreadyExistsException;
import com.infy.accounts.exception.ResourceNotFoundException;
import com.infy.accounts.mapper.AccountsMapper;
import com.infy.accounts.mapper.CustomerMapper;
import com.infy.accounts.repository.AccountsRepository;
import com.infy.accounts.repository.CustomerRepository;
import com.infy.accounts.service.IAccountsService;

@Service
public class AccountsServiceImpl implements IAccountsService {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	
	@Override
	public void createAccount(CustomerDto customerDto) {
		
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if (optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
		}
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("Sunny");
		Customer savedCustomer = customerRepository.save(customer);
		accountsRepository.save(createNewAccount(savedCustomer));

	}

	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		newAccount.setCreatedAt(LocalDateTime.now());
		newAccount.setCreatedBy("Sunny");
		return newAccount;
	}

	
	@Override
	public CustomerDto fetchDetails(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not found with given mobile number"));
		
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account details not found")
				);
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
	 boolean	isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if(accountsDto != null) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
					() -> new ResourceNotFoundException("Account with given number is not present")
					);
			 accounts = AccountsMapper.mapToAccounts(accountsDto, accounts);
			 accountsRepository.save(accounts);
			 
			 Long customerId = accounts.getCustomerId();
			 Customer customer = customerRepository.findById(customerId).orElseThrow(
					 () -> new ResourceNotFoundException("Customer not found with given ID")
					 );
			 
			 customer = CustomerMapper.mapToCustomer(customerDto, customer);
			 customerRepository.save(customer);
			 isUpdated = true;
		}
		return isUpdated;
	}
	
	

}

package com.infy.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.accounts.constants.AccountsConstants;
import com.infy.accounts.dto.CustomerDto;
import com.infy.accounts.dto.ResponseDto;
import com.infy.accounts.service.IAccountsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class AccountsController {

	@Autowired
	private IAccountsService iAccountsService;

	// Create API

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		this.iAccountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

//	Read API

	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountsDetails(
			@RequestParam @Pattern(regexp = "(^[6-9]\\d{9}$)", message = "Mobile number should be of 10 digits") String mobileNumber) {
		CustomerDto fetchDetails = this.iAccountsService.fetchDetails(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(fetchDetails);
	}

	// Update API

	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean updateAccount = this.iAccountsService.updateAccount(customerDto);
		if (updateAccount) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	}

	// Delete API

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccount(
			@RequestParam @Pattern(regexp = "(^[6-9]\\d{9}$)", message = "Mobile number should be of 10 digits") String mobileNumber) {

		boolean deleteAccount = this.iAccountsService.deleteAccount(mobileNumber);
		if (deleteAccount) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}
	}

}

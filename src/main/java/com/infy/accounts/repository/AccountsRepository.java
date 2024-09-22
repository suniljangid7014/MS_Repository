package com.infy.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.accounts.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	
	Optional<Accounts>  findByCustomerId(Long id);

}

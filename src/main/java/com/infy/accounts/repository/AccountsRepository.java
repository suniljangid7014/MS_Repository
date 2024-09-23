package com.infy.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.infy.accounts.entity.Accounts;

import jakarta.transaction.Transactional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	
	Optional<Accounts>  findByCustomerId(Long id);
	
	@Transactional
	@Modifying
	void deleteByCustomerId(Long id);

}

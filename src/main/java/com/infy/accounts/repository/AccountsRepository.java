package com.infy.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.accounts.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}

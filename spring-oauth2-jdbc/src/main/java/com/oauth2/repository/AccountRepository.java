package com.oauth2.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oauth2.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	Optional<Account> findByUsername(String username);
}
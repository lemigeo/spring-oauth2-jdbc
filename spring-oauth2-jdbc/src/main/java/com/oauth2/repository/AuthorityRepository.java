package com.oauth2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oauth2.domain.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	Optional<List<Authority>> findByUsername(String username);
}
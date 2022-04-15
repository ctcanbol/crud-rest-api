package com.cagricanbol.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cagricanbol.springboot.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}

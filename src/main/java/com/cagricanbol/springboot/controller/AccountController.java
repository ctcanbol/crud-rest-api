package com.cagricanbol.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cagricanbol.springboot.entity.Account;
import com.cagricanbol.springboot.exception.ResourceNotFoundException;
import com.cagricanbol.springboot.repository.AccountRepository;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	@GetMapping
	public List<Account> getAllAccounts() {
		return this.accountRepository.findAll();
	}

	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable(value = "id") long acccountId) {
		return this.accountRepository.findById(acccountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account id not found:" + acccountId));
	}

	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		return this.accountRepository.save(account);
	}

	@PutMapping("/{id}")
	public Account updatAccount(@RequestBody Account account, @PathVariable("id") long acccountId) {
		Account existingAccount = this.accountRepository.findById(acccountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account id not found:" + acccountId));
		existingAccount.setBranchCode(account.getBranchCode());
		existingAccount.setAccountNumber(account.getAccountNumber());
		existingAccount.setCustomerNumber(account.getCustomerNumber());

		return this.accountRepository.save(existingAccount);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Account> deleteAccount(@PathVariable("id") long acccountId) {
		Account existingAccount = this.accountRepository.findById(acccountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account id not found:" + acccountId));
		this.accountRepository.delete(existingAccount);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
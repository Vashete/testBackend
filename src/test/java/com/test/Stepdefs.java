package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import com.backend.test.controllers.MoneyTransferController;
import com.backend.test.exceptions.AccountsNotValidException;
import com.backend.test.exceptions.NoBalanceException;
import com.backend.test.exceptions.ServiceException;
import com.backend.test.model.AccountData;
import com.backend.test.model.AccountModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs {
	String request;
	String result;
	@Given("two different accounts")
	public void two_different_accounts() throws JsonProcessingException {
	    AccountModel account = new AccountModel();
	    account.setAmount(120D);
	    AccountData accountOrigin = new AccountData();
	    accountOrigin.setAccountNumber("43322122");
	    accountOrigin.setSortCode("01-01-12");
	    AccountData accountDest = new AccountData();
	    accountDest.setAccountNumber("43322123");
	    accountDest.setSortCode("01-01-13");
	    
	    account.setOrigin(accountOrigin);
	    account.setDestination(accountDest);
	    ObjectMapper objMapper = new ObjectMapper();
	    request=objMapper.writeValueAsString(account);
	}

	@When("I call to the deposit service")
	public void i_call_to_the_deposit_service() throws IOException, ServiceException, NoBalanceException, AccountsNotValidException {
	    MoneyTransferController moneyTransfer = new MoneyTransferController();
	    result=moneyTransfer.deposit(request);
	}

	@Then("a transfer result is returned")
	public void a_transfer_result_is_returned() {
	    assertNotNull(result);
	}

	@Given("the same accounts")
	public void the_same_accounts() throws JsonProcessingException {
		AccountModel account = new AccountModel();
	    AccountData accountOrigin = new AccountData();
	    accountOrigin.setAccountNumber("43322122");
	    accountOrigin.setSortCode("01-01-12");
	    account.setAmount(120D);
	    AccountData accountDest = new AccountData();
	    accountDest.setAccountNumber("43322122");
	    accountDest.setSortCode("01-01-12");
	    
	    
	    account.setOrigin(accountOrigin);
	    account.setDestination(accountDest);
	    ObjectMapper objMapper = new ObjectMapper();
	    request=objMapper.writeValueAsString(account);
	}

	@Then("an error is returned")
	public void an_error_is_returned() {
		assertEquals("\"The origin account and the destination account are the same\"", result);
	}
	
	@Given("two different accounts and the origin without balance")
	public void two_different_accounts_and_the_origin_without_balance() throws JsonProcessingException  {
		AccountModel account = new AccountModel();
	    AccountData accountOrigin = new AccountData();
	    accountOrigin.setAccountNumber("43322122");
	    accountOrigin.setSortCode("01-01-12");
	    account.setAmount(120000D);
	    AccountData accountDest = new AccountData();
	    accountDest.setAccountNumber("43322123");
	    accountDest.setSortCode("01-01-13");
	    account.setOrigin(accountOrigin);
	    account.setDestination(accountDest);
	    ObjectMapper objMapper = new ObjectMapper();
	    request=objMapper.writeValueAsString(account);
	}

	@Then("a balance error is returned")
	public void a_balance_error_is_returned() {
		assertEquals("\"The account 01-01-12 43322122 does not have enough balance to do the operation.\"", result);
	}
	
	@Given("an origin without destination")
	public void an_origin_without_destination() throws JsonProcessingException {
		AccountModel account = new AccountModel();
	    AccountData accountOrigin = new AccountData();
	    accountOrigin.setAccountNumber("43322122");
	    accountOrigin.setSortCode("01-01-12");
	    account.setAmount(120000D);
	    account.setOrigin(accountOrigin);
	    ObjectMapper objMapper = new ObjectMapper();
	    request=objMapper.writeValueAsString(account);
	}

	@Then("an account not validated is returned")
	public void an_account_not_validated_is_returned() {
		assertEquals("\"You need to provide an origin and/or a destination account.\"", result);
	}


}

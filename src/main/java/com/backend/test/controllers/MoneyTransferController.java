package com.backend.test.controllers;

import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.backend.test.exceptions.AccountsNotValidException;
import com.backend.test.exceptions.NoBalanceException;
import com.backend.test.exceptions.ServiceException;
import com.backend.test.model.AccountModel;
import com.backend.test.services.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


/**
 * Main Controller of the application
 * @author Vashete
 * @version 20190515
 */
@Path("/bank-transaction")
@Slf4j
public class MoneyTransferController {

	/**
	 * Method that deposit money into another account
	 * @param accountData The account data that contains the two accounts and the amount to deposit
	 * @return Result of the operation
	 * @throws IOException In case that you send a non valid json
	 * @throws ServiceException If the service layer fails to return a response
	 * @throws NoBalanceException If the origin account does not have enough balance
	 * @throws AccountsNotValidException If there is a problem with the validation of the accounts
	 */
	@Path("deposit")
	@POST
	@Produces("application/json")
	public String deposit(String accountData)
			throws IOException, ServiceException, NoBalanceException, AccountsNotValidException {
		ObjectMapper objMapper = new ObjectMapper();

		String result;
		try {
			AccountModel account = objMapper.readValue(accountData, AccountModel.class);

			if (account.getOrigin() == null || account.getDestination() == null) {
				throw new AccountsNotValidException("You need to provide an origin and/or a destination account.");
			} else if (account.getOrigin().equals(account.getDestination())) {
				throw new AccountsNotValidException("The origin account and the destination account are the same");
			}
			TransferService transfer = TransferService.getInstance();
			result = objMapper.writeValueAsString(transfer.deposit(account));
		} catch (IOException | ServiceException | NoBalanceException | AccountsNotValidException e) {
			log.error("Error in the application. Cause: {} Message: {}", e.getClass().getSimpleName(), e.getMessage());
			result = objMapper.writeValueAsString(e.getMessage());
		}
		return result;

	}


}

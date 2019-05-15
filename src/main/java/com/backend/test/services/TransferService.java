package com.backend.test.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.service.spi.ServiceException;

import com.backend.test.entities.Account;
import com.backend.test.exceptions.AccountsNotValidException;
import com.backend.test.exceptions.NoBalanceException;
import com.backend.test.model.AccountData;
import com.backend.test.model.AccountModel;
import com.backend.test.model.TransferResult;
import com.backend.test.util.AccountValidator;
import com.backend.test.util.HibernateUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Transfer service shared by the controllers in order to do the deposit
 * 
 * @author Vashete
 * @version 20190515
 */

@Slf4j
public class TransferService {
	/**
	 * Method to return an instance of the Transfer service
	 * @return an instance of the Transfer service
	 */
	public static TransferService getInstance() {
		TransferService transferService = new TransferService();
		return transferService;
	}

	private final static String OK = "OK";

	/**
	 * Method to create a deposit into a bank
	 * 
	 * @param origin      From account
	 * @param destination Destination account
	 * @return result of the operation
	 * @throws ServiceException
	 */
	public TransferResult deposit(AccountModel accountData)
			throws com.backend.test.exceptions.ServiceException, NoBalanceException, AccountsNotValidException {
		// We validate that the accounts are valid
		TransferResult transferResult = null;
		if (AccountValidator.validateAccount(accountData.getOrigin().getSortCode(),
				accountData.getOrigin().getAccountNumber())
				&& AccountValidator.validateAccount(accountData.getDestination().getSortCode(),
						accountData.getDestination().getAccountNumber())) {
			log.debug("Accounts {} and {} valdated.", accountData.getOrigin(), accountData.getDestination());

			Account originAccount = getAccountData(accountData.getOrigin());
			// We check that the origin account has enough money to do the operation
			if (originAccount.getAccountBalance() < accountData.getAmount()) {
				throw new NoBalanceException(
						"The account " + AccountValidator.getFullAccountData(accountData.getOrigin())
								+ " does not have enough balance to do the operation.");
			}
			Account destinationAccount = getAccountData(accountData.getDestination());
			Transaction transaction = null;
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				transaction = session.beginTransaction();
				/**
				 * We need to do the following: Add a transaction operation Update the balance
				 * from both accounts. Return the result of the operation
				 */
				com.backend.test.entities.Transaction transactionData = new com.backend.test.entities.Transaction();
				transactionData.setOrigin(originAccount);
				transactionData.setDestination(destinationAccount);
				transactionData.setDate(Timestamp.from(Instant.now()));
				transactionData.setAmount(accountData.getAmount());
				session.save(transactionData);
				originAccount.setAccountBalance(originAccount.getAccountBalance() - accountData.getAmount());
				destinationAccount.setAccountBalance(destinationAccount.getAccountBalance() + accountData.getAmount());
				session.saveOrUpdate(originAccount);
				session.saveOrUpdate(destinationAccount);
				// commit transaction
				transaction.commit();
				transferResult = new TransferResult(OK, accountData.getOrigin(), accountData.getDestination());
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				log.error("Error in the transaction. Cause: {} Message: {}", e.getClass().getSimpleName(),
						e.getMessage());
				throw new ServiceException(e.getMessage(), e);
			}
		} else {
			throw new AccountsNotValidException("The accounts are not valid");
		}
		return transferResult;

	}
	/**
	 * Method that returns the account data with an AccountData object
	 * @param model The accountData object
	 * @return The entity from the database
	 */
	public Account getAccountData(AccountData model) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Account> queryAccount = session.createQuery("from Account where sortCode=?1 and accountNumber=?2",
					Account.class);
			queryAccount.setParameter(1, model.getSortCode());
			queryAccount.setParameter(2, model.getAccountNumber());
			return queryAccount.getSingleResult();
		}
	}
}

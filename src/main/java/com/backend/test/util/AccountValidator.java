package com.backend.test.util;

import com.backend.test.model.AccountData;

import lombok.extern.slf4j.Slf4j;

/**
 * An account validator util to check if the account is valid
 * @author Vashete
 * @version 20190515
 */
@Slf4j
public class AccountValidator {

	/**
	 * Method to validate the sort code and the account number.
	 * This is better to call the modulus API, because it is constantly updating
	 * their database instead of doing manually like here
	 * @param sortCode
	 * @param accountNumber
	 */
	public static boolean validateAccount(String sortCode, String accountNumber) {
		Boolean sortCodeValidator = Boolean.FALSE;
		Boolean accountNumberValidator = Boolean.FALSE;
		//Validation for the sort code
		
		if(sortCode.contains("-")) {
			String[] sortCodeArray=sortCode.split("-");
			if(sortCodeArray.length==3) {
				try {
					for(int i=0;i<sortCodeArray.length;i++) {
						
							Integer.parseInt(sortCodeArray[i]);
					}
					sortCodeValidator= Boolean.TRUE;
				}catch(NumberFormatException e) {
					log.info("Sort Code {} invalid.",sortCode);
					sortCodeValidator = Boolean.FALSE;
				}
				
			}
		}
		
		try {
			Double.parseDouble(accountNumber);
			accountNumberValidator = Boolean.TRUE;
		}catch(NumberFormatException e) {
			log.info("Account number {} invalid.",accountNumber);
			accountNumberValidator = Boolean.FALSE;
		}
		
		return sortCodeValidator && accountNumberValidator;
	}
	
	public static String getFullAccountData(AccountData accountData) {
		StringBuilder sb = new StringBuilder();
		sb.append(accountData.getSortCode()).append(' ').append(accountData.getAccountNumber());
		return sb.toString();
	}
}

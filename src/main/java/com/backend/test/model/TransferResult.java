package com.backend.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The result of the operation once the money is deposit
 * 
 * @author Vashete
 * @version 20190515
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResult {

	private String result;
	private AccountData origin;
	private AccountData destination;
}

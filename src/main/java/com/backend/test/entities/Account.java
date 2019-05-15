package com.backend.test.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity account that contains the information of an account
 * @author Vashete
 * @version 20190515
 */
@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1176055976319937358L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "sortCode")
	private String sortCode;
	
	@Column(name = "accountNumber")
	private String accountNumber;
	
	@Column(name="accountBalance")
	private Double accountBalance;
}

package com.backend.test.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Transaction information that it is going to be fulfiled every transaction
 * executed in the platform
 * 
 * @author Vashete
 * @version 20190515
 */
@Data
@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "origin_account")
	private Account origin;

	@OneToOne
	@JoinColumn(name = "destination_account")
	private Account destination;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "date")
	private Timestamp date;

}

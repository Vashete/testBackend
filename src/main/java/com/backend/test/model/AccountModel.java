package com.backend.test.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * The request object that contains the origin and destination, and the amount
 * of the operation
 * 
 * @author Vashete
 * @version 20190515
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountModel {
	private AccountData origin;
	private AccountData destination;
	private Double amount;
}

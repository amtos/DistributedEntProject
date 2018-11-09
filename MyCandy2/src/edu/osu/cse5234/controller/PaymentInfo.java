package edu.osu.cse5234.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAYMENT_INFO")
public class PaymentInfo {
	@Column(name="CONFIRM_NUM")
	String confirmationNum;
	public String getConfirmationNum() 
	{
		return confirmationNum;
	}
	public void setConfirmationNum(String confirmationNum) {
		this.confirmationNum = confirmationNum;
	}
	@Column(name="CARD_NUM")
	 String ccNum;
	@Column(name="EXP_DATE")
	 String expiryDate;
	@Column(name="CVV")
	 String cvvCode;
	@Column(name="HOLDER_NAME")
	 String cardHolderName;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCcNum() {
		return ccNum;
	}
	public void setCcNum(String ccNum) {
		this.ccNum = ccNum;
	}
	
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public String getCvvCode() {
		return cvvCode;
	}
	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
}

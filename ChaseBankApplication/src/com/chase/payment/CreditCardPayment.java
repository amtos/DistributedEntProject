package com.chase.payment;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CreditCardPayment implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6205737053442725966L;
	
	
	
	
	
	String ccNum;
	
	 String expiryDate;

	 String cvvCode;

	 String cardHolderName;
	
	
	
	



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



	public double getPaymentAmt() {
		return paymentAmt;
	}



	public void setPaymentAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}



	private double paymentAmt;
	

	
	
	
}

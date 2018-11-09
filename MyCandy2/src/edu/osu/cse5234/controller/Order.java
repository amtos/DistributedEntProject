package edu.osu.cse5234.controller;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.osu.cse5234.business.view.Item;

@Entity
@Table(name="CUSTOMER_ORDER")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	int id; 
	/*
	@Column(name="SHIPPING_INFO_ID_FK")
	int shipId;
	public int getShipId() {
		return shipId;
	}
	public void setShipId(int shipId) {
		this.shipId = shipId;
	}
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	@Column(name="PAYMENT_INFO_ID_FK")
	int paymentID; */
	@Column(name="CUSTOMER_NAME")
	String customerName;
	@Column(name="CUSTOMER_EMAIL")
	String emailAddress; 
	//TODO change these to just straight up be a string, per instructions?
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_ORDER_ID_FK")
	private List<LineItem> items;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PAYMENT_INFO_ID_FK")
	private PaymentInfo payment;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="SHIPPING_INFO_ID_FK")
	private ShippingInfo shipping;
	public ShippingInfo getShipping() {
		return shipping;
	}
	public ShippingInfo initShipping() {
		shipping=new ShippingInfo();
		return shipping;
	}
	public void setShipping(ShippingInfo shipping) {
		this.shipping = shipping;
	}

	@Column(name="STATUS")
	String status;
	 
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PaymentInfo getPayment() {
		return payment;
	}
	public PaymentInfo initPayment() {
		payment=new PaymentInfo();
		return payment;
	}

	public void setPayment(PaymentInfo payment) {
		this.payment = payment;
	}

	public List<LineItem> getItems() {
		return items;
	}

	public void setItems(List<LineItem> list) {
		this.items = list;
	}
	
	//private List itemList;
	
	
}

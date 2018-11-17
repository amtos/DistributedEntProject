package edu.osu.cse5234.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

import com.chase.payment.CreditCardPayment;
import com.chase.payment.PaymentProcessorService;
import com.ups.shipping.client.ShippingInitiationClient;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;
import edu.osu.cse5234.controller.LineItem;
import edu.osu.cse5234.controller.Order;
import edu.osu.cse5234.controller.PaymentInfo;
import edu.osu.cse5234.util.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
@Resource(name="jms/emailQCF", lookup="jms/emailQCF", type=ConnectionFactory.class)
public class OrderProcessingServiceBean {
	static int k=0;
	String customerEmail;
	private static String shippingResourceURI = "http://localhost:9080/UPS/jaxrs";
	@Inject
	@JMSConnectionFactory("java:comp/env/jms/emailQCF")
	private JMSContext jmsContext;

	@Resource(lookup="jms/emailQ")
	private Queue queue;

	ShippingInitiationClient shipClient=new ShippingInitiationClient(shippingResourceURI);
	@WebServiceRef(wsdlLocation = 
			"http://localhost:9080/ChaseBankApplication/PaymentProcessorService?wsdl")
			private PaymentProcessorService service;
	 @PersistenceContext
	    private EntityManager entityManager;

	    @Produces
	    @RequestScoped
	    public EntityManager getEntityManager() {
	        return entityManager;
	    }
    /**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String processOrder(Order order) {
    	// TODO Objective 3 (instructions unclear)
    	k=k+1;
    	double sum=0;

    	InventoryService orderProcServ = ServiceLocator.getInventoryService();
    	boolean chk=validateItemAvailability(order);
    	if(chk)
    		{
    		List<LineItem> items = order.getItems();
    		List<Item> orderlist = new ArrayList<Item>();
    	
    		for(LineItem e:items)
    		{
    			Item item1=new Item();
    			item1.setId(e.getId());
    			item1.setQuantity(e.getQuantity());
    			sum+=e.getQuantity()*e.getPrice();
    			item1.setPrice(e.getPrice());
    			orderlist.add(item1);
    		}
    		CreditCardPayment obja=new CreditCardPayment();
    		PaymentInfo ord=((Order) order).getPayment();
    		obja.setCcNum(ord.getCcNum());
    		obja.setExpiryDate(ord.getExpiryDate());
    		obja.setCvvCode(ord.getCvvCode());
    		obja.setCardHolderName(ord.getCardHolderName());
    		obja.setPaymentAmt(sum);
    		PaymentInfo a=order.getPayment();
    		
    		String confirmNum=service.getPaymentProcessorPort().processPayment(obja);
    		
    		if(Integer.parseInt(confirmNum)<0)
    		{
    			// abandon order processing. payment did not go through
    		}
    		a.setConfirmationNum(confirmNum);
    		order.setPayment(a);
    		orderProcServ.updateInventory(orderlist);
    		
    			
    			//privatestatic String shippingResourceURI = "http://localhost:9080/UPS/jaxrs";
    			JsonObject requestJson = Json.createObjectBuilder()
    					.add("Organization", "Delectable Delights LLC")
    					.add("OrderRefId", order.getId())
    					.add("Zip", order.getShipping().getZip())
    					.add("ItemsNumber", order.getItems().size())
    					.build();

    			// shipClient.invokeInitiateShipping(responseShipJson);
    			 JsonObject responseJson = shipClient.invokeInitiateShipping(requestJson);
    			 System.out.println("UPS accepted request? " + responseJson.getBoolean("Accepted"));
    			 System.out.println("Shipping Reference Number: "+responseJson.getInt("ShippingReferenceNumber"));
    			 order.getShipping().setShippingRefNumber(responseJson.getInt("ShippingReferenceNumber"));
    			 entityManager.persist(order);
    			 customerEmail=order.getShipping().getEmail();
    			 notifyUser();
     			entityManager.flush();

    		}
    	return String.valueOf(k);
    }
    
    public boolean validateItemAvailability(Order order) {
    	InventoryService invServ = ServiceLocator.getInventoryService();
    	List<LineItem> items = order.getItems();
    	List<Item> orderlist = new ArrayList<Item>();
    	int i=0;
		for(LineItem e:items)
		{
			Item item1=new Item();
			item1.setId(e.getId());
			item1.setQuantity(e.getQuantity());
		//	order.getItems().get(i).setItemNumber(itemNumber);
			item1.setPrice(e.getPrice());
			orderlist.add(item1);
		}
    	return invServ.validateQuantity(orderlist);
    }
    private void notifyUser() {

    	String message = customerEmail + ":" +
    	"Your order was successfully submitted. " + 
    		"You will hear from us when items are shipped. " + 
    		new Date();

    	System.out.println("Sending message: " + message);
    	jmsContext.createProducer().send((Destination) queue, message);
    	System.out.println("Message Sent!");
    	}


}

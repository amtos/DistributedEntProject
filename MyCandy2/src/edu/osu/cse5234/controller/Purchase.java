package edu.osu.cse5234.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.osu.cse5234.business.OrderProcessingServiceBean;
import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;
import edu.osu.cse5234.util.ServiceLocator;

@Controller
@RequestMapping("/purchase")
public class Purchase {
	ArrayList<Item> itemList;
	
	List<LineItem> inorderlist;
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ... instantiate and set order object with items to display
		/*Item snowballBlast = new Item();
		snowballBlast.setName("Snowball Blast");
		snowballBlast.setPrice("4.99");
		
		Item granolaSurprise = new Item();
		granolaSurprise.setName("Granola Surprise");
		granolaSurprise.setPrice("1.99");
		
		Item chocolateOverload = new Item();
		chocolateOverload.setName("Chocolate Overload");
		chocolateOverload.setPrice("9.99");
		
		Item theBigOne = new Item();
		theBigOne.setName("The Big One");
		theBigOne.setPrice("15.99");
		
		Item goldenEgg = new Item();
		goldenEgg.setName("Golden Egg");
		goldenEgg.setPrice("59.99");
		
		Order order = new Order();
		itemList = new ArrayList<>();
		itemList.add(snowballBlast);
		itemList.add(granolaSurprise);
		itemList.add(chocolateOverload);
		itemList.add(theBigOne);
		itemList.add(goldenEgg);
		order.setItems(itemList);*/
		InventoryService invServ = ServiceLocator.getInventoryService();
		Inventory inv = invServ.getAvailableInventory();
		List<Item> list = inv.getList();
	
		//Order order = new Order();
		//order.setItems(list);
		Order order=new Order();
		 inorderlist = new ArrayList<LineItem>();
		for(Item e:list)
		{
			LineItem item1=new LineItem();
			item1.setId(e.getId());
			item1.setItemName(e.getName());
			item1.setPrice(e.getPrice());
			inorderlist.add(item1);
		}
		order.setItems(inorderlist);
		
		//TODO should this be request or session scoped?
		//request.setAttribute("order", list);
		request.setAttribute("order", order);
		return "OrderEntryForm";
	}
	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		OrderProcessingServiceBean orderProcServ = ServiceLocator.getOrderProcessingService();
		
	
	//	System.out.println("Order01 Customer Name is "+order.getItems().get(0).getItemName());
		
		if(orderProcServ.validateItemAvailability(order)) {
			
			request.getSession().setAttribute("order", order);
			request.getSession().setAttribute("error","");
			
			return "redirect:/purchase/paymentEntry";
		} else {
			// TODO figure out how to make popup (Objective 3/4 number 4)
			request.getSession().setAttribute("error", "The item selected is out of stock");
			
			return "redirect:/purchase";
		}
	}
	
	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String viewPaymentEntryPage(HttpServletRequest request, HttpServletResponse response) {
		Order order =(Order) request.getSession().getAttribute("order");
		
		//System.out.println("Order-1 Customer Name is "+order.getItems().get(0).getItemName());
		request.setAttribute("payment",new PaymentInfo());	
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment(@ModelAttribute("payment") PaymentInfo payment, HttpServletRequest request) {
		Order order = (Order)request.getSession().getAttribute("order");
		order.setPayment(payment);
		//order.setPaymentID(payment.getId());
		request.getSession().setAttribute("order", order);
		request.getSession().setAttribute("payment", payment);
		
		//request.getSession().setAttribute("order", payment);
		return "redirect:/purchase/shippingEntry";
	}
	
	@RequestMapping(path = "/shippingEntry", method = RequestMethod.GET)
	public String viewShippingEntryPage(HttpServletRequest request, HttpServletResponse response) {
		Order order =(Order) request.getSession().getAttribute("order");
		
		
		request.setAttribute("shipping",new ShippingInfo());	
		return "ShippingOrderEntryForm";
	}
	
	@RequestMapping(path = "/submitShipping", method = RequestMethod.POST)
	public String submitShipping(@ModelAttribute("shipping") ShippingInfo shipping, HttpServletRequest request) {
		Order order = (Order)request.getSession().getAttribute("order");
		order.setShipping(shipping);
		order.setCustomerName(shipping.getName());
		order.setEmailAddress(shipping.getEmail());
		//order.setShipId(shipping.getId());
		request.getSession().setAttribute("order", order);
		request.getSession().setAttribute("shipping", shipping);
		
		
		return "redirect:/purchase/viewOrder";
	}
	
	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrderPage(HttpServletRequest request, HttpServletResponse response) {
//		Object shipping = request.getSession().getAttribute("shipping");
//		request.setAttribute("shipping", shipping);
		
		Order order = (Order)request.getSession().getAttribute("order");
		
		request.setAttribute("order", order);
//		Object payment = request.getSession().getAttribute("payment");
//		request.setAttribute("payment", payment);
		return "ViewOrder";
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(@ModelAttribute("order") Order order, HttpServletRequest request) {
		//System.out.println("Order Customer Name is "+order.getItems().get(0).getItemName());
		Order order2 = (Order)request.getSession().getAttribute("order");
		
		OrderProcessingServiceBean orderProcServ = ServiceLocator.getOrderProcessingService();
		
		String confirmNum=orderProcServ.processOrder(order2);
		//System.out.println("The confirm number is "+confirmNum);
		request.getSession().setAttribute("confirmationNum", confirmNum);
		
		return "redirect:/purchase/viewConfirmation";
	}
	
	@RequestMapping(path = "/viewConfirmation", method = RequestMethod.GET)
	public String viewConfirmationPage(HttpServletRequest request, HttpServletResponse response) {
		return "Confirmation";
	}

}
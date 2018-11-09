package edu.osu.cse5234.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	//Create ArrayList of Strings
	//Menu items
	//Mappings for contact us and about us
	//And any other tabs we want to add
	//JUSTIFY BUSINESS ON HOMEPAGE
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ... instantiate and set menu items to display
		ArrayList<String> menuItems = new ArrayList<String>();
		
		//menuItems.add("Home");
		//menuItems.add("Purchase");
		//menuItems.add("About Us");
		//menuItems.add("Contact");
		
		//request.setAttribute("menu", menuItems);
		return "home";
	}

	@RequestMapping(path = "/AboutUs", method = RequestMethod.GET)
	public String viewAboutPage(HttpServletRequest request, HttpServletResponse response) {
		//request.setAttribute("payment", new PaymentInfo());	
		return "AboutUs";
	}
	
	@RequestMapping(path = "/ContactUs", method = RequestMethod.GET)
	public String viewContactPage(HttpServletRequest request, HttpServletResponse response) {
		//request.setAttribute("payment", new PaymentInfo());	
		return "ContactUs";
	}
}
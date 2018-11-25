<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/css/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Order</title>
</head>
<body>
<%@ include file="header.jsp" %> 
<div class="bodyconte">
<form method="post">
  <h3>Confirm Order</h3>
    <table>
     
      
    
      
	<c:forEach items="${order.items}" var="item" varStatus="loop">
		<tr>
			<td><c:out value="${item.itemName}"></c:out></td>
			<td><c:out value="$${item.price}"></c:out></td>
			<td><c:out value="${item.quantity}" /></td>
		</tr>
	</c:forEach>	

    </table>
</form>

<form method="post">

    <table width="400" border="1">
      <h3>Payment Details</h3>
      
      <tr>
        <td><strong>Credit Card Number</strong></td>
        <td><c:out value="${payment.ccNum}" /></td>
       
      </tr>
      
      <tr>
        <td><strong>Expiration Date</strong></td>
        <td><c:out value="${payment.expiryDate}" /></td>
      </tr>
      
      <tr>
        <td><strong>CVV Code</strong></td>
        <td><c:out value="${payment.cvvCode}" /></td>
      </tr>
      
      <tr>
        <td><strong>Name on Card</strong></td>
        <td><c:out value="${payment.cardHolderName}" /></td>
      </tr>

    </table>
	</form>
	<form:form modelAttribute="shipping" method="post" action="confirmOrder">

    <table width="400" border="1">
      <h3>Shipping Address</h3>
      <tr>
        <td><strong>Name</strong></td>
        <td><c:out value="${shipping.name}" /></td>
      </tr>
      
      <tr>
        <td><strong>Address Line 1</strong></td>
        <td><c:out value="${shipping.addressLine1}" /></td>
      </tr>
      
      <tr>
        <td><strong>Address Line 2</strong></td>
        <td><c:out value="${shipping.addressLine2}" /></td>
      </tr>
      
      <tr>
        <td><strong>City</strong></td>
        <td><c:out value="${shipping.city}" /></td>
      </tr>
      
      <tr>
        <td><strong>State</strong></td>
        <td><c:out value="${shipping.state}" /></td>
      </tr>
      
      <tr>
        <td><strong>Zipcode</strong></td>
        <td><c:out value="${shipping.zip}" /></td>
      </tr>
<tr>
        <td><strong>Email</strong></td>
        <td><c:out value="${shipping.email}" /></td>
      </tr>
	  <tr>
		<td colspan="3"><input type="submit" value="Confirm"></td>
	  </tr>
	
    </table>
</form:form>
</div>
<br><br><br>
<%@ include file="footer.jsp" %> 
</body>
</html>
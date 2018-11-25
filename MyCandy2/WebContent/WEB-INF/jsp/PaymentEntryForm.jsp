<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/css/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Entry Form</title>
</head>
<body>
<%@ include file="header.jsp" %> 
<div class="bodyconte">
<form:form modelAttribute="payment" method="post" action="submitPayment">

<h3>Payment Details</h3>
    <table >
      
      
      <tr>
       
        <td><strong>Credit Card Number</strong></td>
        <td><form:input path="ccNum" /></td>
      </tr>
      
      <tr>
        <td><strong>Expiration Date</strong></td>
         <td><form:input path="expiryDate" /></td>
      </tr>
      
      <tr>
        <td><strong>CVV Code</strong></td>
        <td><form:input path="cvvCode" /></td>
      </tr>
      
      <tr>
        <td><strong>Name on Card</strong></td>
        <td><form:input path="cardHolderName" /></td>
      </tr>

	  <tr>
		<td colspan="2"><input type="submit" value="Submit"></td>
	  </tr>
	

    </table>
</form:form>
</div>
<br><br><br>
<%@ include file="footer.jsp" %> 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/css/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shipping Entry Form</title>
</head>
<body>
<%@ include file="header.jsp" %> 
<div class="bodyconte">
<form:form modelAttribute="shipping" method="post" action="submitShipping">

    <table width="400" border="1">
      <tr>
      	 <th colspan="3">Shipping Information</th>
      </tr>
      
      <tr>
        <td><strong>Name</strong></td>
        <td><form:input path="name" /></td>
      </tr>
      
      <tr>
        <td><strong>Address Line 1</strong></td>
        <td><form:input path="addressLine1" /></td>
      </tr>
      
      <tr>
        <td><strong>Address Line 2</strong></td>
        <td><form:input path="addressLine2" /></td>
      </tr>
      
      <tr>
        <td><strong>City</strong></td>
        <td><form:input path="city" /></td>
      </tr>
      
      <tr>
        <td><strong>State</strong></td>
        <td><form:input path="state" /></td>
      </tr>
       <tr>
        <td><strong>Country</strong></td>
        <td><form:input path="country" /></td>
      </tr>
      <tr>
        <td><strong>Zipcode</strong></td>
        <td><form:input path="zip" /></td>
      </tr>
 <tr>
        <td><strong>Email</strong></td>
        <td><form:input path="email" /></td>
      </tr>
	  <tr>
		<td colspan="2"><input type="submit" value="Submit"></td>
	  </tr>
	

    </table>
</form:form>
</div>
<%@ include file="footer.jsp" %> 
</body>
</html>
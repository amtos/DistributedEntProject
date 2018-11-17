<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/css/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Entry Form</title>
</head>
<body>
<%@ include file="header.jsp" %> 
<div class="bodyconte">
<form:form modelAttribute="order" method="post" action="purchase/submitItems">
 <c:if test="${not empty sessionScope.error}">
   Error: ${sessionScope.error}
</c:if>
    <table width="400" border="1">
      <tr>
      	 <th colspan="3">Team 4's Glorious Candies</th>
      </tr>
      
      <tr>
      	 <th>Name</th>
      	 <th>Price</th>
      	 <th>Quantity</th>
      </tr>

	<c:forEach items="${order.items}" var="item" varStatus="loop">
		<tr>
			<td><c:out value="${item.itemName}"></c:out></td>
			<td><c:out value="$${item.price}"></c:out></td>
			<td><form:input path="items[${loop.index}].quantity" /></td>
			<td style="display:none;"><form:hidden path="items[${loop.index}].itemName" value="${item.itemName}" /></td>
			<td style="display:none;"><form:hidden path="items[${loop.index}].price" value="${item.price}" /></td>
		</tr>
	</c:forEach>

	  <tr>
		<td colspan="2"><input type="submit" value="Purchase"></td>
	  </tr>
	

    </table>
   
</form:form>
</div>
<%@ include file="footer.jsp" %> 
</body>
</html>
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
  <h3><b>Try one of our confectionary delights</b></h3>
    <table>
     
    
	<c:forEach items="${order.items}" var="item" varStatus="loop">
		<tr>
			<td><c:out value="${item.itemName}"></c:out></td>
			<td><c:out value="$${item.price}"></c:out></td>
			<td><form:input path="items[${loop.index}].quantity" /></td>
			<td style="display:none;"><form:hidden path="items[${loop.index}].itemName" value="${item.itemName}" /></td>
			<td style="display:none;"><form:hidden path="items[${loop.index}].price" value="${item.price}" /></td>
		</tr>
	</c:forEach>

	 
	

    </table>
    <br>
    <table>
     <tr>
		<td colspan="3"><input type="submit" value="Purchase"></td>
	  </tr></table>
   
</form:form>
</div>
<br><br><br>
<%@ include file="footer.jsp" %> 
</body>
</html>
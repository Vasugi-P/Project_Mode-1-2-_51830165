<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="addAccount" method="post" modelAttribute="account">
	<form:hidden path="accountNumber"/>
	Enter balance:<form:input path="balance"/><br/>
	Enter blocked:<form:input path="blocked"/><br/>
	 Enter customer name:<form:input path="customer.name"/><br/>
	Enter customer email:<form:input path="customer.email"/><br/>
	Enter customer phone:<form:input path="customer.phone"/><br/>
	Enter customer.address:<form:input path="customer.address"/><br/>
	Enter customer city:<form:input path="customer.city"/><br/>
	Enter customer country:<form:input path="customer.country"/><br/> 
	<input type="submit">
	
	</form:form>
</body>
</html>
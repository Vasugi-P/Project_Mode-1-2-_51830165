<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="5">
		<thead>
			<tr>
				<th>accountNumber</th>
				<th>balance</th>
				<th>blocked</th>
				<th>customerName</th>
				<th>customer email</th>
				<th>customer phone</th>
				<th>customer address</th>
				<th>customer city</th>
				<th>customer country</th>
					
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${accounts }" var="account">
				<tr>
				<td>${account.accountNumber }</td>
					<td>${account.balance }</td>
					<td>${account.blocked }</td>
					<td>${account.customer.name }</td>
					<td>${account.customer.email }</td>
					<td>${account.customer.phone }</td>
					<td>${account.customer.address}</td>
					<td>${account.customer.city}</td>
					<td>${account.customer.country }</td>
				
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<a href="addAccount">addAccount</a><br/>
	<a href="transfer">fund transfer</a><br/>
	<a href="deposit">deposit fund</a><br/>
	<a href="withdraw">withdraw fund</a><br/>
	<a href="logout">logout</a>
	
</body>
</html>
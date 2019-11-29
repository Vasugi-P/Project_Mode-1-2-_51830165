<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1">

		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>email</th>
				<th>phone</th>
				<th>address</th>
				<th>roles</th>
				<th>active</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
			   <tr>
				<td>${user.id }</td>
				<td>${user.name }</td>
				<td>${user.email}</td>
				<td>${user.phone}</td>
				<td>${user.address}</td>
				<td>
				<c:forEach var="temp" items="${user.roles}">
        			<c:out value="${temp}"></c:out>
    			</c:forEach>
				</td>
				
				<td>${user.active}</td>
			   </tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="addUser">add  app user</a><br/>
</body>
</html>
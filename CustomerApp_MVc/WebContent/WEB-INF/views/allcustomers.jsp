<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	welcome${ user.name }
	<br />
	<a href="logout">logout</a>
	<br />
	<br />
	<br />
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				
				<table class="table table-striped">

					<thead>
						<tr>
							<th>id</th>
							<th>name</th>
							<th>address</th>
							<th>mobile</th>
							<th>salary</th>
							<th>email</th>
							<th>update</th>
							<th>delete</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${customers}" var="customer">
							<tr>
								<td>${customer.id }</td>
								<td>${customer.name}</td>
								<td>${customer.address }</td>
								<td>${customer.mobile}</td>
								<td>${customer.salary }</td>
								<td>${customer.email }</td>
								
								
								<c:if test="${user.profile == 'admin'}">
									<td><a href="update?id=${customer.id }">update</a></td>
									<td><a href="delete?id=${customer.id }">delete</a></td>
								</c:if>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<c:if test="${user.profile == 'admin' || user.profile == 'mgr'}">
		<a href="addcustomer">addcustomer</a>
	</c:if>

</body>
</html>
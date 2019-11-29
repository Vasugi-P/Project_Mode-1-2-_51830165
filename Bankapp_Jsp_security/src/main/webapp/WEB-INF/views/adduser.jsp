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
<form:form action="adduser" method="post" modelAttribute="aduser">
<form:hidden path="id"/>
Enter name:<form:input path="name"/><br/>
Enter email:<form:input path="email"/><br/>
Enter phone:<form:input path="phone"/><br/>
Enter address:<form:input path="address"/><br/>
Enter roles:<form:input path="roles"/><br/>
Enter active:<form:input path="active"/><br/>
<input type="submit">

</form:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
User : <sec:authentication property="principal.username"/><br/>
User : <sec:authentication property="principal.authorities"/><br/>
<a href="logout">logout</a>
<br/>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')"> 
	App user management: <a href="admin/allusers">user management</a><br/>
</sec:authorize>

Customer management: <a href="customer/allAccount">customer management</a><br/>
Account management: <a href="mgr/allAccount">account management</a><br/>
</body>
</html>
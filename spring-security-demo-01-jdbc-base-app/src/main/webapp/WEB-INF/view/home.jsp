<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>
Spring security with spring mvc

</title>
</head>
<body>
<h2>
Security Anant
</h2>

Welcome Anant
<hr>

<p>
user: <security:authentication property="principal.username"/>
<br><br>

roles: <security:authentication property="principal.authorities"/>

</p>

<hr>
<security:authorize access="hasRole('MANAGER')">
<p>
<a href="${pageContext.request.contextPath}/leaders"> Manager Role Access</a>
</p>
</security:authorize>


<security:authorize access="hasRole('ADMIN')">
<p>
<a href="${pageContext.request.contextPath}/systems"> IT Role Access</a>
</p>
</security:authorize>
<hr>
<form:form action="${pageContext.request.contextPath}/logout" 
		   method="post">
		   <br><br>
<input type="submit" value="logout"/>


</form:form>


</body>
</html>
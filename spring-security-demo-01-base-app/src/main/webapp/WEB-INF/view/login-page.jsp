<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style>

.failed{
color:red;
}

</style>

<title>
Custom Login Form 

</title>


</head> 
<body>
<h2>Custom Login Form</h2>
<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
<c:if test="${param.error!=null}">
<i class="failed">Sorry ! you have entered a wrong user name and password</i>

</c:if>

<br><br>
User Name: <input type="text" name="username">
<br><br>
Password: <input type="password" name="password">
<br><br>
<input type="submit" value="submit">

</form:form>

</body>

</html>
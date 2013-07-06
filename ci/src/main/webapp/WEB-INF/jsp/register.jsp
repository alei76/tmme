<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<h3>Register</h3>

<c:url value="/user/register" var="registerUrl" />
<form:form action="${registerUrl}" method="post" commandName="registrationForm">
     <table>
         <tr><td>Username</td><td><form:input path="username" /></td></tr>
         <tr><td>Password</td><td><form:password path="password" /></td></tr>
         <tr><td>Firstname</td><td><form:input path="firstName" /></td></tr>
         <tr><td>Lastname</td><td><form:input path="lastName" /></td></tr>
         <tr><td>Email</td><td><form:input path="email" /></td></tr>
      </table>
	<p><button type="submit">Register</button></p>
</form:form>

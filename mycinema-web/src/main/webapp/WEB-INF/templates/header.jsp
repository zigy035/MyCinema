<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="nav">
	<h1>MyCinema</h1>
	<a href="<%=request.getContextPath()%>/homepage">Home</a> |
	<sec:authorize access="isAnonymous()">
		<a href="<%=request.getContextPath()%>/login">Login</a> |
		<a href="<%=request.getContextPath()%>/register">Register</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="<%=request.getContextPath()%>/admin">Admin Panel</a> | 
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<a href="<%=request.getContextPath()%>/myaccount">My Account</a> | 
	    <c:url var="logoutURL" value="/login/logout" />
	    <a href="${logoutURL}">Logout</a>
	</sec:authorize>
</div>
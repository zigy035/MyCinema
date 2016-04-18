<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3><spring:message code="please.login"/></h3>
<br/>

<c:if test="${error}">
	<p class="error"><spring:message code="login.error"/></p>
</c:if>

<div class="form_content">
	<c:url var="loginURL" value="/login"/>
	<form action="${loginURL}" method="post">
		<c:if test="${error}">
			<p><spring:message code="login.error"/></p>
		</c:if>
		<p>
			<label for="username"><spring:message code="username"/></label> 
			<input id="username" name="username" type="text"/>
		</p>
		<p>
			<label for="password"><spring:message code="password"/></label> 
			<input id="password" name="password" type="password"/>
		</p>
		<spring:message var="loginLabel" code="login"/>
		<input type="submit" value="${loginLabel}"/>
	</form>
</div>
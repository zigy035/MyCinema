<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3><spring:message code="register.form"/></h3>

<div class="form_content">
	<c:url var="registerURL" value="/register" />
	<form:form commandName="registerFormBean" action="${registerURL}" method="post">
		<p>
			<form:label path="firstName"><spring:message code="authuser.firstname"/></form:label>
			<form:input path="firstName"/>
			<form:errors path="firstName" cssErrorClass="error"/><br/>
		</p>
		<p>
			<form:label path="lastName"><spring:message code="authuser.lastname"/></form:label>
			<form:input path="lastName"/>
			<form:errors path="lastName" cssErrorClass="error"/><br/>
		</p>
		<p>
			<form:label path="username"><spring:message code="authuser.username"/></form:label>
			<form:input path="username"/>
			<form:errors path="username" cssErrorClass="error"/><br/>
		</p>
		<p>
			<form:label path="password"><spring:message code="authuser.password"/></form:label>
			<form:password path="password" showPassword="true"/>
			<form:errors path="password" cssErrorClass="error"/><br/>
		</p>
		<p>
			<form:label path="confirmPassword"><spring:message code="authuser.confirm.password"/></form:label>
			<form:password path="confirmPassword" showPassword="true"/>
			<form:errors path="confirmPassword" cssErrorClass="error"/><br/>
		</p> 
		<spring:message var="registerLabel" code="authuser.register"/>
		<input type="submit" value="${registerLabel}"/>
	</form:form>
</div>
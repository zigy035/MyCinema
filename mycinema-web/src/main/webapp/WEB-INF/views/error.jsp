<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<p class="error">
	<spring:message code="error.occured"/> 
	<a class="plink" href="${pageContext.request.contextPath}/homepage">
		<spring:message code="home.page"/>
	</a>.
</p>
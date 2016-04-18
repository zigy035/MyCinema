<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h3><spring:message code="congrats"/></h3>
<p><spring:message code="booking.confirmed"/></p>
<c:forEach var="seat" items="${matrixForm.seats}">
	<p>${seat}</p>
</c:forEach>
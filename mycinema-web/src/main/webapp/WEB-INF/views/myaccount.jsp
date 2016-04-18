<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:choose>
	<c:when test="${not empty bookings}">
		<h3>My Bookings</h3>
		<table class="stripeMe sample">
			<tr>
				<th><spring:message code="date"/></th>
				<th><spring:message code="time"/></th>
				<th><spring:message code="movie"/></th>
				<th><spring:message code="theatre"/></th>
				<th><spring:message code="seats"/></th>
			</tr>
			<c:forEach items="${bookings}" var="booking">
				<tr>
					<td><fmt:formatDate value="${booking.broadcastDate}" pattern="dd.MM.yyyy"/></td>
					<td><fmt:formatDate value="${booking.broadcastDate}" pattern="HH:mm"/></td>
					<td>${booking.movieTitle}</td>
					<td>${booking.theatreName}</td>
					<td>${booking.tickets}</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<p><spring:message code="no.bookings"/></p>
	</c:otherwise>
</c:choose>
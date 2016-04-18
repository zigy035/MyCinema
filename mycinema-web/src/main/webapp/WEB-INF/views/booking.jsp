<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h3><spring:message code="book.tickets.info"/></h3>
 
<c:if test="${not empty error}">
	<c:choose>
		<c:when test="${error eq 0}">
			<p class="error"><spring:message code="booking.error"/></p>
		</c:when>
		<c:when test="${error eq 1}">
			<p class="error"><spring:message code="please.select.seats"/></p>
		</c:when>
		<c:when test="${error eq 2}">
			<p class="error"><spring:message code="max.seats.allowed"/></p>
		</c:when>
		<c:when test="${error eq 3}">
			<p class="error"><spring:message code="seats.not.available"/></p>
		</c:when>
	</c:choose>
</c:if>

<div class="form_content">
	<c:url var="bookURL" value="/book" />
	<form:form commandName="matrixForm" action="${bookURL}" method="post">
		<form:hidden path="movieBroadcastId"/>
		<form:errors path="movieBroadcastId"/>
		<table class="bookingTable">
		<tr>
			<td></td>
			<c:forEach begin="1" end="${rowLength}" step="1" var="cellIndex">
				<td>${cellIndex}</td>
			</c:forEach>
		</tr>
		<c:forEach var="entry" items="${ticketMap}">
			<tr>
				<td>${entry.key}</td>
				<c:forEach items="${entry.value}" var="ticket">
					<td>
						<form:checkbox path="seats" disabled="${not empty ticket.authUserId}" 
							value="${ticket.seatRow}-${ticket.seatColumn}"/>
						<form:errors path="seats" />
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
		</table>
		<br/>
		<spring:message var="bookLabel" code="book.tickets" />	
		<input type="submit" value="${bookLabel}"/>
		<br/>
	</form:form>
</div>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h3><spring:message code="admin.panel"/></h3>

<div class="form_content">
	<c:if test="${error}">
		<p class="error"><spring:message code="movie.broadcast.exist"/></p>
	</c:if>
	<c:url var="addBroadcastURL" value="/admin/addbroadcast"/>
	<form:form commandName="movieBroadcastForm" action="${addBroadcastURL}" method="post">
		<p><form:hidden path="mbId"/></p>
		<p>
			<form:label path="movieId"><spring:message code="movie.label"/></form:label>
			<form:select path="movieId">
				<form:option value=""><spring:message code="please.select.movie" /></form:option>
				<c:forEach items="${movies}" var="movie">
					<c:choose>
						<c:when test="${movie.id eq movieBroadcastForm.movieId}">
							<option value="${movie.id}" selected="selected">
								${movie.title}
							</option>
						</c:when>
						<c:otherwise>
							<option value="${movie.id}">
								${movie.title}
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="movieId" cssErrorClass="error"/>
		</p>
		<p>
			<form:label path="theatreId"><spring:message code="theatre.label"/></form:label>
			<form:select path="theatreId">
				<form:option value=""><spring:message code="please.select.theatre" /></form:option>
				<c:forEach items="${theatres}" var="theatre">
					<c:choose>
						<c:when test="${theatre.id eq movieBroadcastForm.theatreId}">
							<option value="${theatre.id}" selected="selected">
								${theatre.name}
							</option>
						</c:when>
						<c:otherwise>
							<option value="${theatre.id}">
								${theatre.name}
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="theatreId" cssErrorClass="error"/>
		</p>
		<p>		
			<form:label path="broadcastDate"><spring:message code="broadcast.date.label"/></form:label>
			<form:select path="broadcastDate">
				<form:option value=""><spring:message code="please.select.date" /></form:option>
				<c:forEach items="${broadcastDates}" var="brDate">
					<fmt:formatDate var="fmtDisplayDate" value="${brDate}" pattern="dd.MM.yyyy" />
					<fmt:formatDate var="fmtValueDate" value="${brDate}" pattern="yyyy-MM-dd" />
					<c:choose>
						<c:when test="${fmtValueDate eq movieBroadcastForm.broadcastDate}">
							<option value="${fmtValueDate}" selected="selected">
								${fmtDisplayDate}
							</option>
						</c:when>
						<c:otherwise>
							<option value="${fmtValueDate}">
								${fmtDisplayDate}
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="broadcastDate" cssErrorClass="error"/>
			<br/>
		</p>
		<p class="broadcastTime">
			<form:label path="broadcastHour"><spring:message code="broadcast.time.label"/></form:label>
			<form:select path="broadcastHour">
				<form:option value=""><spring:message code="please.select.hour" /></form:option>
				<c:forEach items="${broadcastHours}" var="brHour">
					<c:choose>
						<c:when test="${brHour eq movieBroadcastForm.broadcastHour}">
							<option value="${brHour}" selected="selected">
								${brHour}
							</option>
						</c:when>
						<c:otherwise>
							<option value="${brHour}">
								${brHour}
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="broadcastHour" cssErrorClass="error"/>
			
			<form:select path="broadcastMinute">
				<form:option value=""><spring:message code="please.select.minute" /></form:option>
				<c:forEach items="${broadcastMinutes}" var="brMinute">
					<c:choose>
						<c:when test="${brMinute eq movieBroadcastForm.broadcastMinute}">
							<option value="${brMinute}" selected="selected">
								${brMinute}
							</option>
						</c:when>
						<c:otherwise>
							<option value="${brMinute}">
								${brMinute}
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			<form:errors path="broadcastMinute" cssErrorClass="error"/>
		</p>
		<br/>
		<spring:message var="createBroadcast" code="create.broadcast"/>
		<input id="submitBroadcast" type="submit" value="${createBroadcast}"/>
	</form:form>
</div>

<c:choose>
	<c:when test="${not empty broadcasts}">
		<h3><spring:message code="available.broadcasts"/></h3>
		<table class="stripeMe sample">
			<tr>
				<th>Date</th>
				<th>Movie</th>
				<th>Theatre</th>
				<th>Time</th>
			</tr>
			<c:forEach items="${broadcasts}" var="broadcast">
				<tr>
					<td><fmt:formatDate value="${broadcast.broadcastDate}" pattern="dd.MM.yyyy"/></td>
					<td>${broadcast.movie.title}</td>
					<td>${broadcast.theatre.name}</td>
					<td><fmt:formatDate value="${broadcast.broadcastDate}" pattern="HH:mm"/></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<p><spring:message code="no.movie.broadcasts.yet"/></p>
	</c:otherwise>
</c:choose>

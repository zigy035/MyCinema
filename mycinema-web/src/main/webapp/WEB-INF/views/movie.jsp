<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3><spring:message code="movie.details"/></h3>

<div class="form_content">
	<c:url var="searchURL" value="/movie"/>
	<form id="movieSearchForm" action="${searchURL}" method="get">
		<p><input type="hidden" name="movieId" value="${param['movieId']}"/></p>
		<p>		
			<label for="broadcastDate"><spring:message code="broadcast.date"/></label>
			<select name="broadcastDate">
				<c:forEach items="${broadcastDates}" var="brDate">
					<fmt:formatDate var="fmtDisplayDate" value="${brDate}" pattern="dd.MM.yyyy" />
					<fmt:formatDate var="fmtValueDate" value="${brDate}" pattern="yyyy-MM-dd" />
					<c:choose>
						<c:when test="${fmtValueDate eq param['broadcastDate']}">
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
			</select>
		</p>
		<spring:message var="searchLabel" code="search"/>
		<input type="submit" value="${searchLabel}"/>
	</form>
</div>

<c:if test="${empty movie}">
	<p><spring:message code="movie.not.exist"/></p>
</c:if>

<br/>

<table class="movieDetail">
	<c:if test="${not empty movie}">
	<tr>
		<td><strong>Title</strong></td>
		<td>${movie.title}</td>
	</tr>
	<tr>
		<td><strong>Description</strong></td>
		<td>${movie.description}</td>
	</tr>
	</c:if>
	<c:choose>
		<c:when test="${not empty broadcasts}">
			<tr>
				<td><strong><spring:message code="broadcasts"/></strong></td>
				<td>
					<c:forEach items="${broadcasts}" var="broadcast">
						<c:url var="bookURL" value="/book">
							<c:param name="mbid" value="${broadcast.id}"/>
						</c:url>
						<a href="${bookURL}" class="broadcast">
							${broadcast.theatre.name} - 
							<fmt:formatDate pattern="HH:mm" value="${broadcast.broadcastDate}" />
						</a>
					</c:forEach>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<p class="error"><spring:message code="no.broadcast.for.date"/></p>
		</c:otherwise>
	</c:choose>
</table>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h3><spring:message code="movies"/></h3>

<table class="movies">
	<c:forEach items="${movies}" var="movie">
		<c:url var="movieDetailURL" value="/movie">
			<c:param name="movieId" value="${movie.id}"/>
		</c:url>
		<tr>
			<td>
				<a href="${movieDetailURL}">${movie.title}</a>
				<p>${movie.description}</p>
			</td>
		</tr>
	</c:forEach>
</table>
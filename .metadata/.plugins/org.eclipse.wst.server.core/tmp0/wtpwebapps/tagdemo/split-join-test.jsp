<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<body>

	<c:set var="data" value="Malaysia,Singapore,Indonesia,Vietnam" />
	
	<h3>Split</h3>
	
	<c:set var="cities" value="${fn:split(data, ',')}" />
	
	<c:forEach var="city" items="${cities}" >
	
		${city} <br/>
	
	</c:forEach>
	
	<h3>Join</h3>
	
	<c:set var="join" value="${fn:join(cities, '*') }" />
	
	${join}
	
	
</body>

</html>
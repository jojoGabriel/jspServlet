<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

	String[] cities = {"Mumbai", "Singapore", "Chicago"};

	pageContext.setAttribute("theCities", cities);
	
%>

<html>

<body>

	<c:forEach var="city" items="${theCities}">
	
		${city} <br/>
	
	</c:forEach>
</body>

</html>
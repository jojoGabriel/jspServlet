<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<body>

	<c:set var="data" value="joyOfCode" />
	
	Length of string <b>${data }</b>: ${fn:length(data)}
	
	<br><br>
	
	Uppercase of string <b>${data }</b>:  ${fn:toUpperCase(data)}

	<br><br>
	
	String <b>${data}</b> starts with <b>joy</b>?: ${fn:startsWith(data, "joy") }

</body>

</html>
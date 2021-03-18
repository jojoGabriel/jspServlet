<%@ page import="java.net.URLEncoder" %>

<html>

<head>
	<title>Confirmation</title>
</head>

<%
	// read form data
	String favLang = request.getParameter("favLang");

	favLang = URLEncoder.encode(favLang, "UTF-8");

	// create cookie
	Cookie favLangCookie = new Cookie("myApp.favLang", favLang);
	
	// set lifespan in seconds
	favLangCookie.setMaxAge(60*60*24);
	
	// send cookie to browser
	
	response.addCookie(favLangCookie);
%>

<body>
	We set your fav lang to: ${param.favLang}
	
	<br><br>
	
	<a href="cookies-homepage.jsp">Return to homepage</a>

</body>

</html>
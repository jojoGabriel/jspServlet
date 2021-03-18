<%@ page import="java.net.URLDecoder" %>

<html>

<head>
    <title>Home Page</title>
</head>

<body>
<h3>Training Portal</h3>

<%
	String favLang = "Java";

	Cookie[] cookies = request.getCookies();
	
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if ("myApp.favLang".equals(cookie.getName())) {
				favLang = URLDecoder.decode(cookie.getValue(), "UTF-8");
				break;
			}
		}
	}

%>

<h4>New Books for <%= favLang %></h4>
<ul>
	<li>book</li>
	<li>book</li>
	<li>book</li>	
</ul>

<h4>Latest News for <%= favLang %></h4>
<ul>
	<li>news</li>
	<li>news</li>
	<li>news</li>	
</ul>

<hr>
<a href="cookies-personalize-form.html">Personalize this page</a>

</body>

</html>
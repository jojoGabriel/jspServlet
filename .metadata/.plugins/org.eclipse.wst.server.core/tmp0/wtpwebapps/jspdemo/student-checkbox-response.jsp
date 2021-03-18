<html>
<head>
	<title>Student Confirmation Title</title>
</head>

<body>

The student is confirm: ${param.firstName} ${param.lastName}

<br><br>

Student's fav languages: <br>
<ul>
	<% 
		String[] langs = request.getParameterValues("favLang");
	
		for (String lang :  langs) {
			out.println("<li>" + lang + "</li>");
		}
	%>

</ul>


</body>

</html>
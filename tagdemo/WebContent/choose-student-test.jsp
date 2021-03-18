<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.*, io.gabriel.jsp.tagdemo.Student" %>

<%

	List<Student> students = new ArrayList<>();

	students.add(new Student("John", "Doe", false));
	students.add(new Student("Max", "Donald", false));	
	students.add(new Student("Mary", "Poppins", true));
	
	pageContext.setAttribute("students", students);
	
%>

<html>

<body>

	<table border="1">

	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Gold Customer</th>
	</tr>
	
	<c:forEach var="student" items="${students}">
	
		<tr>
			<td>${student.firstName}</td> 
			<td>${student.lastName}</td> 
			<td>
				<c:choose>
				
				    <c:when test="${student.goldCustomer}">
				    Special Discount
				    </c:when>
			    
				    <c:otherwise>
				   	$$$
				    </c:otherwise>
			    </c:choose>
			    
			 </td>
		</tr>
		
	</c:forEach>
	
	</table>

</body>

</html>
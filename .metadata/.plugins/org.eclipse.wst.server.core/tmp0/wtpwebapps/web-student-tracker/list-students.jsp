<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

	<head>
		<title>Student Tracker App</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
	</head>
		
	<body>

		<div id="wrapper">
			<div id="header">
				<h2>FooBar University</h2>
			</div>
		</div>
		<div id="container">
			<div id="content">
			
				<!--  Add Student button -->
				
				<input type="button" value="Add Student" 
				onclick="window.location.href='add-student-form.jsp'; return false;"
				class="add=student-button" />
				
				<br/> <br/>
				
				<!--  Add Search box -->
				<form action="StudentControllerServlet" method="GET">
					<input type="hidden" name="command" value="SEARCH" />
					Search student: <input type="text" name="searchName" />
					<input type="submit" value="Search" class="add-student-button" />
				</form>
			
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>						
					</tr>
					
					<c:forEach var="student" items="${STUDENT_LIST}">
					
						<c:url var="link" value="StudentControllerServlet">
							<c:param name="command" value="LOAD" />
							<c:param name="studentId" value="${student.id}" />
						</c:url>
						
						<c:url var="deleteLink" value="StudentControllerServlet">
							<c:param name="command" value="DELETE" />
							<c:param name="studentId" value="${student.id}" />
						</c:url>
					
						<tr>
							<td> ${student.firstName} </td>
							<td> ${student.lastName} </td>
							<td> ${student.email} </td>
							<td> 
								<a href="${link}">Update</a>
								|   
								<a href="${deleteLink}" 
								onclick="if (!(confirm('Are you sure?'))) return false">Delete</a>
							</td>
						</tr>
					
					</c:forEach>
				</table>
			</div>
		</div>

	</body>

</html>
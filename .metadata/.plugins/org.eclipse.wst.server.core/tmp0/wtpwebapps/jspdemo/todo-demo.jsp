<%@ page import="java.util.*" %>

<html>
<body>

<!--  Create HTML form -->
<form action="todo-demo.jsp">
	Add new item: <input type="text" name="item">
	<input type="submit" value="Submit">
</form>

<br>

<!--  Add item on the list -->

<%



	// get the items from the session
	List<String> items = (List<String>) session.getAttribute("toDoList");
	
	// if item doesn't exist, create it
	if (items == null) {
		items = new ArrayList<String>();
		session.setAttribute("toDoList", items);
	}
	
	// see if any form data to add
	String item = request.getParameter("item");
	
	boolean isItemNotEmpty = item !=null && item.trim().length() > 0;
	boolean isItemNotDuplicate = item != null && !items.contains(item.trim());
	
	
	if (isItemNotEmpty && isItemNotDuplicate) {
		items.add(item);
		response.sendRedirect("todo-demo.jsp");
	}

%>


<!--  Display all items  -->

	<hr>
	<b>To Do List Items:</b>
	<br>
	
	<ol>
	<% 
		for (String i : items) {
			out.println("<li>" + i + "</li>");
		}
	%>
	</ol>

</body>

</html>
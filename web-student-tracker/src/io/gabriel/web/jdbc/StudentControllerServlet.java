package io.gabriel.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
		
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception x) {
			throw new ServletException(x);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			// read the command
			String command = request.getParameter("command");
			
			if (command == null ) {
				command = "LIST";
			}

			
			// route to processing code
			switch (command) {
			case "LIST":
				
				// list the students ... in MVC
				listStudents(request, response);
				break;
							
			case "LOAD":
				loadStudent(request, response);
				break;
				
			case "UPDATE":
				updateStudent(request, response);
				break;
				
			case "DELETE":
				deleteStudent(request, response);
				break;
				
			case "SEARCH":
				searchStudent(request, response);
				break;
			
			default:
				listStudents(request, response);
			}
			
			

			

		} catch (Exception x) {
			throw new ServletException(x);
		}
		
	}
	
	private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String searchName = request.getParameter("searchName");
		
		List<Student> students = studentDbUtil.searchStudents(searchName);
		
		request.setAttribute("STUDENT_LIST", students);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String command = request.getParameter("command");
                    
            // route to the appropriate method
            switch (command) {
                            
            case "ADD":
                addStudent(request, response);
                break;
                                
            default:
                listStudents(request, response);
            }
                
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
        
    }
	


	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student id
		String studentId = request.getParameter("studentId");
		
		// delete student from db
		studentDbUtil.deleteStudent(studentId);
		
		// send back to list
		listStudents(request, response);
		
		
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student info
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		
		// create new student object
		Student student = new Student(id, firstName, lastName, email);
		
		// update db
		studentDbUtil.updateStudent(student);
		
		
		// send back to list
		listStudents(request, response);
		
	}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student id from form
		String studentId = request.getParameter("studentId");
		
		
		// get student from db
		Student student = studentDbUtil.getStudent(studentId);
		
		// place student in request
		request.setAttribute("STUDENT", student);
		
		// send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create new student object
		Student student = new Student(firstName, lastName, email);
		
		
		// add student to db
		studentDbUtil.addStudent(student);
		
		
		// send back to list page
		// listStudents(request, response);
		
		response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
		
			
		
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		// get students from db util
		List<Student> students = studentDbUtil.getStudents();
		
		// add students to the request
		request.setAttribute("STUDENT_LIST", students);
		
		// send to JPS page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
	}
	
	

}

package io.gabriel.servletdemo.mvctwo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MvcServletDemoTwo
 */
@WebServlet("/MvcServletDemoTwo")
public class MvcServletDemoTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MvcServletDemoTwo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.  get student data from util class
		List<Student> students = StudentDataUtil.getStudents();
		
		
		// 2.  add students to request object
		request.setAttribute("student_list", students);
		
		
		// 3.  get request dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("view_students_two.jsp");
		
		// 4.  forward to jsp
		dispatcher.forward(request, response);
		
		
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

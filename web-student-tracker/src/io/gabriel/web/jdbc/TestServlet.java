package io.gabriel.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	// Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. set up printwriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		
		// 2.  get a connection
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			
			conn = dataSource.getConnection();
			
			// 3. sql statement
			String sql = "select * from student";
			stmt = conn.createStatement();
			
			// 4.  execute sql
			rs = stmt.executeQuery(sql);
			
			// 5.  process result
			while (rs.next()) {
				String email = rs.getString("email");
				out.println(email);
			}
			
		} catch (Exception x) {
			x.printStackTrace();
		}
		
		
	}

}

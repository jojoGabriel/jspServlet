package io.gabriel.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource ds) {
		dataSource = ds;
	}
	
	public List<Student> getStudents() throws Exception {
		
		List<Student> students = new ArrayList<>();
		
		Connection conn = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			// get a connection 
			conn = dataSource.getConnection();
			
			// create sql
			String sql = "select * from student order by last_name";
			
			stmt = conn.createStatement();
						
			// execute query
			rs = stmt.executeQuery(sql);
			
			
			// process result set
			while (rs.next()) {
				
				// retrieve data from result set
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				// create new student object
				Student student = new Student(id, firstName, lastName, email);
				
				// add to list of students
				students.add(student);
				
				
			}

			return students;
			
		} finally {
			// close jdbc
			close(conn, stmt, rs);
		}

		
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {
		
		try {
			if (rs != null) {
				rs.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			
		}
		catch (Exception x) {
			
			x.printStackTrace();
			
		}
		
		
	}

	public void addStudent(Student student) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			// get db connection
			conn = dataSource.getConnection();
			
			// create insert stmt
			String sql = "insert into student " +
			             "(first_name, last_name, email) " + 
					     "values (?, ?, ?)";
			
			stmt = conn.prepareStatement(sql);
			
			// set param values
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());			
			stmt.setString(3, student.getEmail());			
			
			// execute sql
			stmt.execute();
		} 
		catch (Exception x) {
		
		// clean up jdbc
		close(conn, stmt, null);	
			
		}
		
	}

	public Student getStudent(String studentId) throws Exception {
		
		Student student = null;
		
		Connection conn = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		int id;
		
		try {
			
			// convert studentId to int
			id = Integer.parseInt(studentId);
			
			// get db connection
			conn = dataSource.getConnection();
			
			// create sql to read
			String sql = "select * from student where id = ?";
			
			// create prep stmt
			stmt = conn.prepareStatement(sql);
			
			// set params
			stmt.setInt(1, id);
			
			// execute query
			rs = stmt.executeQuery();
			
			
			// retrieve data from rs
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				// create new student
				student = new Student(id, firstName, lastName, email);
				
			} else {
				throw new Exception("Could not find student id: " + studentId);
			}
			
			return student;
		}
		finally {
			close(conn, stmt, rs);
		}
		
		

	}

	public void updateStudent(Student student) throws Exception {
		
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				
				conn = dataSource.getConnection();
				
				// create SQL update statement
				String sql = "update student "
							+ "set first_name=?, last_name=?, email=? "
							+ "where id=?";
				
				// prepare statement
				stmt = conn.prepareStatement(sql);
				
				// set params
				stmt.setString(1, student.getFirstName());
				stmt.setString(2, student.getLastName());
				stmt.setString(3, student.getEmail());
				stmt.setInt(4, student.getId());
				
				// execute SQL statement
				stmt.execute();
							
			} finally {
				close(conn, stmt, null);
			}		
	}

	public void deleteStudent(String studentId) throws Exception {
		
		Connection conn = null;
		
		PreparedStatement stmt = null;
		
		try {
			
			// convert student id to int
			int id = Integer.parseInt(studentId);
			
			// get db connection
			conn = dataSource.getConnection();
			
			// delete sql
			String sql = "delete from student where id=?";
			
			
			// prep stmt
			stmt = conn.prepareStatement(sql);
			
			// set params
			stmt.setInt(1, id);
			
			
			// execute sql
			stmt.execute();
			
		} finally {
			
			// clean up
			close(conn, stmt, null);
		}
		
	}

	public List<Student> searchStudents(String searchName) throws Exception {
	
		List<Student> students = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			
			if (searchName != null && searchName.trim().length() > 0 ) {
				
				String sql = "select * from student where lower(first_name) like ? " +
				"or lower(last_name) like ?";
				
				stmt = conn.prepareStatement(sql);
				
				String searchNameLike = "%" + searchName.toLowerCase() + "%";
				
				stmt.setString(1, searchNameLike);
				stmt.setString(2, searchNameLike);
			} else {
			
				String sql = "select * from student order by last_name";
				stmt = conn.prepareStatement(sql);
			}
			
			rs = stmt.executeQuery();
			
            while (rs.next()) {
                
                // retrieve data from result set row
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                
                // create new student object
                Student student = new Student(id, firstName, lastName, email);
                
                // add it to the list of students
                students.add(student);           
			
            }
            return students;
		
		} finally {
		close(conn, stmt, rs);
		}
	}

}

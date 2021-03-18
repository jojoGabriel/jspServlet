package io.gabriel.servletdemo.mvctwo;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
	
	public static List<Student> getStudents() {
		
		// create empty list
		List<Student> students = new ArrayList<>();
		
		// add sample data
		students.add(new Student("Mary", "Jane", "maryjane@email.com"));
		students.add(new Student("Jane", "Doe", "janedoe@email.com"));
		students.add(new Student("Matt", "Hero", "matthero@email.com"));
		
		// return list
		return students;
		
	}
}

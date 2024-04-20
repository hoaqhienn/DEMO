package app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import entities.Course;
import entities.Department;
import entities.Student;

public class Client {

	public static void main(String[] args) {
		try {
			String URL = "rmi://127.0.0.1:2005/";
			CourseDAO courseDAO = (CourseDAO) Naming.lookup(URL + "courseDAO");
			StudentDAO studentDAO = (StudentDAO) Naming.lookup(URL + "studentDAO");
			DepartmentDAO departmentDAO = (DepartmentDAO) Naming.lookup(URL + "departmentDAO");

//			List<Course> courses = courseDAO.findAll();
//			System.out.println("Courses:");
//			courses.forEach(System.out::println);

			List<Department> departmentsWithoutCourses = departmentDAO.findDepartmentNotOwnerCourse();
			System.out.println("Departments without courses:");
			departmentsWithoutCourses.forEach(System.out::println);

			List<Student> allStudents = studentDAO.findAll();
			System.out.println("All students:");
			allStudents.forEach(System.out::println);
		} catch (MalformedURLException e) {
			System.err.println("Malformed URL: " + e.getMessage());
		} catch (NotBoundException e) {
			System.err.println("Service not found: " + e.getMessage());
		} catch (RemoteException e) {
			System.err.println("Remote error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
		}
	}

}

package app;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import services.CourseService;
import services.DepartmentService;
import services.StudentService;

public class ServerRMI {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("school_server");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// Create a registry
		Registry registry = LocateRegistry.createRegistry(2005);

		CourseDAO courseDAO = new CourseService(entityManager); // Remote Object
		DepartmentDAO departmentDAO = new DepartmentService(entityManager); // Remote Object
		StudentDAO studentDAO = new StudentService(entityManager); // Remote Object

		
		
		registry.bind("courseDAO", courseDAO);
		registry.bind("departmentDAO", departmentDAO);
		registry.bind("studentDAO", studentDAO);

		System.out.println("RMI Server ready");
	}
}

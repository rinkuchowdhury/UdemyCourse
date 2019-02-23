package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import demo.entity.Course;
import demo.entity.Instructor;
import demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		

		try {
			// create objects
			Instructor tempInstructor = new Instructor("avinash","chy","avinah_chy@ymail.com");
			
			InstructorDetail tempInstructorDetail= new InstructorDetail("www.example.com", "guitar");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction			
			session.beginTransaction();

			// save the instructor : this will save the details object (cascadeType.All)
			System.out.println("saving instructor  : "+tempInstructor.getId());
		
			session.save(tempInstructor);
		
			System.out.println("after saving instructor  : \"+tempInstructor.getId()");
			
				
			//commit transaction
			session.getTransaction().commit();
			System.out.println("after commiting instructor object : "+tempInstructor.getId());
			
			System.out.println("done: Instructor object");
		}
		
		finally {
			
			//add clean up code
			session.close();
			factory.close();
		}
	}

}

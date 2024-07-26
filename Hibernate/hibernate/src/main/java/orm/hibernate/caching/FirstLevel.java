package orm.hibernate.caching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import orm.hibernate.annotation.Student;

public class FirstLevel {

	  public static void main(String[] args) {

		  /**
		   * This line creates a new instance of the Configuration class from Hibernate.
		   * The Configuration object is used to configure Hibernate and set up its properties.
		   */
		  Configuration con=new Configuration();
		  
		  /**
		   * This line tells the Configuration object to load the configuration settings from the file
		   *  hibernateConfig.cfg.xml, located in the orm/hibernate directory.
		   * The XML file contains important settings such as database connection details, dialect, 
		   *  mappings, and other Hibernate configurations.
		   */
		  con.configure("orm/hibernate/hibernateConfig.cfg.xml");
	  
		  /**
		   * The SessionFactory is a crucial object in Hibernate. It is a factory for Session objects, 
		   * which are used to interact with the database. The SessionFactory is typically created once 
		   * and used to create multiple Session instances.
		   */
		  SessionFactory ssf=con.buildSessionFactory();
		  
		  /**
		   * Created a session
		   */
		  Session session=ssf.openSession();
		  
		  Student st=session.get(Student.class, 1);
		  System.out.println(st.getName());
		  System.out.println("Performing some other operation");
		  System.out.println("Again need to access Student class");
		  Student st1=session.get(Student.class, 1);
		  System.out.println(st1.getName());
		  System.out.println("Check if the object exists in Session Object");
		  System.out.println(session.contains(st1));
		  
		  session.clear();
		  
		  System.out.println("Closed Session");
		  System.out.println("Check if the object exists in Session Object");
		  System.out.println(session.contains(st1));
	  }
}

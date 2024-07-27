package orm.hibernate.caching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import orm.hibernate.annotation.Student;

public class SecondLevel {


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
		   * Created 2 sessions
		   */
		  System.out.println("Created Session 1");
		  Session session1=ssf.openSession();
		  
		  /** 
		   * Executed query in session1 
		   */
		  Student st=session1.get(Student.class, 1);
		  
		  System.out.println(st.getName());
		  System.out.println("Shared arcoss multiple sessions");
		  session1.close();
		  System.out.println("Closed Session 1");
		  System.out.println("Created Session 2");
		  Session session2=ssf.openSession();
		  Student st2=session2.get(Student.class, 1);
		  System.out.println("Query not again got executed we retrieve values from cache");
		  System.out.println(st2.getName());
		  
		  System.out.println("New session created");
		  String hqlQuery="from Student where id=1";
		  Session session=ssf.openSession();
		  Query q1=session.createQuery(hqlQuery);
		  
		  /**
		   * First time we are storing value in the cache 
		   */
		  q1.setCacheable(true);
		  Student st3=(Student) q1.uniqueResult();
		  System.out.println(st3.getName());
		  System.out.println("Shared between multiple sessions");
		  session.close();
		  
		  
		  System.out.println("Session closed");
		  System.out.println("New session created");
		  Session session3=ssf.openSession();
		  Query q2=session3.createQuery(hqlQuery);
		  
		  /**
		   * Second time we are fetching value from the cache 
		   */
		  q2.setCacheable(true);
		  Student st4=(Student) q2.uniqueResult();
		  System.out.println(st4.getName());

		  

	  }

}

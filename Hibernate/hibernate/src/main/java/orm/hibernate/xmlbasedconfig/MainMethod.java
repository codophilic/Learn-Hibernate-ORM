package orm.hibernate.xmlbasedconfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainMethod {

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
		  con.configure("orm/hibernate/hibernateXmlBasedConfig.cfg.xml");
	  
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
		  
		  Transaction tx=session.beginTransaction();
				  
		  
		  Coders code=new Coders();
		  code.setId(1);
		  code.setName("Harsh");
		  code.setRating(7);
		  
		  session.save(code);
		  tx.commit();
		  session.close();
		
		  
	  }
}

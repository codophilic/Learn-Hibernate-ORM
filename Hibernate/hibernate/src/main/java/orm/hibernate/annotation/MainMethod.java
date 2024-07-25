package orm.hibernate.annotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import orm.hibernate.annotation.Address.formStatus;
import orm.hibernate.annotation.onetoone.Payroll;
import orm.hibernate.annotation.onetoone.Personal;

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
	  con.configure("orm/hibernate/annotation/hibernateConfig.cfg.xml");
  
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
	  
	  /**
	   * Using session we created a new transaction
	   */
	  Transaction tx=session.beginTransaction();
	  
	  
	  Student st=new Student();
	  st.setId(1);
	  st.setName("Harsh");
	  st.setCity("Mumbai");
	  
	  /**
	   * Save or inserted student object
	   */
	  session.save(st);

	  
	  Address address=new Address();
	  address.setAddress1("abc");
	  address.setAddress2("xyz");
	  address.setForm(formStatus.PENDING);
	  address.setStores_date(new Date());
	  address.setStores_timestamp(new Date());
	  try {
		FileInputStream fis=new FileInputStream("src/main/java/orm/hibernate/annotation/image.png");
		try {
			byte[] image=new byte[fis.available()];
			address.setPhoto(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  session.save(address);
	  
	  /**
	   * Using Get Method
	   */
	  Address ad=session.get(Address.class, 1);
	  System.out.println(ad.getAddress1()+" "+ad.getAddress2());

	  /**
	   * Using Load Method
	   */
	  Address ad1=session.load(Address.class, 1);
	  System.out.println(ad1.getAddress1()+" "+ad1.getAddress2());
	  
	  Employee emp=new Employee();
	  emp.setEmpid("1");
	  emp.setDeptname("mumbai");
	  
	  EmployeeAddress empadd=new EmployeeAddress();
	  empadd.setAddress1("abc");
	  empadd.setAddress2("xyz");
	  emp.setEmpAddr(empadd);
	  session.save(emp);
	  
	  Personal ps=new Personal();
	  ps.setEmpid_personal(1);
	  ps.setFirstname("Harsh");
	  ps.setLastname("Pandya");
	  
	  Payroll py=new Payroll();
	  py.setEmpid_of_payroll(1);
	  py.setPayrate(1000);
	  ps.setPayroll(py);
	  
	  session.save(ps);
	  session.save(py);
	  
	  tx.commit();
	  
	  /**
	   * Close the resources
	   */
	  
	  session.close();
	  
	  
			  
  }
}

package orm.hibernate.annotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import orm.hibernate.annotation.Address.formStatus;
import orm.hibernate.annotation.manytomany.EmployeeProjects;
import orm.hibernate.annotation.manytomany.Projects;
import orm.hibernate.annotation.onetomany.Customer;
import orm.hibernate.annotation.onetomany.CustomerOrder;
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
	   * Using Get Method, fetch based on primary key as its where clause
	   */
	  Address ad=session.get(Address.class, 1);
	  System.out.println(ad.getAddress1()+" "+ad.getAddress2());

	  /**
	   * Using Load Method, fetch based on primary key as its where clause
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
	  ps.setPersonalEmployeesPayroll(py);
	  py.setPersonal(ps);
	  
	  session.save(ps);
	  session.save(py);
	  
	  Customer cust=new Customer();
	  cust.setCustId(1);
	  cust.setCustName("Harsh");
	  cust.setCustCity("Mumbai");
	  
	  CustomerOrder custOrder1=new CustomerOrder();
	  custOrder1.setUnqiueOrderCode(100);
	  custOrder1.setOrderNumber("123OA");
	  CustomerOrder custOrder2=new CustomerOrder();

	  custOrder2.setUnqiueOrderCode(101);
	  custOrder2.setOrderNumber("123OB");
	  List<CustomerOrder> allOrders=new ArrayList<>();
	  allOrders.add(custOrder1);
	  allOrders.add(custOrder2);
	  cust.setAllOrdersofACustomer(allOrders);
	  custOrder1.setCustomerIdhavingMultipleOrders(cust);
	  custOrder2.setCustomerIdhavingMultipleOrders(cust);
	  session.save(cust);
	  session.save(custOrder1);
	  session.save(custOrder2);
	  
	  EmployeeProjects empProject1=new EmployeeProjects();
	  empProject1.setEmpId(1);
	  empProject1.setEmpName("Harsh");
	  EmployeeProjects empProject2=new EmployeeProjects();
	  empProject2.setEmpId(2);
	  empProject2.setEmpName("Meet");	
	  
	  Projects proj1=new Projects();
	  proj1.setProjectId(404);
	  proj1.setProjectName("AI");
	  Projects proj2=new Projects();
	  proj2.setProjectId(405);
	  proj2.setProjectName("Hibernate");
	  
	  empProject1.setEmpWorkingonProjects(Arrays.asList(proj1,proj2));
	  empProject2.setEmpWorkingonProjects(Arrays.asList(proj1));
	  
	  proj1.setProjectAssignToemp(Arrays.asList(empProject1,empProject2));
	  proj2.setProjectAssignToemp(Arrays.asList(empProject2));
	  
	  session.save(empProject1);
	  session.save(empProject2);
	  session.save(proj1);
	  session.save(proj2);
	  
	  /**
	   * Cascading
	   */
	  allOrders.clear();
	  Customer cust1=new Customer();
	  cust1.setCustId(2);
	  cust1.setCustName("Meet");
	  cust1.setCustCity("Mumbai");
	  
	  CustomerOrder custOrder3=new CustomerOrder();
	  custOrder3.setUnqiueOrderCode(102);
	  custOrder3.setOrderNumber("123OC");
	  custOrder3.setCustomerIdhavingMultipleOrders(cust1);
	  CustomerOrder custOrder4=new CustomerOrder();
	  custOrder4.setUnqiueOrderCode(103);
	  custOrder4.setOrderNumber("123OD");
	  custOrder4.setCustomerIdhavingMultipleOrders(cust1);
	  CustomerOrder custOrder5=new CustomerOrder();
	  custOrder5.setUnqiueOrderCode(104);
	  custOrder5.setOrderNumber("123OE");
	  custOrder5.setCustomerIdhavingMultipleOrders(cust1);

	  allOrders.add(custOrder3);
	  allOrders.add(custOrder4);
	  allOrders.add(custOrder5);
	  cust1.setAllOrdersofACustomer(allOrders);
	  session.save(cust1);
	  /**
	   * automatically saves the child entities for CustomerOrder
	   */
	  
	  tx.commit();  
	  /**
	   * Lazy Loading, loads Personal class entities details based on
	   * primary keys as where clause
	   */
	  System.out.println("Starting the lazy loading process");
	  Personal pvalue=session.get(Personal.class, 1);
	  System.out.println("Queries are now formed and executed");
	  System.out.println(pvalue.getFirstname());
	  
	  /**
	   * Now when we call Payroll method, the queries will then executed and loads
	   * the data for it
	   */
	  System.out.println("Now lazy loading is applied on Payroll");
	  System.out.println(pvalue.getPersonalEmployeesPayroll().getPayrate());
	  
	  
	  /**
	   * Eager loading
	   */
	  System.out.println("All data are loaded once");
	  Customer custvalue=session.get(Customer.class, 1);
	  
	  
	  /**
	   * Close the resources
	   */
	  
	  session.close();
	  
	  
			  
  }
}

package orm.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import orm.hibernate.annotation.Student;
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
		   * Begin transaction
		   */
		  Transaction tx=session.beginTransaction();
		  
		  String hqlQuery="";
		  Query query=null;
		  
		  //HQL
		  /**
		   * When are selecting all columns , we don't need to specify SELECT key word
		   * when are selecting few columns , we do need to specify SELECT key word
		   * 
		   * Here Student is my Object class name, whereas in my database
		   * Student_data is the table created
		   * st here is alias
		   */
		  hqlQuery="from Student st where st.name=:x";
		  
		  /**
		   * Query is an interface
		   */
		  query=session.createQuery(hqlQuery);
		  query.setParameter("x", "Harsh");
		  List<Student> studentList=query.list();
		  for(Student e: studentList) {
			  System.out.println(e.getId());
		  }
		  
		  
		  /**
		   * Update,Delete and Join operation
		   */
		  
		  /**
		   * Using session we created a new transaction
		   */
		  hqlQuery=" update Student set id=:n where name=:x";
		  query=session.createQuery(hqlQuery);
		  query.setParameter("n", 2);
		  query.setParameter("x", "Harsh");
		  int rowsaffected=query.executeUpdate();
		  System.out.println("Updated Rows - "+rowsaffected);
		  
		  
		  hqlQuery=" delete from Student where name=:x";
		  query=session.createQuery(hqlQuery);
		  query.setParameter("x", "Harsh");
		  rowsaffected=query.executeUpdate();
		  System.out.println("Deleted Rows - "+rowsaffected);
		  tx.commit();

		  
		  /**
		   * In Joined Operation, here we use OneToOne mapping relation where payroll
		   * is a part of Personal, so using Personal instance variable
		   * we will perform join operation
		   */
		  hqlQuery="select psl.firstname,psl.lastname,prl.payrate from Personal as psl inner join psl.personalEmployeesPayroll as prl";
		  query=session.createQuery(hqlQuery);
		  
		  List<Object[]> joinResult=query.list();
		  for(Object[] obj: joinResult) {
			  System.out.println(obj[0]+" "+obj[1]+" "+obj[2]);
		  }
		  tx.begin();
		  for (int i = 1; i < 201; i++) {
			  Paginator pg=new Paginator();
			  pg.setIdval("value "+i);
			  session.save(pg);
	    	  tx.commit();
			  tx.begin();
	    	  /**
	    	   * Whenever we commit, transaction gets completed
	    	   * so we need to again start the transaction
	    	   */
		  }
		  
		  hqlQuery=" from Paginator";
		  query=session.createQuery(hqlQuery);
		  query.setFirstResult(100); // Starting from which row
		  query.setMaxResults(10); // fetch 10 values
		  
		  List<Paginator> fetchAll=query.list();
		  
		  for(Paginator objf:fetchAll) {
			  System.out.println(objf.getId());
		  }
		  
		  
		  /**
		   * Executing Native Queries
		   */
		  
		  String sqlQuery="select * from one_to_one_personal";
		  NativeQuery nq=session.createNativeQuery(sqlQuery);
		  List<Object[]> nativeList=nq.list();
		  for(Object[] objn:nativeList) {
			  System.out.println(objn[0]+" "+objn[1]);
		  }
		  
		  session.close();

	}

}

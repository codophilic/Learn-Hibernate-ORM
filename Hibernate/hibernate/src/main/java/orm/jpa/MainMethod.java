package orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainMethod {

	public static void main(String[] args) {
		
		/**
		 * Provide just configuration file persistence-unit name
		 */
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpapersistencefile");
		EntityManager em=emf.createEntityManager();
		
		/**
		 * Inserting data
		 */
		
		Workers wk=new Workers();
		wk.setId(1);
		wk.setName("Harsh");
		em.getTransaction().begin();
		em.persist(wk);
		em.getTransaction().commit();
		
		/**
		 * Find data
		 */
		
		Workers wk1=em.find(Workers.class, 1);
		System.out.println("Name: "+wk1.getName());
		
	}

}

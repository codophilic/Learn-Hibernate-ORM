package orm.hibernate.criteria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import orm.hibernate.hql.Paginator;

public class MainMethod {

	public static void main(String[] args) {
        // Get SessionFactory (Assuming you have a configured SessionFactory)
        SessionFactory sessionFactory = new Configuration().configure("orm/hibernate/hibernateConfig.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            // Create a Criteria instance
            Criteria criteria = session.createCriteria(Paginator.class);

            // Add restrictions
            criteria.add(Restrictions.gt("id", 10)); // Id greater than 30000
            criteria.add(Restrictions.ilike("idval", "%value%")); // idvalue like %value%
            // Add projections
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.sum("id"), "sumOfId")); // sum(id)

            // Execute the query
            Object results = criteria.uniqueResult();
            System.out.println("Sum of IDs - "+results);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
	}

}

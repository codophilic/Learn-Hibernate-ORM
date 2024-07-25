package orm.hibernate.annotation.onetomany;

import jakarta.persistence.*;

@Entity
@Table(name="one_to_many_customer")
public class Customer {

	@Id
	private int custId;
	
	private String custName;
	
	private String custCity;
	
	private CustomerOrder order;
	
}

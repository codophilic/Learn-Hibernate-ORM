package orm.hibernate.annotation.onetomany;

import jakarta.persistence.*;

@Entity
public class CustomerOrder {

	@Id
	private int orderNum;
	
}

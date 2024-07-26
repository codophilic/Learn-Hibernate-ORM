package orm.hibernate.annotation.onetomany;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="one_to_many_customer")
public class Customer {

	@Id
	private int custId;
	
	private String custName;
	
	private String custCity;
	
	/**
	 * Extra column will not be created in Customer class
	 */
	@OneToMany(mappedBy = "customerIdhavingMultipleOrders",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<CustomerOrder> allOrdersofACustomer;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustCity() {
		return custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	public List<CustomerOrder> getAllOrdersofACustomer() {
		return allOrdersofACustomer;
	}

	public void setAllOrdersofACustomer(List<CustomerOrder> allOrdersofACustomer) {
		this.allOrdersofACustomer = allOrdersofACustomer;
	}


	
}

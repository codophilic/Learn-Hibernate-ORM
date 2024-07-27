package orm.hibernate.annotation.onetomany;

import javax.persistence.*;
import orm.hibernate.annotation.onetomany.Customer;

@Entity
@Table(name="many_to_one_customerorder")
public class CustomerOrder {

	@Id
	private int unqiueOrderCode;
	
	private String orderNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="foreignkey_cust_id", referencedColumnName = "custId")
	private Customer customerIdhavingMultipleOrders ;

	public int getUnqiueOrderCode() {
		return unqiueOrderCode;
	}

	public void setUnqiueOrderCode(int unqiueOrderCode) {
		this.unqiueOrderCode = unqiueOrderCode;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Customer getCustomerIdhavingMultipleOrders() {
		return customerIdhavingMultipleOrders;
	}

	public void setCustomerIdhavingMultipleOrders(Customer customerIdhavingMultipleOrders) {
		this.customerIdhavingMultipleOrders = customerIdhavingMultipleOrders;
	}

	
	
}

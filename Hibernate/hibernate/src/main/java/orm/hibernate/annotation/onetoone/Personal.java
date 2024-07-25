package orm.hibernate.annotation.onetoone;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="one_to_one_personal")
public class Personal {

	@Id
	private int empid_personal;
	
	private String firstname;
	
	private String lastname;
	
	@OneToOne(mappedBy = "empid_of_payroll")
	private Payroll payroll;


	public int getEmpid_personal() {
		return empid_personal;
	}

	public void setEmpid_personal(int empid_personal) {
		this.empid_personal = empid_personal;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}
	
	
	
}

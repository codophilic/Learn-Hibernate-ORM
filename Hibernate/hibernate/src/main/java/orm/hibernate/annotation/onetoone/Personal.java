package orm.hibernate.annotation.onetoone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="one_to_one_personal")
public class Personal {

	@Id
	private int empid_personal;
	
	@Column(unique = true)
	private String firstname;
	
	private String lastname;
	
	/**
	 * Default fetching type is lazy
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="foreignkey_payroll_payrate",referencedColumnName = "payrate")
	private Payroll personalEmployeesPayroll;


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

	public Payroll getPersonalEmployeesPayroll() {
		return personalEmployeesPayroll;
	}

	public void setPersonalEmployeesPayroll(Payroll personalEmployeesPayroll) {
		this.personalEmployeesPayroll = personalEmployeesPayroll;
	}

	
	
}

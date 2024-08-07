package orm.hibernate.annotation.onetoone;

import javax.persistence.*;

@Entity
@Table(name="one_to_one_payroll")
public class Payroll {

	@Id
	private int empid_of_payroll;
	
	@Column(unique = true)
	private double payrate;
	
	@OneToOne(mappedBy = "personalEmployeesPayroll",fetch = FetchType.LAZY)
	private Personal personal;


	public double getPayrate() {
		return payrate;
	}

	public void setPayrate(double payrate) {
		this.payrate = payrate;
	}

	public int getEmpid_of_payroll() {
		return empid_of_payroll;
	}

	public void setEmpid_of_payroll(int empid_of_payroll) {
		this.empid_of_payroll = empid_of_payroll;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	
	
}

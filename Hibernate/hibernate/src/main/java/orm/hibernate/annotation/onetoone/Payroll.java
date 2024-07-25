package orm.hibernate.annotation.onetoone;

import jakarta.persistence.*;

@Entity
@Table(name="one_to_one_payroll")
public class Payroll {

	private int empid;
	
	private double payrate;

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public double getPayrate() {
		return payrate;
	}

	public void setPayrate(double payrate) {
		this.payrate = payrate;
	}
	
	
}

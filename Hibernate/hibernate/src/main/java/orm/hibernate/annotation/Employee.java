package orm.hibernate.annotation;

import jakarta.persistence.*;

@Entity
public class Employee {

	@Id
	private String empid;
	private String deptname;
	
	@Embedded
	private EmployeeAddress empAddr;
	
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public EmployeeAddress getEmpAddr() {
		return empAddr;
	}
	public void setEmpAddr(EmployeeAddress empAddr) {
		this.empAddr = empAddr;
	}
	
	
}

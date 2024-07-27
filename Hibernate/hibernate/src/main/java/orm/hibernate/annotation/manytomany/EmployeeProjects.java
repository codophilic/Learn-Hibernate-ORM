package orm.hibernate.annotation.manytomany;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "many_to_many_employeeprojects")
public class EmployeeProjects {

	@Id
	private int empId;
	
	private String empName;
	
	@ManyToMany
	@JoinTable(
			name ="joined_table_employee_project",
			joinColumns= { @JoinColumn( name="employee_ids") }, //EmployeeProject -> empid
			inverseJoinColumns = { @JoinColumn( name="project_ids") } //Projects -> projectId 
			)
	private List<Projects> empWorkingonProjects;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public List<Projects> getEmpWorkingonProjects() {
		return empWorkingonProjects;
	}

	public void setEmpWorkingonProjects(List<Projects> empWorkingonProjects) {
		this.empWorkingonProjects = empWorkingonProjects;
	}
	
	
}

package orm.hibernate.annotation.manytomany;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "many_to_many_projects")
public class Projects {

	@Id
	private int projectId;
	
	private String projectName;
	
	@ManyToMany(mappedBy = "empWorkingonProjects")
	private List<EmployeeProjects> projectAssignToemp;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<EmployeeProjects> getProjectAssignToemp() {
		return projectAssignToemp;
	}

	public void setProjectAssignToemp(List<EmployeeProjects> projectAssignToemp) {
		this.projectAssignToemp = projectAssignToemp;
	}
	
	
	
}

package com.project.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Project {
	
	private int projectId;
	private String projectName;
	private String projectDescription;
	private LocalDate startDate;
	private String projectStatus;
	private List<User> teamMembers;		//Set Data Type
	
	
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Project(int projectId, String projectName, String projectDescription, LocalDate startDate, String projectStatus,
			List<User> teamMembers) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.startDate = startDate;
		this.projectStatus = projectStatus;
		this.teamMembers = teamMembers;
	}


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


	public String getProjectDescription() {
		return projectDescription;
	}


	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public String getProjectStatus() {
		return projectStatus;
	}


	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}


	public List<User> getTeamMembers() {
		return teamMembers;
	}


	public void setTeamMembers(List<User> teamMembers) {
		this.teamMembers = teamMembers;
	}


	@Override
	public String toString() {
		return "Projects [projectId=" + projectId + ", projectName=" + projectName + ", projectDescription="
				+ projectDescription + ", startDate=" + startDate + ", projectStatus=" + projectStatus
				+ ", teamMembers=" + teamMembers + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectDescription == null) ? 0 : projectDescription.hashCode());
		result = prime * result + projectId;
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((projectStatus == null) ? 0 : projectStatus.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((teamMembers == null) ? 0 : teamMembers.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (projectDescription == null) {
			if (other.projectDescription != null)
				return false;
		} else if (!projectDescription.equals(other.projectDescription))
			return false;
		if (projectId != other.projectId)
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (projectStatus == null) {
			if (other.projectStatus != null)
				return false;
		} else if (!projectStatus.equals(other.projectStatus))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (teamMembers == null) {
			if (other.teamMembers != null)
				return false;
		} else if (!teamMembers.equals(other.teamMembers))
			return false;
		return true;
	}
	
	

}

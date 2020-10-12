package com.project.entity;

import java.sql.Date;
import java.time.LocalDate;

public class Bug {
	
	private int bugId;
	private int projectId;
	private int assignedTo;					//User Id of Developer//By default value=0
	private String bugTitle;
	
	private String bugDescription;
	private LocalDate openDate;	
	private boolean markedForClosing;		//Data type Boolean// default false
	
	private LocalDate closedOn;				// in database default value 1990-01-01
	private int closedBy;					//User Id of Manager//By default value=0
	private String bugStatus;				//Default "created"
	private String severityLevel;
	
	private int createdBy;					//User Id of Tester

	
	
	public Bug() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Bug(int bugId, int projectId, int assignedTo, String bugTitle, String bugDescription, LocalDate openDate,
			boolean markedForClosing, LocalDate closedOn, int closedBy, String bugStatus, String severityLevel,
			int createdBy) {
		super();
		this.bugId = bugId;
		this.projectId = projectId;
		this.assignedTo = assignedTo;
		this.bugTitle = bugTitle;
		this.bugDescription = bugDescription;
		this.openDate = openDate;
		this.markedForClosing = markedForClosing;
		this.closedOn = closedOn;
		this.closedBy = closedBy;
		this.bugStatus = bugStatus;
		this.severityLevel = severityLevel;
		this.createdBy = createdBy;
	}


	
	
	

	public Bug(int projectId, String bugTitle, String bugDescription,String severityLevel,
			int createdBy) {
		super();
		this.projectId = projectId;
		this.bugTitle = bugTitle;
		this.bugDescription = bugDescription;
		this.severityLevel = severityLevel;
		this.createdBy = createdBy;
	}



	public int getBugId() {
		return bugId;
	}



	public void setBugId(int bugId) {
		this.bugId = bugId;
	}



	public int getProjectId() {
		return projectId;
	}



	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}



	public int getAssignedTo() {
		return assignedTo;
	}



	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}



	public String getBugTitle() {
		return bugTitle;
	}



	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}



	public String getBugDescription() {
		return bugDescription;
	}



	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}



	public LocalDate getOpenDate() {
		return openDate;
	}



	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}



	public boolean isMarkedForClosing() {
		return markedForClosing;
	}



	public void setMarkedForClosing(boolean markedForClosing) {
		this.markedForClosing = markedForClosing;
	}



	public LocalDate getClosedOn() {
		return closedOn;
	}



	public void setClosedOn(LocalDate closedOn) {
		this.closedOn = closedOn;
	}



	public int getClosedBy() {
		return closedBy;
	}



	public void setClosedBy(int closedBy) {
		this.closedBy = closedBy;
	}



	public String getBugStatus() {
		return bugStatus;
	}



	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}



	public String getSeverityLevel() {
		return severityLevel;
	}



	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}



	public int getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + assignedTo;
		result = prime * result + ((bugDescription == null) ? 0 : bugDescription.hashCode());
		result = prime * result + bugId;
		result = prime * result + ((bugStatus == null) ? 0 : bugStatus.hashCode());
		result = prime * result + ((bugTitle == null) ? 0 : bugTitle.hashCode());
		result = prime * result + closedBy;
		result = prime * result + ((closedOn == null) ? 0 : closedOn.hashCode());
		result = prime * result + createdBy;
		result = prime * result + (markedForClosing ? 1231 : 1237);
		result = prime * result + ((openDate == null) ? 0 : openDate.hashCode());
		result = prime * result + projectId;
		result = prime * result + ((severityLevel == null) ? 0 : severityLevel.hashCode());
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
		Bug other = (Bug) obj;
		if (assignedTo != other.assignedTo)
			return false;
		if (bugDescription == null) {
			if (other.bugDescription != null)
				return false;
		} else if (!bugDescription.equals(other.bugDescription))
			return false;
		if (bugId != other.bugId)
			return false;
		if (bugStatus == null) {
			if (other.bugStatus != null)
				return false;
		} else if (!bugStatus.equals(other.bugStatus))
			return false;
		if (bugTitle == null) {
			if (other.bugTitle != null)
				return false;
		} else if (!bugTitle.equals(other.bugTitle))
			return false;
		if (closedBy != other.closedBy)
			return false;
		if (closedOn == null) {
			if (other.closedOn != null)
				return false;
		} else if (!closedOn.equals(other.closedOn))
			return false;
		if (createdBy != other.createdBy)
			return false;
		if (markedForClosing != other.markedForClosing)
			return false;
		if (openDate == null) {
			if (other.openDate != null)
				return false;
		} else if (!openDate.equals(other.openDate))
			return false;
		if (projectId != other.projectId)
			return false;
		if (severityLevel == null) {
			if (other.severityLevel != null)
				return false;
		} else if (!severityLevel.equals(other.severityLevel))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Bug [bugId=" + bugId + ", projectId=" + projectId + ", assignedTo=" + assignedTo + ", bugTitle="
				+ bugTitle + ", bugDescription=" + bugDescription + ", openDate=" + openDate + ", markedForClosing="
				+ markedForClosing + ", closedOn=" + closedOn + ", closedBy=" + closedBy + ", bugStatus=" + bugStatus
				+ ", severityLevel=" + severityLevel + ", createdBy=" + createdBy + "]";
	}

	

	
	
	

}

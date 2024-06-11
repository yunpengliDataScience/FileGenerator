package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "JOB_HISTORY")
@Entity
@Table(name = "JOB_HISTORY")
public class Job_history {


	@Id
	@Column(name = "EMPLOYEE_ID")
	private Object employeeId;

	@Id
	@Column(name = "START_DATE")
	private java.sql.Timestamp startDate;

	@Column(name = "END_DATE")
    private java.sql.Timestamp endDate;

	@Column(name = "JOB_ID")
    private String jobId;

	@Column(name = "DEPARTMENT_ID")
    private Object departmentId;


	@XmlElement(name="EMPLOYEE_ID")
    public Object getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Object employeeId) {
        this.employeeId = employeeId;
    }

	@XmlElement(name="START_DATE")
    public java.sql.Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Timestamp startDate) {
        this.startDate = startDate;
    }

	@XmlElement(name="END_DATE")
    public java.sql.Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Timestamp endDate) {
        this.endDate = endDate;
    }

	@XmlElement(name="JOB_ID")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

	@XmlElement(name="DEPARTMENT_ID")
    public Object getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Object departmentId) {
        this.departmentId = departmentId;
    }
}

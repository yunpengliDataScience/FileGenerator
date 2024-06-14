package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "JOB_HISTORY")
@Entity
@Table(name = "JOB_HISTORY")
public class JobHistory implements java.io.Serializable {

	@Id
	@Column(name = "EMPLOYEE_ID")
	@XmlElement(name="EMPLOYEE_ID")
	private Object employeeId;

	@Id
	@Column(name = "START_DATE")
	@XmlElement(name="START_DATE")
	private TimeStamp startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE")
	@XmlElement(name="END_DATE")
	@XmlSchemaType(name="date")
	private java.util.Date endDate;

	@Column(name = "JOB_ID")
	@XmlElement(name="JOB_ID")
    private String jobId;

	@Column(name = "DEPARTMENT_ID")
	@XmlElement(name="DEPARTMENT_ID")
    private Object departmentId;


    public Object getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Object employeeId) {
        this.employeeId = employeeId;
    }

	public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

	public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Object getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Object departmentId) {
        this.departmentId = departmentId;
    }

}

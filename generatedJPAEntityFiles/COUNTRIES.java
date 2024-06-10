package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "COUNTRIES")
@Entity
@Table(name = "COUNTRIES")
public class Countries {


	@Id
	private String COUNTRY_ID;

	@Column(name = "COUNTRY_NAME")
    private String COUNTRY_NAME;

	@Column(name = "REGION_ID")
    private Object REGION_ID;


	@XmlElement(name="COUNTRY_ID")
    public String getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    public void setCOUNTRY_ID(String COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }

	@XmlElement(name="COUNTRY_NAME")
    public String getCOUNTRY_NAME() {
        return COUNTRY_NAME;
    }

    public void setCOUNTRY_NAME(String COUNTRY_NAME) {
        this.COUNTRY_NAME = COUNTRY_NAME;
    }

	@XmlElement(name="REGION_ID")
    public Object getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(Object REGION_ID) {
        this.REGION_ID = REGION_ID;
    }
}

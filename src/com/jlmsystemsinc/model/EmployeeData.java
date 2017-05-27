package com.jlmsystemsinc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jlmsystemsinc.StaticValues;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class EmployeeData {
	@JsonProperty(StaticValues.Employee.NUMBER)
	private StringProperty empno;
	@JsonProperty(StaticValues.Employee.NAME)
	private StringProperty name;
	@JsonProperty(StaticValues.Employee.EMAIL)
	private StringProperty email;
	
	public EmployeeData() {}

	public EmployeeData(String empno, String name, String email) {
		this.empno = new SimpleStringProperty(empno!=null?empno:StaticValues.Employee.NUMBER+" not found");
		this.name = new SimpleStringProperty(name!=null?name:StaticValues.Employee.NAME+" not found");
		this.email = new SimpleStringProperty(email!=null?email:StaticValues.Employee.EMAIL+" not found");
	}

	public String getEmpno() {
		return empno.get();
	}

	public StringProperty getEmpnoProperty() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno.set(empno);
	}

	public String getName() {
		return name.get();
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getEmail() {
		return email.get();
	}

	public StringProperty getEmailProperty() {
		return email;
	}

	public void setEmail(String email) {
		this.email.set(email);
	}


}

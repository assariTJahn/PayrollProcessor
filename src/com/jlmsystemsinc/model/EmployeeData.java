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
	

	public EmployeeData(String empno, String name, String email) {
		this.empno = new SimpleStringProperty(empno);
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
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

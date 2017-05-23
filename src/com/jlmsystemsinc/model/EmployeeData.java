package com.jlmsystemsinc.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeData {

	private StringProperty empno;
	private StringProperty name;
	private StringProperty email;
	private StringProperty filename;
	private StringProperty ptoInformation;

	public EmployeeData(String empno, String name, String email, String filename, String ptoInformation) {
		this.empno = new SimpleStringProperty(empno);
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.filename = new SimpleStringProperty(filename);
		this.ptoInformation = new SimpleStringProperty(ptoInformation);
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
		;
	}

	public String getEmail() {
		return email.get();
	}

	public StringProperty getEmailProperty() {
		return email;
	}

	public void setEmail(String email) {
		this.email.set(email);
		;
	}

	public String getFilename() {
		return filename.get();
	}

	public StringProperty getFilenameProperty() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename.set(filename);
		;
	}

	public String getPtoInformation() {
		return ptoInformation.get();
	}

	public StringProperty getPtoInformationProperty() {
		return ptoInformation;
	}

	public void setPtoInformation(String ptoInformation) {
		this.ptoInformation.set(ptoInformation);;
	}

}

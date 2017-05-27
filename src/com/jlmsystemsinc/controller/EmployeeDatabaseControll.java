package com.jlmsystemsinc.controller;

import com.jlmsystemsinc.model.EmployeeData;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmployeeDatabaseControll extends AbstractController{

	@FXML
	private TableView<EmployeeData> employeeDatabaseTable;

	@FXML
	private TableColumn<EmployeeData, String> empnoColumn;
	@FXML
	private TableColumn<EmployeeData, String> nameColumn;
	@FXML
	private TableColumn<EmployeeData, String> emailColumn;

	@FXML
	private Label empnoLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label emailLabel;


	public EmployeeDatabaseControll() {
	}

	@FXML
	private void intialize() {
		empnoColumn.setCellValueFactory(cellData -> cellData.getValue().getEmpnoProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
	}

}

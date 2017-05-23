package com.jlmsystemsinc.controller;

import com.jlmsystemsinc.MainApp;
import com.jlmsystemsinc.model.EmployeeData;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PayrollOverviewController {

	@FXML
	private TableView<EmployeeData> employeeTable;
	@FXML
	private TableColumn<EmployeeData, String> empnoColumn;
	@FXML
	private TableColumn<EmployeeData, String> nameColumn;
	@FXML
	private TableColumn<EmployeeData, String> emailColumn;
	@FXML
	private TableColumn<EmployeeData, String> ptoColumn;
	@FXML
	private TableColumn<EmployeeData, String> fileColumn;

	@FXML
	private Label empnoLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label ptoLabel;
	@FXML
	private Label fileLabel;

	private MainApp mainApp;

	public PayrollOverviewController() {

	}

	private void intialize() {
		empnoColumn.setCellValueFactory(cellData -> cellData.getValue().getEmpnoProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
		ptoColumn.setCellValueFactory(cellData -> cellData.getValue().getPtoInformationProperty());
		fileColumn.setCellValueFactory(cellData -> cellData.getValue().getFilenameProperty());
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		employeeTable.setItems(mainApp.getEmployeeDataList());
	}
}

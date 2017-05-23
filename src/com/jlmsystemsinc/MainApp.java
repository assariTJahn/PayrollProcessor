package com.jlmsystemsinc;

import java.io.File;
import java.io.IOException;

import com.jlmsystemsinc.model.EmployeeData;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private ObservableList<EmployeeData> employeeDataList = FXCollections.observableArrayList();
	private Stage primaryStage;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("PAYROLL PROCESSOR");

		initRootLayout();
		showPayrollOverview();
	}

	private void showPayrollOverview() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PayrollOverview.fxml"));
			AnchorPane payrollOverview = loader.load();
			rootLayout.setCenter(payrollOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void initRootLayout() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<EmployeeData> getEmployeeDataList() {
		return employeeDataList;
	}

	public void setEmployeeDataList(ObservableList<EmployeeData> employeeDataList) {
		this.employeeDataList = employeeDataList;
	}
	
	public void loadEmployeeDataFromFile(File file) {
		
		
	}
	
}

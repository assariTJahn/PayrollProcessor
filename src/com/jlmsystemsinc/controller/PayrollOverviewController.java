package com.jlmsystemsinc.controller;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jlmsystemsinc.MainApp;
import com.jlmsystemsinc.StaticValues;
import com.jlmsystemsinc.model.EmployeeData;
import com.jlmsystemsinc.parser.xml.BuildXlsxFile;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

public class PayrollOverviewController extends AbstractController{
	Logger logger = LogManager.getLogger(PayrollOverviewController.class);

	String[] PTO_HEADER = { StaticValues.Pto.AVAILABLE, StaticValues.Pto.USED, StaticValues.Pto.LEFT };
	String[] EMP_HEADER = { StaticValues.Employee.NUMBER, StaticValues.Employee.NAME, StaticValues.Employee.EMAIL };
	
	@FXML
	private TableView<EmployeeData> employeeTable;
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
	@FXML
	private Label ptoLabel;
	@FXML
	private Label fileLabel;


	public PayrollOverviewController() {
	}

	@FXML
	private void initialize() {
		empnoColumn.setCellValueFactory(cellData -> cellData.getValue().getEmpnoProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
	}

	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		employeeTable.setItems(mainApp.getEmployeeDataList());
	}

	@FXML
	private void handleGetEmployeeData() {
		String[] header = EMP_HEADER;
		BuildXlsxFile bxf = new BuildXlsxFile(1, header);
		File file = fileChooserForSave("employee_data.xlsx", "Excel Files (*.xlsx)", "*.xlsx");
		// fileLabel.setText(file.getName());
		try {
			bxf.build(file);
		} catch (IOException e) {
			logger.error(e);
		}
		// fileLabel.setText(file.getName());
	}

	private FileChooser getFileChooser(String defaultFilename, String fileExtensionDescription, String extension) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName(defaultFilename);
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(fileExtensionDescription, extension);
		fileChooser.getExtensionFilters().add(extFilter);
		return fileChooser;
	}

	private File fileChooserForSave(String defaultFilename, String fileExtensionDescription, String extension) {
		return getFileChooser(defaultFilename, fileExtensionDescription, extension)
				.showSaveDialog(mainApp.getPrimaryStage());
	}

	private File fileChooserForOpen(String defaultFilename, String fileExtensionDescription, String extension) {
		return getFileChooser(defaultFilename, fileExtensionDescription, extension)
				.showOpenDialog(mainApp.getPrimaryStage());
	}

	/**
	 * open file save dialog for employee data
	 */
	@FXML
	private void handleSetEmployeeData() {
		String[] header = EMP_HEADER;
		BuildXlsxFile bxf = new BuildXlsxFile(1, header);
		ObservableList<EmployeeData> list = mainApp.getEmployeeDataList();
		bxf.parse(fileChooserForOpen("employee_data.xlsx", "Excel Files (*.xlsx)", "*.xlsx"), map -> {
			list.add(new EmployeeData(map.get(StaticValues.Employee.NUMBER), map.get(StaticValues.Employee.NAME), map.get(StaticValues.Employee.EMAIL)));
		});
		employeeTable.setItems(mainApp.getEmployeeDataList());
	}
	
	

	/**
	 * open dialog for openning pto data file
	 */
	@FXML
	private void handleSetPtoData() {
		String[] header = PTO_HEADER;
		BuildXlsxFile bxf = new BuildXlsxFile(1, header);
		try {
			bxf.build(fileChooserForOpen("pto_form.xlsx", "Excel Files (*.xlsx)", "*.xlsx"));
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * open file save dialog for pto data
	 */
	@FXML
	private void handleGetPtoData() {
		String[] header = PTO_HEADER;
		BuildXlsxFile bxf = new BuildXlsxFile(1, header);
		try {
			bxf.build(fileChooserForSave("pto_form.xlsx", "Excel Files (*.xlsx)", "*.xlsx"));
		} catch (IOException e) {
			logger.error(e);
		}
	}

}

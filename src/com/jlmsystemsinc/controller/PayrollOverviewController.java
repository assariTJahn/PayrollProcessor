package com.jlmsystemsinc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;

import com.jlmsystemsinc.MainApp;
import com.jlmsystemsinc.StaticValues;
import com.jlmsystemsinc.model.EmployeeData;
import com.jlmsystemsinc.parser.xml.BuildXlsxFile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

public class PayrollOverviewController {
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

	@FXML
	private void intialize() {
		empnoColumn.setCellValueFactory(cellData -> cellData.getValue().getEmpnoProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
	}

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		employeeTable.setItems(mainApp.getEmployeeDataList());
	}

	@FXML
	private void handleGetEmployeeData() {
		String[] header = EMP_HEADER;
		BuildXlsxFile bxf = new BuildXlsxFile(1, header.length, header);
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
		BuildXlsxFile bxf = new BuildXlsxFile(1, header.length, header);
		List<Map<String, String>> list = new ArrayList<>();
		bxf.parse(fileChooserForOpen("employee_data.xlsx", "Excel Files (*.xlsx)", "*.xlsx"), row -> {
			int idx = 0;
			Map<String, String> map = new HashMap<>();
			for (Cell cell : row) {
				map.put(header[idx++], cell.getStringCellValue());
			}
			list.add(map);
		});
	}

	/**
	 * open dialog for openning pto data file
	 */
	@FXML
	private void handleSetPtoData() {
		String[] header = PTO_HEADER;
		BuildXlsxFile bxf = new BuildXlsxFile(1, header.length, header);
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
		BuildXlsxFile bxf = new BuildXlsxFile(1, header.length, header);
		try {
			bxf.build(fileChooserForSave("pto_form.xlsx", "Excel Files (*.xlsx)", "*.xlsx"));
		} catch (IOException e) {
			logger.error(e);
		}
	}

}

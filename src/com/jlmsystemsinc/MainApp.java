package com.jlmsystemsinc;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jlmsystemsinc.controller.PayrollOverviewController;
import com.jlmsystemsinc.controller.RootLayoutController;
import com.jlmsystemsinc.model.EmployeeData;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * refer http://code.makery.ch/library/javafx-8-tutorial/part5/
 * 
 * @author
 *
 */
public class MainApp extends Application {
	private static Logger logger = LogManager.getLogger(MainApp.class);

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

			PayrollOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			logger.error(e);
		}

	}

	private void initRootLayout() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();

			File file = getEmployeeFilePath();
			if (file != null) {
				loadEmployeeDataFromFile(file);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened. The
	 * preference is read from the OS specific registry. If no such preference can
	 * be found, null is returned.
	 * 
	 * @return
	 */
	public File getEmployeeFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	public void setEmployeeFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
			primaryStage.setTitle("PayrollApp - " + file.getName());
		} else {
			prefs.remove("filepath");
			// update the stage title

			primaryStage.setTitle("PayrollApp");
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

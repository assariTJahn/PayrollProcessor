package com.jlmsystemsinc;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jlmsystemsinc.controller.AbstractController;
import com.jlmsystemsinc.controller.PayrollOverviewController;
import com.jlmsystemsinc.controller.PtoOverviewController;
import com.jlmsystemsinc.controller.RootLayoutController;
import com.jlmsystemsinc.controller.TabViewController;
import com.jlmsystemsinc.model.EmployeeData;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
	private BorderPane rootLayout;

	public MainApp() {
		// init employee data
		// try {
		// loadEmployeeDatabase();
		// } catch (IOException e) {
		// logger.error(e);
		// }
	}
	
	

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

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("PAYROLL PROCESSOR");

		initRootLayout();
		// showPayrollOverview();
		showTabViewLayout();
	}

	
	
	private <T extends Pane> T getPane(String location, Class<? extends AbstractController> clazz) {
		T pane = null;
		try {
			FXMLLoader loader = getLoader(location);
			pane = loader.load();
			(clazz.cast(loader.getController())).setMainApp(this);
		} catch (IOException e) {
			logger.error(e);
		}
		return pane;
	}
	
	
	private FXMLLoader getLoader(String location) {
		FXMLLoader loader = null;
		loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource(location));
		return loader; 
	}
	

	private void showTabViewLayout() {

		try {
			FXMLLoader loader = getLoader("view/TabViewLayout.fxml");
			TabPane tabViewLayout = loader.load();
			rootLayout.setCenter(tabViewLayout);
			TabViewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setPayrollOverview(getPane("view/PayrollOverview.fxml", PayrollOverviewController.class));
			controller.setPtoOverview(getPane("view/PtoOverview.fxml", PtoOverviewController.class));
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

package com.jlmsystemsinc.controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class RootLayoutController extends AbstractController{


	@FXML
	private void handleOpen() {
		FileChooser filechooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel Files (*.xls)", "*.xls");
		filechooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		File file = filechooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file != null) {
			mainApp.loadEmployeeDataFromFile(file);
		}

	}

	/**
	 * closes the application
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

}

package com.jlmsystemsinc.controller;

import com.jlmsystemsinc.model.PtoInformation;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PtoOverviewController extends AbstractController {

	@FXML
	private TableView<PtoInformation> ptoTable;
	@FXML
	private TableColumn<PtoInformation, String> empnoColumn;
	@FXML
	private TableColumn<PtoInformation, String> nameColumn;
	@FXML
	private TableColumn<PtoInformation, String> availableColumn;
	@FXML
	private TableColumn<PtoInformation, String> usedColumn;
	@FXML
	private TableColumn<PtoInformation, String> leftColumn;


	public PtoOverviewController() {
	}


	@FXML
	private void initialize() {

	}

}

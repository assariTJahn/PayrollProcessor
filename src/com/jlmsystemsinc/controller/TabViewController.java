package com.jlmsystemsinc.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class TabViewController extends AbstractController {

	@FXML
	private Tab payrollOverview;
	@FXML
	private Tab ptoOverView;

	@FXML
	public void initialize() {
	}


	public TabViewController() {
	}

	

	public void setPayrollOverview(AnchorPane payrollOverview) {
		 this.payrollOverview.setContent(payrollOverview);
	}


	public void setPtoOverview(AnchorPane ptoOverview) {
		this.ptoOverView.setContent(ptoOverview);
	}

}

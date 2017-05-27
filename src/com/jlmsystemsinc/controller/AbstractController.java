package com.jlmsystemsinc.controller;

import com.jlmsystemsinc.MainApp;

public class AbstractController {

	protected MainApp mainApp;

	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}

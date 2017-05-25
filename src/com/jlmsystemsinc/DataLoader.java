package com.jlmsystemsinc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.core.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlmsystemsinc.model.EmployeeData;

import javafx.collections.ObservableList;

public class DataLoader {

	private String filename;

	public DataLoader(String filename) {
		this.filename = filename;

	}

	public <T extends Object> T loadEmployeeDatabase(Class<T> t) throws JsonParseException, JsonMappingException, IOException {
		Assert.isNonEmpty(filename);
		File file = new File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException();
		} else {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(file, t);
		}
	}
	
	public void dataToJsonFile(Object wrapperObject) throws JsonGenerationException, JsonMappingException, IOException {
		File file = new File(filename);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(file, wrapperObject);
	}

	public class EmployeeDataListWrapper {
		@JsonProperty(StaticValues.Employee.LISTNAME)
		private ObservableList<EmployeeData> employeeDataList;

		public ObservableList<EmployeeData> getEmployeeDataList() {
			return employeeDataList;
		}

		public void setEmployeeDataList(ObservableList<EmployeeData> employeeDataList) {
			this.employeeDataList = employeeDataList;
		}
	}

}

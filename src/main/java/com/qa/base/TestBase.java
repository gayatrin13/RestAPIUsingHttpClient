package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	protected Properties props;
	protected int RESPONSE_CODE_200 = 200;
	protected int RESPONSE_CODE_201 = 201;
	protected int RESPONSE_CODE_400 = 400;
	protected int RESPONSE_CODE_401 = 401;
	protected int RESPONSE_CODE_500 = 500;

	public TestBase() {
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\config\\config.properties";
		System.out.println(path);
		FileInputStream fs;
		try {
			fs = new FileInputStream(path);
			props = new Properties();
			props.load(fs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

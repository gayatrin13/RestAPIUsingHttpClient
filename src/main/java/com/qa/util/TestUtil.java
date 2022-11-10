package com.qa.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

public class TestUtil {

	public static String jsonParse(JSONObject json, String name) {
		// System.out.println("Value of " + name + " is " + json.get(name).toString());
		// System.out.println(json.get("data").toString());

		return json.get(name).toString();
	}

	public static List<String> readFromCSV() throws FileNotFoundException {
		List<String> values = new ArrayList<String>();
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\data\\Users.csv";
		Scanner sc = new Scanner(new File(path));
		sc.useDelimiter("\n"); // sets the delimiter pattern

		while (sc.hasNext()) // returns a boolean value
		{
			values.add(sc.next());
		}
		sc.close(); // closes the scanner
		return values;
	}

	public static void main(String[] args) {
		try {
			readFromCSV();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

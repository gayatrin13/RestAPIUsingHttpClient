package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestHttpClient;
import com.qa.data.LocalUser;
import com.qa.data.Users;
import com.qa.util.TestUtil;

public class PostAPITest extends TestBase {
	String url;
	RestHttpClient client;
	CloseableHttpResponse response;

	@BeforeMethod
	public void setUp() {
		url = props.getProperty("url");
		client = new RestHttpClient();
	}

	@Test
	public void postAPITest() throws ClientProtocolException, IOException {
		System.out.println("*******POST API Test***********");

		// create headers map
		HashMap<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Content-Type", "application/json");

		// ********** create a java object using builder

		// user for dummy.restapiexample.com
		// Users user = Users.builder().name("Gayatri").salary(12000).age(34).build();

		// User for localhost:3000/users
		// LocalUser user =
		// LocalUser.builder().location("Delhi").name("Ishaan").ph_no(89898989).courses(new
		// String[]{"ruby","sql"}).build();
		List<String> users = TestUtil.readFromCSV();

		for (String string : users) {
			if (!string.contains("Name")) {
				System.out.println("from csv::" + string);

				String[] values = string.split(",");
				LocalUser user = LocalUser.builder().location(values[1]).name(values[0])
						.ph_no(Long.parseLong(values[2])).courses(new String[] { values[3], values[4] }).build();

				// ******create payload json object
				ObjectMapper mapper = new ObjectMapper();

				// mapper.writeValue(new File(System.getProperty("user.dir") +
				// "\\src\\main\\java\\com\\qa\\data\\Users.json"),u);

				// *************** convert POJO into json (masrshalling/serializing)
				String jsonString = mapper.writeValueAsString(user);
				System.out.println("POJO to JSON string :" + jsonString);
				response = client.postAPITest(url, jsonString, headersMap);

				String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println("EntityUtils.toString(response.getEntity() " + responseString);

				/*
				 * JSONObject obj = null; try { obj = new JSONObject(responseString);
				 * System.out.println("Response  JSON :" + obj);
				 * 
				 * } catch (JSONException e) { // TODO: handle exception }
				 */

				// ************** convert jsonString to POJO (unmarshalling/deserializing)
				// Users respUser = mapper.readValue(responseString, Users.class);
				LocalUser respUser = mapper.readValue(responseString, LocalUser.class);

				// **********validation
				System.out.println("Status code :" + response.getStatusLine().getStatusCode());
				Assert.assertEquals(response.getStatusLine().getStatusCode(), RESPONSE_CODE_201);
				Assert.assertTrue(user.getName().equals(respUser.getName()), " user names are not equal");
				// Assert.assertTrue(user.getAge() == respUser.getAge(), "User age is not same
				// ");
				// Assert.assertTrue(user.getSalary() == respUser.getSalary(), "user salary is
				// not same");

				for (Header header : response.getAllHeaders()) {
					System.out.println("response.getAllHeaders():" + header.getName() + " :" + header.getValue());
				}

			}
		}
	}
}

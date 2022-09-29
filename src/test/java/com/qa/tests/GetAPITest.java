package com.qa.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestHttpClient;

public class GetAPITest extends TestBase {
	String url;
	CloseableHttpResponse httpResponse;

	@BeforeClass
	public void setUp() {
		url = props.getProperty("url");
		System.out.println("URL :" + url);

	}

	@Test
	public void getAPITest() throws UnsupportedOperationException, IOException {
		System.out.println("*******Get API Test***********");

		RestHttpClient client = new RestHttpClient();
		try {
			httpResponse = client.getAPI(url);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 1.Fetch responseCode
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("status code :" + statusCode);
		// Assert.assertEquals(statusCode, RESPONSE_CODE_200);

		if (statusCode == RESPONSE_CODE_200) {
			// 2.Fetch JSON response
			String response = "";

			try {
				response = EntityUtils.toString(httpResponse.getEntity());
			} catch (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * BufferedReader in = new BufferedReader(new
			 * InputStreamReader(httpResponse.getEntity().getContent())); StringBuffer respo
			 * = new StringBuffer(); String inputLine; try { while ((inputLine =
			 * in.readLine()) != null) { respo.append(inputLine); } } catch (IOException e)
			 * { // TODO Auto-generated catch block e.printStackTrace(); }
			 * 
			 * System.out.println("Response :" + respo.toString());
			 */
			/*
			 * JSONObject json; try { json = new JSONObject(response.toString()); } catch
			 * (JSONException e) { // TODO: handle exception }
			 */
			JSONArray jsonArray = new JSONArray(response.toString());

			System.out.println("Response jSON :" + jsonArray);
			List<String> courses = new ArrayList<String>();
			for (Object obj : jsonArray) {
				JSONObject json = (JSONObject) obj;
				Set<String> set = json.keySet();
				for (String string : set) {
					// if the object is of type JSONArray
					if (json.get(string) instanceof JSONArray) {
						JSONArray arr = (JSONArray) json.get(string);
						for (Object object : arr) {
							courses.add(object.toString());
						}
						// if the object is of type JSONObject

					} else if (json.get(string) instanceof String) {
						// System.out.println("key: " + string + " value :" +
						// json.get(string).toString());
					}
				}

			}

			/*
			 * JSONArray jsonArray = json.getJSONArray("data");
			 * 
			 * for (Object object : jsonArray) { JSONObject jsonObj = (JSONObject) object;
			 * Set<String> it = jsonObj.keySet(); for (Iterator<String> iterator =
			 * it.iterator(); iterator.hasNext();) { String string = (String)
			 * iterator.next(); data.put(string, jsonObj.get(string).toString());
			 * System.out.println("key: " + string + " value: >> " +
			 * jsonObj.get(string).toString()); } }
			 */
			// 3.Fetch all headers
			Header[] headers = httpResponse.getAllHeaders();
			HashMap<String, String> dataMap = new HashMap<String, String>();
			for (Header header : headers) {
				dataMap.put(header.getName(), header.getValue());
			}
		}
	}

}

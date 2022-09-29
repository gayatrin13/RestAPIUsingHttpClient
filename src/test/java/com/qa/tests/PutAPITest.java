package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestHttpClient;
import com.qa.data.LocalUser;

public class PutAPITest extends TestBase {

	String url;
	RestHttpClient client;

	@BeforeMethod
	public void setUP() {

		url = props.getProperty("url") + props.getProperty("put_url");
		client = new RestHttpClient();
	}

	@Test
	public void putAPITest() throws ClientProtocolException, IOException {
		System.out.println("*******PUT API Test***********");
		ObjectMapper mapper = new ObjectMapper();
		LocalUser user = LocalUser.builder().location("Delhi").name("Ishaan").ph_no(77777777).build();

		// String json = " {\r\n" + " \"name\": \"gayatri\",\r\n" + " \"location\":
		// \"mumbai\",\r\n"
		// + " \"ph_no\": 89898989,\r\n" + " \"courses\": null,\r\n" + " \"id\": 17\r\n"
		// + " }";
		CloseableHttpResponse response = client.putAPITest(url, mapper.writeValueAsString(user));
		System.out.println("status code:" + response.getStatusLine().getStatusCode());

		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
		System.out.println("EntityUtils.toString(response.getEntity() " + responseString);

		LocalUser respUser = mapper.readValue(responseString, LocalUser.class);

		// validation
		System.out.println("Status code :" + response.getStatusLine().getStatusCode());
		Assert.assertEquals(response.getStatusLine().getStatusCode(), RESPONSE_CODE_200);
		Assert.assertTrue(user.getName().equals(respUser.getName()), " user names are not equal");
	}

}

package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestHttpClient;

public class DeleteAPITest extends TestBase {
	String url;
	RestHttpClient client;

	@BeforeMethod
	public void setUP() {
		url = props.getProperty("url") + props.getProperty("put_url");
		client = new RestHttpClient();
	}

	@Test
	public void deleteAPITest() throws ClientProtocolException, IOException {
		
		System.out.println("*******Delete API Test***********");

		CloseableHttpResponse response = client.deleteAPITest(url);
		System.out.println("response code :" + response.getStatusLine().getStatusCode());
		Assert.assertTrue(response.getStatusLine().getStatusCode() == RESPONSE_CODE_200);

	}
}

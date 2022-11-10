package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestHttpClient {
	public RestHttpClient() {

	}

	public CloseableHttpResponse getAPI(String url) throws ClientProtocolException, IOException {
		System.out.println("RestHttpClient.getAPI() :" + url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		get.setHeader("Content-Type", "application/json");
		CloseableHttpResponse httpResponse = httpClient.execute(get);
		return httpResponse;
	}

	public CloseableHttpResponse postAPITest(String url, String entity, HashMap<String, String> headers)
			throws ClientProtocolException, IOException {
		System.out.println("RestHttpClient.postAPITest() :" + url);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost postHttp = new HttpPost(url);

		postHttp.setEntity(new StringEntity(entity));
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			postHttp.setHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse response = httpClient.execute(postHttp);
		return response;
	}

	public CloseableHttpResponse putAPITest(String url, String entity) throws ClientProtocolException, IOException {
		System.out.println("Entity :" + entity);
		CloseableHttpClient httpCl = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(new StringEntity(entity));
		httpPut.setHeader("Content-Type", "application/json");
		return httpCl.execute(httpPut);

	}

	public CloseableHttpResponse deleteAPITest(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpDelete delete = new HttpDelete(url);
		return client.execute(delete);

	}
}

/*
 * This file (WebhookService.java), part of Minecraft to Discord Plugin by AL3X_69, is licensed under CC BY-SA 4.0. 
 * To view a copy of this license, visit https://creativecommons.org/licenses/by-sa/4.0
 * You are free to:
 * -> Share — copy and redistribute the material in any medium or format
 * -> Adapt — remix, transform, and build upon the material
 *            for any purpose, even commercially.
 *
 * This license is acceptable for Free Cultural Works.
 * The licensor cannot revoke these freedoms as long as you follow the license terms.
 */
package fr.alex6.mc2discord.discord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class WebhookService {
	public static void postWebhook(String url, JSONObject json) throws ClientProtocolException, IOException {
		CloseableHttpClient http = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		StringEntity params = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
		request.setEntity(params);
		CloseableHttpResponse response = http.execute(request);
		if ((int)(response.getStatusLine().getStatusCode() / 100) != 2) {
			System.err.print(response.getStatusLine().getStatusCode());
			System.err.println(response.getStatusLine().getReasonPhrase());
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		    StringBuffer sb = new StringBuffer();
		    String str;
		    while((str = reader.readLine())!= null){
		    	sb.append(str);
		    }
		    System.err.println(sb.toString());
		}
		http.close();
	}
	
	public static int getWebhookStatus(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient http = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response = http.execute(request);
		return response.getStatusLine().getStatusCode();
	}
}

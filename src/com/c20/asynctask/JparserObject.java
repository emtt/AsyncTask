package com.c20.asynctask;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class JparserObject {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	static JSONArray jObjArr = null;
	static Context context;
	
	public JparserObject(Context _context){
		context = _context;
	}

	public JSONObject getJSONFromUrl(String url) {

		//HTTP request
		try {
			//En Lollipop
			URL _url = new URL(url);
			HttpURLConnection h = (HttpURLConnection) _url.openConnection();
			
			//Pre LolliPop
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpPost = new HttpGet(url);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				 sb.append(line + "\n");
			}
			
			json = sb.toString();
			is.close();
			
		} catch (Exception e) {
			Log.e("Buffer Error", "Error convirtiendo resultado " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error al parsear " + e.toString());
			Log.e("JSON Parser", "Error al parsear " + json.toString());
		}

		// return JSON String
		return jObj;

	}
}
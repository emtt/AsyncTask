package com.c20.asynctask;

/*
 * 19 Ago 2015
 * @emt http://mobilize.mx
 * 
 * AsynkTaskService
 * 
 * Clase que extiende a AsynkTask y devuelve
 * un objeto de JSON
 * 
 * params: Context, AsynkTaskListener
 * 
 * return JSONObject
 * 
 */

import org.json.JSONObject;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AsynkTaskService extends AsyncTask<String, String, JSONObject>{

	AsynkTaskListener listener = null;
	Context context;

	public AsynkTaskService(Context _context, AsynkTaskListener _listener){
		listener = _listener;
		context = _context;
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected JSONObject doInBackground(String... urls) {

		JparserObject jParser = new JparserObject(context);
		Log.wtf("JSON URL", ""+ urls[0].toString());
		JSONObject json = jParser.getJSONFromUrl(urls[0].toString());
		return json;

	}

	@Override
	protected void onPostExecute(JSONObject json) {
		Log.wtf("AsynkTaskService", "TASK COMPLETE");
		if(json != null){
			listener.onTaskCompleted(json);
		}

	}

}
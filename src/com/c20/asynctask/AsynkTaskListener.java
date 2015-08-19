package com.c20.asynctask;

/*
 * 19 Ago 2015
 * @emt http://mobilize.mx
 * 
 * AsynkTaskListener
 * 
 * Interface que devuelve objeto Json
 * 
 */
import org.json.JSONObject;

public interface AsynkTaskListener {
	 void onTaskCompleted(JSONObject json);
}



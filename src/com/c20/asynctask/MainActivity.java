package com.c20.asynctask;

/*
 * 19 Ago 2015
 * @emt http://mobilize.mx
 * 
 * MainActivity
 * 
 */
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends Activity implements AsynkTaskListener{

	static private String TAG = MainActivity.class.getSimpleName();
	private static String TITULO 			= "titulo";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	
    	//EJECUTAMOS ASYNCTASK
    	//LLAMANDO A UN ARCHIVO JSON EN SERVIDOR
    	new AsynkTaskService(getApplicationContext(), this).execute(new String[] {
				"http://mobilize.mx/demos/curso_android_5_sept.json"
    	});
    }

    
	@Override
	public void onTaskCompleted(JSONObject json) {
		// TODO MÉTODO CREADO POR LA IMPLEMENTACIÓN:AsynkTaskListener
		
		try {
			Log.d(TAG, json.getString(TITULO).toString());
			//MOSTRAR RESULTADO EN UI
			TextView txtResultado = (TextView) this.findViewById(R.id.txtResultado);
			txtResultado.setText("Resultado Json remoto: " + json.getString(TITULO).toString());
			
			//MOSTRAR RESULTADO EN LOGCAT
			Log.d(TAG, "Resultado JSon: " + json.toString());
			
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

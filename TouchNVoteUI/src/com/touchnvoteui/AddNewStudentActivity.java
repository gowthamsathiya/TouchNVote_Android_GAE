package com.touchnvoteui;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddNewStudentActivity extends Activity {

	//Button addExcelButton;
	Button oneStudentButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_student);
		//addExcelButton = (Button)findViewById(R.id.addExcelButton);
		oneStudentButton = (Button)findViewById(R.id.oneStudentButton);
		//addExcelButton.setOnClickListener(excelSaveListener);
		oneStudentButton.setOnClickListener(studentSaveListener);
	}
	
	private OnClickListener studentSaveListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			//Intent studentstore = new Intent(AddNewStudentActivity.this,OneStudentEnrollActivity.class);
			//startActivity(studentstore);
			new ClassAvailableTask().execute();
		}
		
	};
	/*
	private OnClickListener excelSaveListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent excelsave = new Intent(AddNewStudentActivity.this,StoreExcelStudents.class);
			startActivity(excelsave);
		}
		
	};
	*/
	private class ClassAvailableTask extends AsyncTask<Void,Void,String>{
		JSONArray jsonclasslist;
		private String result;
		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		
        		String fullPath = new String(TNVMainActivity.SERVICE_URL);
    			//fullPath+="store_user_details?userdetails=";
        		fullPath+="class_available";
    			//fullPath+=sendUserDetailsjson;
        		Log.i("path",fullPath.toString());
				DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpGet getRequest = new HttpGet(fullPath);
	            //getRequest.addHeader("accept", "application/json");
	            HttpResponse response = httpClient.execute(getRequest);
	            ParseResponse presponse=new ParseResponse();
        		result = presponse.getResult(response).toString();
	            //result = getResult(response).toString();
	            Log.i("responsetext", result);
	            //jsonclasslist = new JSONArray(result);
	            httpClient.getConnectionManager().shutdown();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.i("error :", "inside Exception");
				e.printStackTrace();
			}
			return result;
		
		}
		protected void onPostExecute(String result){
			//pd.dismiss();
			Intent registerIntent = new Intent(AddNewStudentActivity.this,OneStudentEnrollActivity.class);
			registerIntent.putExtra("classlist", result);
			startActivity(registerIntent);
		}
			
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_class, menu);
		return true;
	}

}

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

public class ProfessorMainActivity extends Activity {

	Button addClassButton;
	Button addStudentButton;
	Button startQuizButton;
	Button postTheQuizButton;
	Button viewResultButton;
	private String result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_professor_main);
		addClassButton = (Button)findViewById(R.id.addClassButton);
		addStudentButton = (Button)findViewById(R.id.addStudentButton);
		startQuizButton = (Button)findViewById(R.id.postQuizButton);
		postTheQuizButton = (Button)findViewById(R.id.postTheQuizButton);
		viewResultButton = (Button)findViewById(R.id.viewResultButton);
		addClassButton.setOnClickListener(callnewclass);
		addStudentButton.setOnClickListener(callAddStudent);
		startQuizButton.setOnClickListener(startQuizListener);
		postTheQuizButton.setOnClickListener(postTheQuizListener);
		viewResultButton.setOnClickListener(viewResultListener);
		
		
	}

	private OnClickListener callnewclass = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			Intent newclass= new Intent(ProfessorMainActivity.this,AddNewClassActivity.class);
			startActivity(newclass);
		}
		
	};
	
	private OnClickListener callAddStudent = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			Intent newclass= new Intent(ProfessorMainActivity.this,AddNewStudentActivity.class);
			startActivity(newclass);
		}
		
	};
	
	private OnClickListener startQuizListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			new ClassAvailableTask().execute();
		}
		
	};
	
	private OnClickListener postTheQuizListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			new postTheQuizTask().execute();
		}
		
	};
	
	private OnClickListener viewResultListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			new QuizTitleListTask().execute();
		}
		
	};
	
	private class ClassAvailableTask extends AsyncTask<Void,Void,String>{
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
			Intent registerIntent = new Intent(ProfessorMainActivity.this,PostQuizActivity.class);
			registerIntent.putExtra("classlist", result);
			startActivity(registerIntent);
		}
			
	}
	
	private class postTheQuizTask extends AsyncTask<Void,Void,String>{
		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		
        		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="quiz_title_list";
        		Log.i("path",fullPath.toString());
				DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpGet getRequest = new HttpGet(fullPath);
	            HttpResponse response = httpClient.execute(getRequest);
	            ParseResponse presponse=new ParseResponse();
        		result = presponse.getResult(response).toString();
	            Log.i("responsetext", result);
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
			Intent registerIntent = new Intent(ProfessorMainActivity.this,ClassSelectionToPost.class);
			registerIntent.putExtra("quiztitlelist", result);
			startActivity(registerIntent);
		}
			
	}
	
	private class QuizTitleListTask extends AsyncTask<Void,Void,String>{
		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		
        		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="entire_quiz_list";
        		Log.i("path",fullPath.toString());
				DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpGet getRequest = new HttpGet(fullPath);
	            HttpResponse response = httpClient.execute(getRequest);
	            ParseResponse presponse=new ParseResponse();
        		result = presponse.getResult(response).toString();
	            Log.i("responsetext", result);
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
			Intent registerIntent = new Intent(ProfessorMainActivity.this,ResultTitleSelectionActivity.class);
			registerIntent.putExtra("quiztitlelist", result);
			startActivity(registerIntent);
		}
			
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.professor_main, menu);
		return true;
	}

}

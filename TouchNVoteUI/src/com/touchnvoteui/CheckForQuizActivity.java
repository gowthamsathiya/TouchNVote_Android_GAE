package com.touchnvoteui;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CheckForQuizActivity extends Activity {

	private String username;
	private String userclass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_for_quiz);
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			username = (String) extras.get("username");
			userclass = (String) extras.get("userclass");
		}
		Button checkForQuizButton = (Button)findViewById(R.id.checkForQuizButton);
		checkForQuizButton.setOnClickListener(buttonListener);
	}

	private OnClickListener buttonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			new GetPoll().execute();
		}
		
	};

	
	private class GetPoll extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			try {
	       		String fullPath1 = new String(TNVMainActivity.SERVICE_URL);
        		fullPath1+="checkandsendquestions?"+"class="+userclass;
        		fullPath1+="&username="+username;
        		Log.i("CheckForQuizActivity fullpath", fullPath1);
        		DefaultHttpClient httpClient1 = new DefaultHttpClient();
        		HttpGet getRequest1 = new HttpGet(fullPath1);
        		HttpResponse response1 = httpClient1.execute(getRequest1);
        		ParseResponse presponse1=new ParseResponse();
        		String result1 = presponse1.getResult(response1).toString();
        		Log.i("Result in main activity", result1);
        		httpClient1.getConnectionManager().shutdown();
        		
        		Intent goTOStudentMainActivity = new Intent(CheckForQuizActivity.this,StudentMainActivity.class);
        		goTOStudentMainActivity.putExtra("question", result1);
        		goTOStudentMainActivity.putExtra("username", username);
				startActivity(goTOStudentMainActivity);
				finish();
			}
			catch (ClientProtocolException e) {
				Log.i("Main Activity exception", "clientprotocolexep");
				e.printStackTrace();
				
			}
			catch (IOException e) {
				Log.i("Main Activity exception", "ioexception");
				e.printStackTrace();
			} 
			catch(Exception e){
				Log.i("Main Activity exception", "exception");
				e.printStackTrace();
			}
			return null;
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_for_quiz, menu);
		return true;
	}

}

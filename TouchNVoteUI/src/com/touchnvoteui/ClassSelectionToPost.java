package com.touchnvoteui;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ClassSelectionToPost extends Activity {

	private String quizlist;
	Spinner quizListSpinner;
	Button postQuizButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_selection_to_post);
		Bundle quiztitlelist = getIntent().getExtras();
		if (quiztitlelist != null) {
			quizlist = quiztitlelist.getString("quiztitlelist");
		}
		quizListSpinner = (Spinner)findViewById(R.id.quizListSpinner);

		if(quizlist!=null && !quizlist.equals("null")){
			List<String> quizlistarray = new ArrayList<String>();
			if(quizlist.contains("&&")){
				String[] quizfulllist = quizlist.split("&&");
				int i=0;
				for(int item=0;item<quizfulllist.length;item++){
					i++;
					Log.i("result list "+i,quizfulllist[item]);
					quizlistarray.add(quizfulllist[item]);
				}
			}
			else
				quizlistarray.add((String) quizlist);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ClassSelectionToPost.this, android.R.layout.simple_spinner_item , quizlistarray);
			quizListSpinner.setAdapter(adapter);	
			adapter.notifyDataSetChanged();
		}
		else{
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ClassSelectionToPost.this, android.R.layout.simple_spinner_item ,new String[] {"No test started"});
			quizListSpinner.setAdapter(adapter);
		}
		
		postQuizButton = (Button) findViewById(R.id.postQuizButton);
		postQuizButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				/*
				Intent quizpost = new Intent(ClassSelectionToPost.this,PostQuizQuestionActivity.class);
				quizpost.putExtra("title", quizListSpinner.getSelectedItem().toString());
				startActivity(quizpost);				
				*/
				new postQuizTask().execute();
			}
			
		});
		
	}
	
	private class postQuizTask extends AsyncTask<Void,Void,String>{

		private String result;

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				//addQuestion();
	       		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="check_question?"+"title="+quizListSpinner.getSelectedItem().toString();
        		Log.i("ClassSelectionToPost fullpath", fullPath);
        		DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpGet getRequest = new HttpGet(fullPath);
        		HttpResponse response = httpClient.execute(getRequest);
        		ParseResponse presponse=new ParseResponse();
        		result = presponse.getResult(response).toString();
        		httpClient.getConnectionManager().shutdown();
			}
			catch (ClientProtocolException e) {
				Log.i("ForgotPasswordexception", "clientprotocolexep");
				e.printStackTrace();
			}
			catch (IOException e) {
				Log.i("ForgotPassword exception", "ioexception");
				e.printStackTrace();
			} 
			catch(Exception e){
				Log.i("ForgotPassword exception", "exception");
				e.printStackTrace();
			}
			return result;
		}
		
		protected void onPostExecute(String result){
			if(result.equals("false")){
				Log.i("result in classelectiontopost :", "false");
				Intent quizpost = new Intent(ClassSelectionToPost.this,PostQuizQuestionActivity.class);
				quizpost.putExtra("title", quizListSpinner.getSelectedItem().toString());
				startActivity(quizpost);
				finish();
			}
			else{
				Log.i("result in classelectiontopost:", "true");
				AlertDialog alert =new AlertDialog.Builder(ClassSelectionToPost.this).create();
				alert.setMessage("This quiz is already posted");
				alert.show();
			}
			
		}

	}
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.post_question, menu);
		return true;
	}

}

package com.touchnvoteui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PostQuizActivity extends Activity {

	Spinner classListSpinner;
	Spinner monthClassSpinner;
	Spinner yearClassSpinner;
	EditText dateEditText;
	EditText classTitleEditText;
	Button addClassQuizButton;
	String classlist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_quiz);
		classListSpinner = (Spinner)findViewById(R.id.classListSpinner);
		monthClassSpinner = (Spinner)findViewById(R.id.monthClassSpinner);
		yearClassSpinner = (Spinner)findViewById(R.id.yearClassSpinner);
		dateEditText = (EditText)findViewById(R.id.dateEditText);
		classTitleEditText = (EditText)findViewById(R.id.classTitleEditText);
		addClassQuizButton = (Button)findViewById(R.id.addClassQuizButton);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			classlist = extras.getString("classlist");
		}
		if(classlist!=null && !classlist.equals("null")){
			List<String> fclasslistarray = new ArrayList<String>();
			if(classlist.contains("&&")){
				String[] classlistarray = classlist.split("&&");
				int i=0;
				for(int item=0;item<classlistarray.length;item++){
					i++;
					Log.i("result list "+i,classlistarray[item]);
					fclasslistarray.add(classlistarray[item]);
				}
			}
			else
				fclasslistarray.add((String) classlist);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostQuizActivity.this, android.R.layout.simple_spinner_item , fclasslistarray);
			classListSpinner.setAdapter(adapter);	
			adapter.notifyDataSetChanged();
		}
		else{
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostQuizActivity.this, android.R.layout.simple_spinner_item ,new String[] {"No class available"});
			classListSpinner.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
		addClassQuizButton.setOnClickListener(saveQuizListener);
		
	}
	
	Date stdate = null;
	Date eddate = null;
	Date examdate = null;
	
	private OnClickListener saveQuizListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			Log.i("date :", dateEditText.getText().toString().trim());
			Log.i("date :", classTitleEditText.getText().toString().trim());
			if(!dateEditText.getText().toString().trim().equals("")&&!classTitleEditText.getText().toString().trim().equals("")){
				Log.i("postquizactivity test", "validated");
				if(Integer.parseInt(dateEditText.getText().toString())<=31){
					String selectedclass = (String) classListSpinner.getSelectedItem();
					if(selectedclass.contains("_")){
						String[] splitarray = selectedclass.split("_");
						String startdate = splitarray[1];
						String enddate =  splitarray[2];
						
						try {
							stdate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(startdate);
							eddate =new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(enddate);
							examdate = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH).parse(dateEditText.getText().toString()+"-"+monthClassSpinner.getSelectedItem()+"-"+yearClassSpinner.getSelectedItem());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if(examdate.before(stdate) || examdate.after(eddate)){
							AlertDialog alert = new AlertDialog.Builder(PostQuizActivity.this).create();
							alert.setMessage("Sorry, exam date should be within course");
							alert.show();
						}
						else{
							new PostQuizTask().execute();
						}
					}
				
					else{
						AlertDialog alert = new AlertDialog.Builder(PostQuizActivity.this).create();
						alert.setMessage("No class opened. Can't register");
						alert.show();
					}
				}
				else{
					AlertDialog alert = new AlertDialog.Builder(PostQuizActivity.this).create();
					alert.setMessage("Invalid exam date!");
					alert.show();
				}
		}
		else{
			AlertDialog alert = new AlertDialog.Builder(PostQuizActivity.this).create();
			alert.setMessage("Fields cant be empty");
			alert.show();
		}
	}
		
	};

	private class PostQuizTask extends AsyncTask<Void,Void,String>{

		private String result;

		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="post_quiz?"+"quizId="+classListSpinner.getSelectedItem().toString()+"_"+dateEditText.getText().toString()+"-"+monthClassSpinner.getSelectedItem().toString()+"-"+yearClassSpinner.getSelectedItem().toString()+"&"+"quizTitle="+classTitleEditText.getText().toString().replace(" ", "_");
        		Log.i("PostQuizActivity fullpath", fullPath);
        		DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpGet getRequest = new HttpGet(fullPath);
        		HttpResponse response = httpClient.execute(getRequest);
        		ParseResponse presponse=new ParseResponse();
        		result = presponse.getResult(response).toString();
        		httpClient.getConnectionManager().shutdown();
			}
			catch (ClientProtocolException e) {
				Log.i("PostQuizActivity", "clientprotocolexep");
				e.printStackTrace();
			}
			catch (IOException e) {
				Log.i("PostQuizActivity exception", "ioexception");
				e.printStackTrace();
			} 
			catch(Exception e){
				Log.i("PostQuizActivity exception", "exception");
				e.printStackTrace();
			}
			return result;
		}
		
		protected void onPostExecute(String result){
			if(result.equals("true")){
				/*
				AlertDialog.Builder passedalert=new AlertDialog.Builder(PostQuizActivity.this);
				passedalert.setMessage("Poll posted successfully!");
				passedalert.show();
				*/
				Toast.makeText(getApplicationContext(), "Poll posted successfully!",
						   Toast.LENGTH_LONG).show();
				finish();
			}
			else{
				AlertDialog.Builder passedalert=new AlertDialog.Builder(PostQuizActivity.this);
				passedalert.setMessage(result);
				passedalert.show();
			}
	}
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_quiz, menu);
		return true;
	}

}

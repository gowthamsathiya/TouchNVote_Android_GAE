package com.touchnvoteui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class OneStudentEnrollActivity extends Activity {

	EditText netIdSEEditText;
	Spinner classSpinner;
	Button addStudentButton;
	private String classlist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one_student_enroll);
		netIdSEEditText = (EditText)findViewById(R.id.netIdSEEditText);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			classlist = extras.getString("classlist");
		}
		classSpinner = (Spinner)findViewById(R.id.classSpinnerOneStudent);
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
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(OneStudentEnrollActivity.this, android.R.layout.simple_spinner_item , fclasslistarray);
			classSpinner.setAdapter(adapter);	
			adapter.notifyDataSetChanged();
		}
		else{
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(OneStudentEnrollActivity.this, android.R.layout.simple_spinner_item ,new String[] {"No class available"});
			classSpinner.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
		addStudentButton = (Button)findViewById(R.id.addStudentButton);
		addStudentButton.setOnClickListener(saveStudentListener);
	}

	private boolean validateNetId(){
		boolean valid = true;
		boolean hasDigit;
		if(netIdSEEditText.getText().toString().length()==7){
		String nid=netIdSEEditText.getText().toString();
		String firstsubnid=nid.substring(0, 2);
		String secondsubnid=nid.substring(3, netIdSEEditText.getText().toString().length());
		Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
		Matcher matcher = regex.matcher(netIdSEEditText.getText().toString());
		if (matcher.find()){
		    return false;
		}
		else{
		for(char c:firstsubnid.toCharArray()){
			if(hasDigit = Character.isDigit(c)){
				valid=false;
                break;
            }
		}
		for(char c:secondsubnid.toCharArray()){
			if(!(hasDigit = Character.isDigit(c))){
				valid=false;
                break;
            }
		}
		return valid;
		}
		}
		else{
			return false;
		}
	}
	
	private OnClickListener saveStudentListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			if(validateNetId()){
				if(!classSpinner.getSelectedItem().toString().equalsIgnoreCase("No class available")){
					new saveStudentHandler().execute();
				}
				else{
					AlertDialog alert = new AlertDialog.Builder(OneStudentEnrollActivity.this).create();
					alert.setMessage("Generate a class to get registered");
					alert.show();
				}
			}
			else{
				AlertDialog alert = new AlertDialog.Builder(OneStudentEnrollActivity.this).create();
				alert.setMessage("Enter a valid net id");
				alert.show();
			}
		}
		
	};
	
	private class saveStudentHandler extends AsyncTask<Void,Void,String>{

		private String result;

		@Override
		protected String doInBackground(Void... params) {
			
			try {
	       		
        		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="add_student_to_class?"+"username="+netIdSEEditText.getText().toString()+"&";
        		fullPath+="classid="+classSpinner.getSelectedItem().toString();
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
				Log.i("error :", "inside Exception");
				e.printStackTrace();
			}

			return result;
			
		}
		
		protected void onPostExecute(String result){
			if(result.trim().equals("true")){
				AlertDialog existalert=new AlertDialog.Builder(OneStudentEnrollActivity.this).create();
        		existalert.setMessage("Registered Successfully");
        		existalert.show();
			}
			else{
				AlertDialog.Builder existalert=new AlertDialog.Builder(OneStudentEnrollActivity.this);
        		existalert.setMessage(result);
        		existalert.show();
			}
		}
		}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.one_student_enroll, menu);
		return true;
	}

}

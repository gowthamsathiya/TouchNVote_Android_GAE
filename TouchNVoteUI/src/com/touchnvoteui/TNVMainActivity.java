package com.touchnvoteui;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TNVMainActivity extends Activity {

	//public final static String SERVICE_URL = "http://10.0.2.2:8888/";
	public final static String SERVICE_URL = "http://1.touchnvote.appspot.com/";
	EditText userNameEditText;
	EditText passwordEditText;
	Button signINButton;
	Button fPasswordButton;
	Button registerButton;
	private ProgressBar pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tnvmain);
		//setContentView(R.layout.activity_add_new_class);
		userNameEditText=(EditText)findViewById(R.id.userNameEditText);
		passwordEditText=(EditText)findViewById(R.id.passwordEditText);
		signINButton=(Button)findViewById(R.id.signInButton);
		signINButton.setOnClickListener(signinButtonListener);
		registerButton=(Button)findViewById(R.id.registerButton);
		registerButton.setOnClickListener(registerButtonListener);
		fPasswordButton=(Button)findViewById(R.id.fPasswordButton);
		fPasswordButton.setOnClickListener(fPasswordListener);
		//pd = (ProgressBar)findViewById(R.id.marker_progress_login);
		//pd.setVisibility(View.INVISIBLE);
	}
	
	
	private OnClickListener fPasswordListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			Intent registerIntent = new Intent(TNVMainActivity.this,ForgotPasswordActivity.class);
			startActivity(registerIntent);
		}
		
	};

	
	
	private OnClickListener registerButtonListener=new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			//pd = ProgressDialog.show(getApplicationContext(), "Registration", "Preparing to register");
			new ClassAvailableTask().execute();
			
		}
		
	};
	public String result;
	
	private class ClassAvailableTask extends AsyncTask<Void,Void,String>{
		JSONArray jsonclasslist;
		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		//pd.setVisibility(View.VISIBLE);
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
				Log.i("error :", "inside Exception");
				e.printStackTrace();
			}
			return result;
		
		}
		protected void onPostExecute(String result){
			//pd.dismiss();
       		//pd.setVisibility(View.INVISIBLE);
			Intent registerIntent = new Intent(TNVMainActivity.this,RegisterActivity.class);
			registerIntent.putExtra("classlist", result);
			startActivity(registerIntent);
		}
			
	}
	
	private boolean validateEntries(){
		if(userNameEditText.getText().toString().trim().equals("") ||	passwordEditText.getText().toString().trim().equals("")){
			return false;
		}
		else 
			return true;
	}
	
	private OnClickListener signinButtonListener=new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			if(!validateEntries()){
				AlertDialog alert=new AlertDialog.Builder(TNVMainActivity.this).create();
				alert.setMessage("Sorry, fields can't be empty");
				alert.show();
			}
			else if(!validateNetId()){
				Log.i("Error", "Invalid ");
				AlertDialog netidalert=new AlertDialog.Builder(TNVMainActivity.this).create();
				netidalert.setMessage("Invalid net id entered");
				netidalert.show();
        		
			}
			else{
				if(InternetConnectivityCheck.checkINetConnection(TNVMainActivity.this))
					new UserLoginTask().execute();
				else{
					Toast.makeText(getApplicationContext(), "No internet connectivity found", Toast.LENGTH_SHORT).show();
				}
					
			}
		}
		
	};
	
	private boolean validateNetId(){
		boolean valid = true;
		@SuppressWarnings("unused")
		boolean hasDigit;
		if(userNameEditText.getText().toString().length()==7){
			String nid=userNameEditText.getText().toString();
			String firstsubnid=nid.substring(0, 2);
			String secondsubnid=nid.substring(3, userNameEditText.getText().toString().length());
			Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
			Matcher matcher = regex.matcher(userNameEditText.getText().toString());
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tnvmain, menu);
		return true;
	}

	private class UserLoginTask extends AsyncTask<Void,Void,String>{

		private String result;
		private String result1;

		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		//pd.setVisibility(View.VISIBLE);
	       		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="login_user_details?"+"username="+userNameEditText.getText().toString()+"&";
        		fullPath+="password="+passwordEditText.getText().toString();
        		Log.i("TNVMainActivity fullpath", fullPath);
        		DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpGet getRequest = new HttpGet(fullPath);
        		HttpResponse response = httpClient.execute(getRequest);
        		ParseResponse presponse=new ParseResponse();
        		result = presponse.getResult(response).toString();
        		//result = response.getEntity().getContent().toString();
        		Log.i("Result in main activity", result);
        		httpClient.getConnectionManager().shutdown();
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
			return result;
		}
		
		protected void onPostExecute(String result){
			if(result.contains("true")){
				if(Integer.parseInt((String) result.subSequence(result.length()-1, result.length()))==1){
				String id[] = result.split("_");
				String userclass = id[1]+"_"+id[2]+"_"+id[3]+"_"+id[4];
				Intent goTOCheckQuiz = new Intent(TNVMainActivity.this,CheckForQuizActivity.class);
				goTOCheckQuiz.putExtra("username", userNameEditText.getText().toString());
				goTOCheckQuiz.putExtra("userclass", userclass);
				startActivity(goTOCheckQuiz);
				finish();
				}
				else{
					Intent goTOProfessorMainActivity = new Intent(TNVMainActivity.this,ProfessorMainActivity.class);
					goTOProfessorMainActivity.putExtra("username", userNameEditText.getText().toString());
					startActivity(goTOProfessorMainActivity);
					finish();
				}
			}
			else{
				//pd.setVisibility(View.INVISIBLE);
				AlertDialog.Builder failedalert=new AlertDialog.Builder(TNVMainActivity.this);
				failedalert.setMessage("Invalid username or password!");
				failedalert.show();
				userNameEditText.setText("");
				passwordEditText.setText("");
			}
		}
	}
}

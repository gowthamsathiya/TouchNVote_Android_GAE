package com.touchnvoteui;

import java.io.IOException;
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
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordActivity extends Activity {

	EditText username;
	Button requestbutton;
	String result=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password);
		username=(EditText) findViewById(R.id.userNameEditText);
		requestbutton=(Button) findViewById(R.id.reqPasswordButton);
		requestbutton.setOnClickListener(requestButtonListener);
		
	}
	
	private OnClickListener requestButtonListener=new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			if(validateNetId()){
				new getUserSecurityQuestions().execute();
			}
			else{
				AlertDialog.Builder passedalert=new AlertDialog.Builder(ForgotPasswordActivity.this);
				passedalert.setMessage("Invalid username");
				passedalert.show();
			}
		}
		
	};
	
	private class getUserSecurityQuestions extends AsyncTask<Void,Void,String>{

		@SuppressWarnings("static-access")
		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="get_security_question?"+"username="+username.getText().toString();
        		Log.i("ForgotPassword fullpath", fullPath);
        		DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpGet getRequest = new HttpGet(fullPath);
        		HttpResponse response = httpClient.execute(getRequest);
        		ParseResponse presponse=new ParseResponse();
        		result = presponse.getResult(response).toString();
        		Log.i("result in ForgotPasswordActivity", result);
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
				AlertDialog.Builder passedalert=new AlertDialog.Builder(ForgotPasswordActivity.this);
				passedalert.setMessage("Username is not found!");
				passedalert.show();
			}
			else{
				String[] questionnumb;
				questionnumb=result.split(":");
				Log.i("value of q1", questionnumb[0]);
				Log.i("Before calling intent","");
				Intent securityquestionpage=new Intent(ForgotPasswordActivity.this,SecurityQuestionActivity.class);
				securityquestionpage.putExtra("question1", questionnumb[0]);
				securityquestionpage.putExtra("question2", questionnumb[1]);
				securityquestionpage.putExtra("question3", questionnumb[2]);
				securityquestionpage.putExtra("username", username.getText().toString());
				Log.i("after calling intent","");
				startActivity(securityquestionpage);
			}
		}
		
	}
	private boolean validateNetId(){
		boolean valid = true;
		@SuppressWarnings("unused")
		boolean hasDigit;
		if(username.getText().toString().length()==7){
			String nid=username.getText().toString();
			String firstsubnid=nid.substring(0, 2);
			String secondsubnid=nid.substring(3, username.getText().toString().length());
			Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
			Matcher matcher = regex.matcher(username.getText().toString());
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
		getMenuInflater().inflate(R.menu.forgot_password, menu);
		return true;
	}
	


}

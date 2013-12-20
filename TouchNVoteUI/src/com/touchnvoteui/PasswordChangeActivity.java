package com.touchnvoteui;

import java.io.IOException;

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
import android.widget.Toast;

public class PasswordChangeActivity extends Activity {

	String username;
	EditText password;
	EditText confirmPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_change);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    username = extras.getString("username");
		}
		password = (EditText)findViewById(R.id.newPasswordEditText);
		confirmPassword = (EditText)findViewById(R.id.ConfirmPasswordEditText);
		Button submit = (Button)findViewById(R.id.submitButton);
		submit.setOnClickListener(changePasswordListener);
	}
	
	private OnClickListener changePasswordListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			if(password.getText().toString().equals(null)||confirmPassword.getText().toString().equals(null)){
				Toast.makeText(getApplicationContext(), "Password Cant be empty", Toast.LENGTH_SHORT);
			}
			else if(!password.getText().toString().equals(confirmPassword.getText().toString())){
				AlertDialog alert=new AlertDialog.Builder(PasswordChangeActivity.this).create();
				alert.setMessage("Sorry, passwords didn't match!");
				alert.show();
			}
			else{
				new UpdatePassword().execute();
			}
		}
		
	};
	
	private class UpdatePassword extends AsyncTask<Void,Void,String>{

		private String result;

		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="change_password?"+"username="+username+"&"+"password="+password.getText().toString();
        		Log.i("PasswordChangeActivity fullpath", fullPath);
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
				AlertDialog.Builder passedalert=new AlertDialog.Builder(PasswordChangeActivity.this);
				passedalert.setMessage("Sorry. Something went wrong. Try Again please!");
				passedalert.show();
			}
			else{
				Intent passwordchangepage=new Intent(PasswordChangeActivity.this,TNVMainActivity.class);
				//passwordchangepage.putExtra("username", username);
				startActivity(passwordchangepage);
				finish();
			}
	}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.password_change, menu);
		return true;
	}

}

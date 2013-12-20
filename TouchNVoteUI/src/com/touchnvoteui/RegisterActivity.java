package com.touchnvoteui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson.JacksonFactory;
import com.touchnvoteui.entities.securedloginendpoint.Securedloginendpoint;
import com.touchnvoteui.entities.securedloginendpoint.Securedloginendpoint.GetSecuredLogin;
import com.touchnvoteui.entities.securedloginendpoint.model.CollectionResponseSecuredLogin;
import com.touchnvoteui.entities.securedloginendpoint.model.SecuredLogin;
import com.touchnvoteui.entities.userdetailslistendpoint.Userdetailslistendpoint;
import com.touchnvoteui.entities.userdetailslistendpoint.Userdetailslistendpoint.GetUserDetailsList;
import com.touchnvoteui.entities.userdetailslistendpoint.Userdetailslistendpoint.ListUserDetailsList;
import com.touchnvoteui.entities.userdetailslistendpoint.model.UserDetailsList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;


public class RegisterActivity extends Activity {
	
	EditText fNameEditText;
	EditText lNameEditText;
	EditText netIDEditText;
	EditText rPasswordEditText;
	EditText cRPasswordditText;
	EditText q1EditText;
	EditText q2EditText;
	EditText q3EditText;
	Button submitButton;
	Button clearButton;
	Spinner q1Spinner;
	Spinner q2Spinner;
	Spinner q3Spinner;
	boolean userregistered=false;
	String result = "";
	
	public HttpClient client = new DefaultHttpClient();
	private String classlist;
	private Spinner classSpinner;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registeraccount);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			classlist = extras.getString("classlist");
		}
		classSpinner = (Spinner)findViewById(R.id.classSpinner);
		if(classlist!=null && !classlist.equals("null")){
			List<String> fclasslistarray = new ArrayList<String>();
			//String[] classlistarray;
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
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item , fclasslistarray);
			classSpinner.setAdapter(adapter);	
			adapter.notifyDataSetChanged();
		}
		else{
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item ,new String[] {"No class available"});
			classSpinner.setAdapter(adapter);
		}

		fNameEditText=(EditText)findViewById(R.id.fNameEditText);
		//fNameEditText.addTextChangedListener(mTextEditorWatcher);
		lNameEditText=(EditText)findViewById(R.id.lNameEditText);
		//lNameEditText.addTextChangedListener(mTextEditorWatcher);
		netIDEditText=(EditText)findViewById(R.id.netIDEditText);
		//fNameEditText=(EditText)findViewById(R.id.fNameEditText);
		rPasswordEditText=(EditText)findViewById(R.id.rPasswordEditText);
		cRPasswordditText=(EditText)findViewById(R.id.cRPasswordEditText);
		q1Spinner=(Spinner)findViewById(R.id.q1Spinner);
		q1EditText=(EditText)findViewById(R.id.q1EditText);
		q2Spinner=(Spinner)findViewById(R.id.q2Spinner);
		q2EditText=(EditText)findViewById(R.id.q2EditText);
		q3Spinner=(Spinner)findViewById(R.id.q3Spinner);
		q3EditText=(EditText)findViewById(R.id.q3EditText);
		submitButton=(Button)findViewById(R.id.submitButton);
		submitButton.setOnClickListener(submitButtonClickListener);
		clearButton=(Button)findViewById(R.id.clearButton);
		clearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fNameEditText.setText("");
				lNameEditText.setText("");
				netIDEditText.setText("");
				rPasswordEditText.setText("");
				cRPasswordditText.setText("");
				q1EditText.setText("");
				q2EditText.setText("");
				q3EditText.setText("");
				fNameEditText.setFocusable(true);
				
			}
		});
	}
	
	private boolean validateNetId(){
		boolean valid = true;
		boolean hasDigit;
		if(netIDEditText.getText().toString().length()==7){
		String nid=netIDEditText.getText().toString();
		String firstsubnid=nid.substring(0, 2);
		String secondsubnid=nid.substring(3, netIDEditText.getText().toString().length());
		Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
		Matcher matcher = regex.matcher(netIDEditText.getText().toString());
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
	
	private boolean checkForDigit(String entryText){
		for(char c:entryText.toCharArray()){
			if(Character.isDigit(c)){
				return true;
            }
		}
		return false;
	}
	
	private boolean validateEntries(){
		if(fNameEditText.getText().toString().trim().equals("") ||	lNameEditText.getText().toString().trim().equals("") || netIDEditText.getText().toString().trim().equals("") ||
				rPasswordEditText.getText().toString().trim().equals("") || cRPasswordditText.getText().toString().trim().equals("") ||
				q1EditText.getText().toString().trim().equals("") || q2EditText.getText().toString().trim().equals("") ||
				q3EditText.getText().toString().trim().equals("")){
			return false;
		}
		else 
			return true;
	}
	
	private boolean validatePassword(){
		if(rPasswordEditText.getText().toString().equals(cRPasswordditText.getText().toString())){
			return true;
		}
		else 
			return false;
	}
	
	private OnClickListener submitButtonClickListener=new OnClickListener(){

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			if(!validateEntries()){
				AlertDialog alert=new AlertDialog.Builder(RegisterActivity.this).create();
				alert.setMessage("Sorry, fields can't be empty");
				alert.show();
			}
			else if(checkForDigit(fNameEditText.getText().toString().trim()) || checkForDigit(lNameEditText.getText().toString().trim())){
				AlertDialog alert=new AlertDialog.Builder(RegisterActivity.this).create();
				alert.setMessage("Sorry, we dont accept numeric names :p");
				alert.show();
			}
			else if(!validateNetId()){
				Log.i("Error", "Invalid ");
				AlertDialog existalert=new AlertDialog.Builder(RegisterActivity.this).create();
        		existalert.setMessage("Invalid net id entered");
        		existalert.show();
        		
			}
			else if(validatePassword()){
				if(InternetConnectivityCheck.checkINetConnection(getApplicationContext()))
					new UserRegisterTask().execute();
				else
					Toast.makeText(getApplicationContext(), "No internet connectivity found", Toast.LENGTH_SHORT).show();
			}

			else{
				AlertDialog alert=new AlertDialog.Builder(RegisterActivity.this).create();
				alert.setMessage("Password mismatch");
				alert.setButton("OK", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
						rPasswordEditText.setFocusable(true);
					}
				});
				alert.show();
			}
			
		}
		
	};
	
	
	private class UserRegisterTask extends AsyncTask<Void,Void,String>{

		@Override
		protected String doInBackground(Void... params) {			
			String key=null;
			int q1Index=q1Spinner.getSelectedItemPosition();
        	int q2Index=q2Spinner.getSelectedItemPosition();
        	int q3Index=q3Spinner.getSelectedItemPosition();
        	String question1 = null;
        	String question2 = null;
        	String question3 = null;
        	if(q1Index==0){
        		question1="Q1";
        	}
        	else if(q1Index==1){
        		question1="Q2";
        	}
        	else if(q1Index==2){
        		question1="Q3";
        	}
        	if(q2Index==0){
        		question2="Q4";
        	}
        	else if(q2Index==1){
        		question2="Q5";
        	}
        	else if(q2Index==2){
        		question2="Q6";
        	}
        	if(q3Index==0){
        		question3="Q7";
        	}
        	else if(q3Index==1){
        		question3="Q8";
        	}
        	else if(q3Index==2){
        		question3="Q9";
        	}
        	
        	try {
       		
        		String fullPath = new String(TNVMainActivity.SERVICE_URL);
    			//fullPath+="store_user_details?userdetails=";
        		fullPath+="store_user_details?"+"firstname="+fNameEditText.getText().toString()+"&";
        		fullPath+="lastname="+lNameEditText.getText().toString()+"&";
        		fullPath+="netid="+netIDEditText.getText().toString()+"&";
        		fullPath+="password="+rPasswordEditText.getText().toString()+"&";
        		fullPath+="securityquestion1="+question1+"&";
        		fullPath+="securityquestion2="+question2+"&";
        		fullPath+="securityquestion3="+question3+"&";
        		fullPath+="securityquestion1answers="+q1EditText.getText().toString()+"&";
        		fullPath+="securityquestion2answers="+q2EditText.getText().toString()+"&";
        		fullPath+="securityquestion3answers="+q3EditText.getText().toString()+"&";
        		fullPath+="class="+classSpinner.getSelectedItem().toString();
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
		
		@SuppressWarnings("deprecation")
		protected void onPostExecute(String result){
			Log.i("onpostexe", result);
			if(result.trim().equals("false")){
				/*
				AlertDialog existalert=new AlertDialog.Builder(RegisterActivity.this).create();
        		existalert.setMessage("Seems you haven't enrolled for this class. Contact your professor to enroll");
        		existalert.show();
        		*/
        		Toast.makeText(getApplicationContext(), "Seems you haven't enrolled for this class. Contact your professor to enroll",
        				   Toast.LENGTH_LONG).show();
        		Intent showTNVMainActivity = new Intent(RegisterActivity.this,TNVMainActivity.class);
        		startActivity(showTNVMainActivity);
        		finish();
			}
			else if(!result.trim().equals("null")){
				/*
				AlertDialog existalert=new AlertDialog.Builder(RegisterActivity.this).create();
        		existalert.setMessage("Hurrey!, Registered Successfully. Try SignIn");
        		existalert.show();
        		*/
        		Toast.makeText(getApplicationContext(), "Hurrey!, Registered Successfully. Try SignIn",
     				   Toast.LENGTH_LONG).show();
        		Intent showTNVMainActivity = new Intent(RegisterActivity.this,TNVMainActivity.class);
        		startActivity(showTNVMainActivity);
        		finish();
				}
				else{
					AlertDialog.Builder existalert=new AlertDialog.Builder(RegisterActivity.this);
	        		existalert.setMessage("Seems you have already registered. Try SignIn");
	        		existalert.setPositiveButton("Continue", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int which) {
							Intent showTNVMainActivity = new Intent(RegisterActivity.this,TNVMainActivity.class);
			        		startActivity(showTNVMainActivity);
			        		finish();
						}
	        		});
	        		existalert.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int which) {

						}
	        		});
	        		existalert.show();
				}
		}
		
		/*
		private StringBuilder getResult(org.apache.http.HttpResponse response) throws IllegalStateException, IOException {

	        StringBuilder result = new StringBuilder();
	        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())), 1024);
	        String output;
	        while ((output = br.readLine()) != null) 
	            result.append(output);
	        return result;
	    }
	    */
		
	}
	
	
	
	  
}
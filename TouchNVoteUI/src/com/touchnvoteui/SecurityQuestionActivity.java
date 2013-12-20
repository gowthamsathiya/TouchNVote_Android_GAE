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
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecurityQuestionActivity extends Activity {

	EditText answer1EditText;
	EditText answer2EditText;
	EditText answer3EditText;
	TextView question1TextView;
	TextView question2TextView;
	TextView question3TextView;
	String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("Inside SecurityQuestionActivity", "sec");
		super.onCreate(savedInstanceState);
		//LinearLayout pageLayout = new LinearLayout(this);
		//pageLayout.setOrientation(R.layout.activity_security_question);
		setContentView(R.layout.activity_security_question);
		answer1EditText = (EditText)findViewById(R.id.answer1EditText);
		answer2EditText = (EditText)findViewById(R.id.answer2EditText);
		answer3EditText = (EditText)findViewById(R.id.answer3EditText);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    String question1 = extras.getString("question1");
		    String question2 = extras.getString("question2");
		    String question3 = extras.getString("question3");
		    username = extras.getString("username");
		    Log.i("inside SecurityQuestionActivity", "inside onCreate");
		    String[] question_array_1 = getResources().getStringArray(R.array.q1arrayquestions);
		    String[] question_array_2 = getResources().getStringArray(R.array.q2arrayquestions);
		    String[] question_array_3 = getResources().getStringArray(R.array.q3arrayquestions);

		    Log.i("answers", question1.substring(1)+question2.substring(1)+question3);
		    String q1=((String) question1.substring(1));
		    String q2=((String) question2.substring(1));
		    String q3=((String) question3.substring(1));
		    Log.i("inside SecurityQuestionActivity", q1+"+"+q2+"+"+q3);
		    if(q1.equalsIgnoreCase("1")||q1.equalsIgnoreCase("2")||q1.equalsIgnoreCase("3")){
		    	question1TextView=(TextView)findViewById(R.id.question1TextView);
		    	question1TextView.setText(question_array_1[Integer.parseInt(q1)-1]);
		    	Log.i("inside SecurityQuestionActivity", question_array_1[Integer.parseInt(q1)-1]);
		    	//pageLayout.addView(question1TextView);
		    	//answer1=new EditText(this);
		    	//pageLayout.addView(answer1);
		    	
		    }
		    if(q2.equalsIgnoreCase("4")||q2.equalsIgnoreCase("5")||q2.equalsIgnoreCase("6")){
		    	question2TextView=(TextView)findViewById(R.id.question2TextView);
		    	question2TextView.setText(question_array_2[Integer.parseInt(q2)-3-1]);
		    	Log.i("inside SecurityQuestionActivity", question_array_2[Integer.parseInt(q2)-3-1]);
		    	//pageLayout.addView(question2TextView);
		    	//answer2=new EditText(this);
		    	//pageLayout.addView(answer2);
		    	
		    }
		    if(q3.equalsIgnoreCase("7")||q3.equalsIgnoreCase("8")||q3.equalsIgnoreCase("9")){
		    	question3TextView=(TextView)findViewById(R.id.question3TextView);
		    	question3TextView.setText(question_array_3[Integer.parseInt(q3)-6-1]);
		    	Log.i("inside SecurityQuestionActivity", question_array_3[Integer.parseInt(q3)-6-1]);
		    	//pageLayout.addView(question3TextView);
		    	//answer3=new EditText(this);
		    	//pageLayout.addView(answer3);
		    	
		    }
		    //Button submit=new Button(this);
		    //submit.setText("Submit");
		    Button submitAnswerButton=(Button)findViewById(R.id.submitAnswerButton);
		    submitAnswerButton.setOnClickListener(changePasswordListener);
		    //pageLayout.addView(submit);
		}
	}

	private OnClickListener changePasswordListener=new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			if(answer1EditText.getText().toString().trim().equals("") || answer2EditText.getText().toString().trim().equals("") || answer3EditText.getText().toString().trim().equals("")){
				AlertDialog alert=new AlertDialog.Builder(SecurityQuestionActivity.this).create();
				alert.setMessage("Sorry, answers can't be empty");
				alert.show();
			}
			else{
				new CheckAnswerTask().execute();
			}
		}
		
	};
	
	private class CheckAnswerTask extends AsyncTask<Void,Void,String>{

		private String result;



		@SuppressWarnings("static-access")
		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="check_security_answers?"+"username="+username+"&"+"answer1="+answer1EditText.getText().toString()+"&"+"answer2="+answer2EditText.getText().toString()+"&"+"answer3="+answer3EditText.getText().toString();
        		Log.i("SecurityQuestionActivity fullpath", fullPath);
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
				AlertDialog passedalert=new AlertDialog.Builder(SecurityQuestionActivity.this).create();
				passedalert.setMessage("Sorry. Your answer seems to be wrong!");
				passedalert.show();
			}
			else{
				Intent passwordchangepage=new Intent(SecurityQuestionActivity.this,PasswordChangeActivity.class);
				passwordchangepage.putExtra("username", username);
				startActivity(passwordchangepage);
				finish();
			}
	}
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.security_question, menu);
		return true;
	}

}

package com.touchnvoteui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.touchnvoteui.generictype.ValidateDate;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddNewClassActivity extends Activity {

	Spinner seasonSpinner;
	EditText startDateEditText;
	Spinner startMonthSpinner;
	EditText startYearEditText;
	EditText endDateEditText;
	Spinner endMonthSpinner;
	EditText endYearEditText;
	TimePicker classTime;
	Button addClassButton;
	String classid;
	public String classstartdate;
	public String classenddate;
	public String classtime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_class);
		seasonSpinner = (Spinner)findViewById(R.id.seasonClassSpinner);
		startDateEditText = (EditText)findViewById(R.id.sDteEditText);
		startMonthSpinner = (Spinner)findViewById(R.id.sMonthSpinner);
		startYearEditText = (EditText)findViewById(R.id.sYearEditText);
		endDateEditText = (EditText)findViewById(R.id.eDteEditText);
		endMonthSpinner = (Spinner)findViewById(R.id.eMonthSpinner);
		endYearEditText = (EditText)findViewById(R.id.eYearEditText);
		classTime = (TimePicker)findViewById(R.id.timePicker);
		addClassButton = (Button)findViewById(R.id.addNewClassButton);
		addClassButton.setOnClickListener(classButtonListener);
	}

	private OnClickListener classButtonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        	Date date1 = null;
        	Date date2 = null;
        	String startDateEditTextValue;
        	String endDateEditTextValue;
			try {
				if(startDateEditText.getText().toString().trim().length()==1){
					startDateEditTextValue='0'+startDateEditText.getText().toString().trim();
				}
				if(endDateEditText.getText().toString().trim().length()==1){
					endDateEditTextValue='0'+endDateEditText.getText().toString().trim();
				}
				date1 = sdf.parse(startDateEditText.getText().toString().trim()+"-"+startMonthSpinner.getSelectedItem().toString()+"-"+
						startYearEditText.getText().toString().trim());
				date2 = sdf.parse(endDateEditText.getText().toString().trim()+"-"+endMonthSpinner.getSelectedItem().toString()+"-"+
						endYearEditText.getText().toString().trim());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
			if(startDateEditText.getText().toString().trim().equals("") || startYearEditText.getText().toString().trim().equals("") || 
					endDateEditText.getText().toString().trim().equals("") || endYearEditText.getText().toString().trim().equals("")){
				AlertDialog error = new AlertDialog.Builder(AddNewClassActivity.this).create();
				error.setMessage("Fields cant be empty");
				error.show();
			}
			
			else if(!ValidateDate.checkdate1(startDateEditText.getText().toString().trim()+"-"+
					startMonthSpinner.getSelectedItem().toString()+"-"+
					startYearEditText.getText().toString().trim()) ||
					!ValidateDate.checkdate1(endDateEditText.getText().toString().trim()+"-"+
							endMonthSpinner.getSelectedItem().toString()+"-"+
							endYearEditText.getText().toString().trim())
					){
				AlertDialog error = new AlertDialog.Builder(AddNewClassActivity.this).create();
				error.setMessage("Invalid date entered");
				error.show();
			}

			else if(validateAlphabets(startDateEditText.getText().toString().trim())&&validateAlphabets(startYearEditText.getText().toString().trim())
					&&validateAlphabets(endDateEditText.getText().toString().trim())&&validateAlphabets(endYearEditText.getText().toString().trim())){
				if(startDateEditText.getText().toString().trim().length()>=1 && startYearEditText.getText().toString().trim().length()==4 && 
						endDateEditText.getText().toString().trim().length()>=1 && endYearEditText.getText().toString().trim().length()==4){
					try {
						if(startDateEditText.getText().toString().trim().length()==1){
							startDateEditTextValue='0'+startDateEditText.getText().toString().trim();
						}
						if(endDateEditText.getText().toString().trim().length()==1){
							endDateEditTextValue='0'+endDateEditText.getText().toString().trim();
						}
						date1 = sdf.parse(startDateEditText.getText().toString().trim()+"-"+startMonthSpinner.getSelectedItem().toString()+"-"+
								startYearEditText.getText().toString().trim());
						date2 = sdf.parse(endDateEditText.getText().toString().trim()+"-"+endMonthSpinner.getSelectedItem().toString()+"-"+
								endYearEditText.getText().toString().trim());
						if(date1.compareTo(date2)>0){
							AlertDialog error = new AlertDialog.Builder(AddNewClassActivity.this).create();
							error.setMessage("Sorry, time machine haven't built yet. So we accept end date greater than start date only.");
							error.show();
						}
						else{
						classstartdate = startDateEditText.getText().toString().trim()+"-"+startMonthSpinner.getSelectedItem().toString()+"-"+
							startYearEditText.getText().toString().trim();
						classenddate = endDateEditText.getText().toString().trim()+"-"+endMonthSpinner.getSelectedItem().toString()+"-"+
								endYearEditText.getText().toString().trim();
						classtime = classTime.getCurrentHour()+":"+classTime.getCurrentMinute();
						classid = seasonSpinner.getSelectedItem().toString()+"_"+classstartdate+"_"+classenddate+"_"+classtime;
						new callStoreClass().execute();
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					AlertDialog error = new AlertDialog.Builder(AddNewClassActivity.this).create();
					error.setMessage("Date or year is not valid.");
					error.show();
				}
				
			}
			else{
				AlertDialog error = new AlertDialog.Builder(AddNewClassActivity.this).create();
				error.setMessage("Sorry, date and year shouldn't contain alphabets");
				error.show();
			}
		}
		
	};
	public String result;

	
	private boolean validateAlphabets(String temp){
		boolean hasDigit = true;
		for(char c:temp.toCharArray()){
			if(!Character.isDigit(c)){
				hasDigit=false;
				break;
			}
		}
		return hasDigit;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_class, menu);
		return true;
	}
	
	private class callStoreClass extends AsyncTask<Void,Void,String>{

		@SuppressWarnings("static-access")
		@Override
		protected String doInBackground(Void... arg0) {
			try {
	       		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="class_details?"+"classid="+classid+"&";
        		fullPath+="classseason="+seasonSpinner.getSelectedItem().toString()+"&";
        		fullPath+="classstartdate="+classstartdate+"&";
        		fullPath+="classenddate="+classenddate+"&";
        		fullPath+="classtime="+classtime;
        		Log.i("AddNewClassActivity fullpath", fullPath);
        		DefaultHttpClient httpClient = new DefaultHttpClient();
        		HttpGet getRequest = new HttpGet(fullPath);
        		HttpResponse response = httpClient.execute(getRequest);
        		ParseResponse presponse=new ParseResponse();
        		result = presponse.getResult(response).toString();
        		//result = response.getEntity().getContent().toString();
        		Log.i("Result in Add new class activity", result);
        		httpClient.getConnectionManager().shutdown();
			}
			catch (ClientProtocolException e) {
				Log.i("Add new class Activity exception", "clientprotocolexep");
				e.printStackTrace();
			}
			catch (IOException e) {
				Log.i("Add new class Activity exception", "ioexception");
				e.printStackTrace();
			} 
			catch(Exception e){
				Log.i("Add new class Activity exception", "exception");
				e.printStackTrace();
			}
			return result;
		}
		
		protected void onPostExecute(String result){
			if(result.equals("true")){
				Toast.makeText(AddNewClassActivity.this, "Class: "+classid+" is added successfully", Toast.LENGTH_SHORT);
				AlertDialog.Builder okalert = new AlertDialog.Builder(AddNewClassActivity.this);
				okalert.setPositiveButton("Class: "+classid+" is added successfully", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent lectureMainActivity = new Intent(AddNewClassActivity.this,ProfessorMainActivity.class);
						startActivity(lectureMainActivity);
						finish();
					}
				});
				okalert.show();
			}
			else if(result.equals("null")){
				AlertDialog.Builder failedalert=new AlertDialog.Builder(AddNewClassActivity.this);
				failedalert.setMessage("This class is already registered!");
				failedalert.show();
			}
			else{
				AlertDialog.Builder failedalert=new AlertDialog.Builder(AddNewClassActivity.this);
				failedalert.setMessage("Sorry. Something went wrong. Try again please!");
				failedalert.show();
			}
		}
		
	}

}

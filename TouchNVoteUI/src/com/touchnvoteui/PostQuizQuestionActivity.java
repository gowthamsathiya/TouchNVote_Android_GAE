package com.touchnvoteui;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.touchnvoteui.generictype.Question;

public class PostQuizQuestionActivity extends Activity {

	String title;
	TextView selectedClassTextView;
	EditText questionEditText;
	RadioGroup answerTypeRadioGroup;
	RadioButton answertTypeRadio;
	RadioButton answerTypeCombo;
	Button plusButton;
	EditText answerEditText1;
	EditText answerEditText2;
	EditText answerEditText3;
	EditText answerEditText4;
	CheckBox option1CheckBox;
	CheckBox option2CheckBox;
	CheckBox option3CheckBox;
	CheckBox option4CheckBox;
	Button addQuestionButton;
	Button postQuizButton;
	int count;
	//List<String> answerlist = new ArrayList<String>();
	List<Question> questionlist = new ArrayList<Question>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		count = 2;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_quiz_question);
		selectedClassTextView = (TextView)findViewById(R.id.selectedClassTextView);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    title = extras.getString("title");
		}
		Log.i("title", title);
		selectedClassTextView.setText(title);
		questionEditText = (EditText)findViewById(R.id.questionEditText);
		answerTypeRadioGroup = (RadioGroup)findViewById(R.id.answerTypeRadioGroup);
		answertTypeRadio = (RadioButton)findViewById(R.id.answertTypeRadio);
		answerTypeCombo = (RadioButton)findViewById(R.id.answerTypeCombo);
		plusButton = (Button)findViewById(R.id.plusButton);
		answerEditText1 = (EditText)findViewById(R.id.answerEditText1);
		answerEditText2 = (EditText)findViewById(R.id.answerEditText2);
		answerEditText3 = (EditText)findViewById(R.id.answerEditText3);
		answerEditText4 = (EditText)findViewById(R.id.answerEditText4);
		option1CheckBox = (CheckBox)findViewById(R.id.option1CheckBox);
		option2CheckBox = (CheckBox)findViewById(R.id.option2CheckBox);
		option3CheckBox = (CheckBox)findViewById(R.id.option3CheckBox);
		option4CheckBox = (CheckBox)findViewById(R.id.option4CheckBox);
		addQuestionButton = (Button)findViewById(R.id.addQuestionButton);
		postQuizButton = (Button)findViewById(R.id.postQuizButton);
		
		
		plusButton.setOnClickListener(plusButtonListener);
		addQuestionButton.setOnClickListener(addQuestionListener);
		postQuizButton.setOnClickListener(postQuizListener);
	}

	private OnClickListener plusButtonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			count++;
			if(count<=4){
				if(count == 3){
					Log.i("count value", Integer.toString(count));
					option3CheckBox.setVisibility(View.VISIBLE);
					answerEditText3.setVisibility(View.VISIBLE);
				}
				else if(count == 4){
					Log.i("count value", Integer.toString(count));
					option4CheckBox.setVisibility(View.VISIBLE);
					answerEditText4.setVisibility(View.VISIBLE);
				}
			}
			else{
				AlertDialog nooption = new AlertDialog.Builder(PostQuizQuestionActivity.this).create();
				nooption.setMessage("Sorry, only 4 options can be added");
				nooption.show();
			}
		}
		
	};
	
	private OnClickListener addQuestionListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			addQuestion();
			Log.i("questionlist",questionlist.toString());
			//Log.i("answerlist", answerlist.toString());
			questionEditText.setText("");
			answerEditText1.setText("");
			answerEditText2.setText("");
			answerEditText3.setText("");
			answerEditText4.setText("");
			option1CheckBox.setChecked(false);
			option2CheckBox.setChecked(false);
			option3CheckBox.setChecked(false);
			option4CheckBox.setChecked(false);
			answerEditText3.setVisibility(View.INVISIBLE);
			answerEditText4.setVisibility(View.INVISIBLE);
			option3CheckBox.setVisibility(View.INVISIBLE);
			option4CheckBox.setVisibility(View.INVISIBLE);
			count = 2;
			/*
			if(answerlist!=null){
				for(int index=0;index<answerlist.size();index++){
					//answerlist.remove(list);
					Log.i("index val :", Integer.toString(index));
					answerlist.remove(index);
					Log.i("index val after deletion :", Integer.toString(index));
				}
				Log.i("answerlist after removing", answerlist.toString());
			}
			*/
			//Log.i("answerlist before clear ", answerlist.toString());
			//answerlist.clear();
			//Log.i("answerlist after removing", answerlist.toString());
		}
		
	};
	int noofquestioncount = 0;
	private void addQuestion(){
		if(!questionEditText.getText().toString().equals("")&&!answerEditText1.getText().toString().equals("")&&
				!answerEditText2.getText().toString().equals("")){
			if(count == 2){
				radioCheck();
				comboCheck();
				//Log.i("answerlist print inside addQuestion()", answerlist.toString());
			}
			if(count == 3){
				if(!answerEditText3.getText().toString().equals("")){
					//RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());
					radioCheck();
					comboCheck();
					//Log.i("answerlist print inside addQuestion()", answerlist.toString());
					}
				else{
					AlertDialog alert = new AlertDialog.Builder(PostQuizQuestionActivity.this).create();
					alert.setMessage("Sorry! the fields can't be empty");
					alert.show();
				}
					
				}
			if(count == 4){
				if(!answerEditText3.getText().toString().equals("")&&!answerEditText4.getText().toString().equals("")){
					radioCheck();
					comboCheck();
					//Log.i("answerlist print inside addQuestion()", answerlist.toString());
				}
				else{
					AlertDialog alert = new AlertDialog.Builder(PostQuizQuestionActivity.this).create();
					alert.setMessage("Sorry! the fields can't be empty");
					alert.show();
				}
			}
		}
		else{
			AlertDialog alert = new AlertDialog.Builder(PostQuizQuestionActivity.this).create();
			alert.setMessage("Sorry! the fields can't be empty");
			alert.show();
		}
	}
	
	private void radioCheck(){
		if(noofquestioncount < 4){
		if(answertTypeRadio.isChecked()){
			List<ArrayList<String>> correctanswerlist = getCheckedCombo();
			List<String> answerlist = correctanswerlist.get(0);
			List<String> correctanswer = correctanswerlist.get(1);
			Log.i("inside radioCheck() correctanswer size",Integer.toString(correctanswer.size()));
			if(correctanswer.size() == 0){
				AlertDialog alert = new AlertDialog.Builder(PostQuizQuestionActivity.this).create();
				alert.setMessage("Answer(or)opinion should be marked");
				alert.show();
			}
			else if(correctanswer.size() >= 2){
				AlertDialog alert = new AlertDialog.Builder(PostQuizQuestionActivity.this).create();
				alert.setMessage("Radio type questions cant have more than one correct answer");
				alert.show();
			}
			else{
				Log.i("title in radiocheck(): ", title);
				Log.i("in radioCheck(): ", title+"-"+questionEditText.getText().toString().trim().replaceAll(" ", "___")+"-"+1+"-"+answerlist.toString()+"-"+correctanswer.toString());
				Question q = new Question(title,questionEditText.getText().toString().trim().replaceAll(" ", "___"),1,answerlist,correctanswer,noofquestioncount+1);
				questionlist.add(q);
				noofquestioncount++;
			}
			Log.i("correctanswer inside radio check", correctanswer.toString());
		}
	}
		else{
			AlertDialog alert = new AlertDialog.Builder(PostQuizQuestionActivity.this).create();
			alert.setMessage("Max 4 question is allowed");
			alert.show();
		}
		
	}
	
	private void comboCheck(){
		if(noofquestioncount < 4){
		if(answerTypeCombo.isChecked()){
			List<ArrayList<String>> correctanswerlist = getCheckedCombo();
			List<String> answerlist = correctanswerlist.get(0);
			List<String> correctanswer = correctanswerlist.get(1);
			Log.i("in comboCheck(): ", title+"-"+questionEditText.getText().toString().trim().replaceAll(" ", "___")+"-"+2+"-"+answerlist.toString()+"-"+correctanswer.toString());
			Question q = new Question(title,questionEditText.getText().toString().trim().replaceAll(" ", "___"),2,answerlist,correctanswer,noofquestioncount+1);
			questionlist.add(q);
			noofquestioncount++;
			Log.i("correctanswer inside combo check", correctanswer.toString());
		}
		}
		else{
			AlertDialog alert = new AlertDialog.Builder(PostQuizQuestionActivity.this).create();
			alert.setMessage("Max 4 question is allowed");
			alert.show();
		}

	}
		
	private List<ArrayList<String>> getCheckedCombo(){
		List<ArrayList<String>> answerandcorrectlist = new ArrayList<ArrayList<String>>();
		List<String> answerlist = new ArrayList<String>();
		List<String> correctanswer = new ArrayList<String>();
		if(count==2){
			if(option1CheckBox.isChecked()){
				correctanswer.add(Integer.toString(1));
				
			}
			if(option2CheckBox.isChecked()){
				correctanswer.add(Integer.toString(2));
				
			}
			answerlist.add(answerEditText1.getText().toString());
			answerlist.add(answerEditText2.getText().toString());
		}
		else if(count==3){
			if(option1CheckBox.isChecked()){
				correctanswer.add(Integer.toString(1));
				
			}
			if(option2CheckBox.isChecked()){
				correctanswer.add(Integer.toString(2));
				
			}
			if(option3CheckBox.isChecked()){
				correctanswer.add(Integer.toString(3));
				
			}
			answerlist.add(answerEditText1.getText().toString());
			answerlist.add(answerEditText2.getText().toString());
			answerlist.add(answerEditText3.getText().toString());
		}
		else if(count==4){
			if(option1CheckBox.isChecked()){
				correctanswer.add(Integer.toString(1));
				
			}
			if(option2CheckBox.isChecked()){
				correctanswer.add(Integer.toString(2));
				
			}
			if(option3CheckBox.isChecked()){
				correctanswer.add(Integer.toString(3));
				
			}
			if(option4CheckBox.isChecked()){
				correctanswer.add(Integer.toString(4));
				
			}
			answerlist.add(answerEditText1.getText().toString());
			answerlist.add(answerEditText2.getText().toString());
			answerlist.add(answerEditText3.getText().toString());
			answerlist.add(answerEditText4.getText().toString());
		}
		answerandcorrectlist.add((ArrayList<String>) answerlist);
		answerandcorrectlist.add((ArrayList<String>) correctanswer);
		
		return answerandcorrectlist;
	}
	
	private String questionListToString(){
		String questionliststring="";
		for(Question q: questionlist){
			questionliststring+=q.toString()+"####";
		}
		if(questionliststring!="")
			return questionliststring.substring(0, questionliststring.length()-4);
		else
			return null;
	}
	
	private OnClickListener postQuizListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			if(questionlist.size()!=0 || questionlist == null)
				new postQuizTask().execute();
			else{
				AlertDialog alert = new AlertDialog.Builder(PostQuizQuestionActivity.this).create();
				alert.setMessage("No questions added to +question bag. Try adding questions and post");
				alert.show();
			}
		}
		
	};
	
	private class postQuizTask extends AsyncTask<Void,Void,String>{

		private String result;

		@Override
		protected String doInBackground(Void... arg0) {
			try {
				//addQuestion();
	       		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="quiz_question?"+"questionlist="+URLEncoder.encode(questionListToString(),"UTF-8");
        		Log.i("PostQuizQuestionActivity fullpath", fullPath);
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
				AlertDialog.Builder passedalert=new AlertDialog.Builder(PostQuizQuestionActivity.this);
				passedalert.setMessage("Sorry. Something went wrong. Please try again!");
				passedalert.show();
			}
			else if(result.equals("true")){
				Toast.makeText(PostQuizQuestionActivity.this, "Exam posted successfully", Toast.LENGTH_LONG).show();
				Intent profmain = new Intent(PostQuizQuestionActivity.this,ProfessorMainActivity.class);
				startActivity(profmain);
				finish();
				/*
				Builder passedalert=new AlertDialog.Builder(PostQuizQuestionActivity.this);
				passedalert.setMessage("Exam posted successfully");
				passedalert.show();
				passedalert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) 
                    {
                    	Intent profmain = new Intent(PostQuizQuestionActivity.this,ProfessorMainActivity.class);
        				startActivity(profmain);
        				finish();

                    }
                });
				*/
			}
	}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_quiz_question, menu);
		return true;
	}

}

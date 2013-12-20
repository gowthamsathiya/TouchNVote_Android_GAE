package com.touchnvoteui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.touchnvoteui.generictype.Question;

public class StudentMainActivity extends Activity {

	private String questionlist;
	private String username;
	int questioncount=0;
	HashMap<String,List<String>> answeredlist = new HashMap<String,List<String>>();
	private TextView noActivityTitle;
	private TextView title;
	
	private TextView question1;
	private RadioGroup answerRadioGroup1;
	private RadioButton answerRadio1_1;
	private RadioButton answerRadio1_2;
	private RadioButton answerRadio1_3;
	private RadioButton answerRadio1_4;
	private CheckBox answerCheckBox1_1;
	private CheckBox answerCheckBox1_2;
	private CheckBox answerCheckBox1_3;
	private CheckBox answerCheckBox1_4;
	
	private TextView question2;
	private RadioGroup answerRadioGroup2;
	private RadioButton answerRadio2_1;
	private RadioButton answerRadio2_2;
	private RadioButton answerRadio2_3;
	private RadioButton answerRadio2_4;
	private CheckBox answerCheckBox2_1;
	private CheckBox answerCheckBox2_2;
	private CheckBox answerCheckBox2_3;
	private CheckBox answerCheckBox2_4;
	
	private TextView question3;
	private RadioGroup answerRadioGroup3;
	private RadioButton answerRadio3_1;
	private RadioButton answerRadio3_2;
	private RadioButton answerRadio3_3;
	private RadioButton answerRadio3_4;
	private CheckBox answerCheckBox3_1;
	private CheckBox answerCheckBox3_2;
	private CheckBox answerCheckBox3_3;
	private CheckBox answerCheckBox3_4;
	
	private TextView question4;
	private RadioGroup answerRadioGroup4;
	private RadioButton answerRadio4_1;
	private RadioButton answerRadio4_2;
	private RadioButton answerRadio4_3;
	private RadioButton answerRadio4_4;
	private CheckBox answerCheckBox4_1;
	private CheckBox answerCheckBox4_2;
	private CheckBox answerCheckBox4_3;
	private CheckBox answerCheckBox4_4;
	private int len = 0;
	private String quiztitle;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_main);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			questionlist = extras.getString("question");
			username = extras.getString("username");
		}
		Log.i("result ", questionlist);
		noActivityTitle = (TextView)findViewById(R.id.noActivityTitle);
		title = (TextView)findViewById(R.id.title);
		Button submitQuizAnsButton = (Button)findViewById(R.id.submitQuizAnsButton);
		submitQuizAnsButton.setVisibility(View.VISIBLE);
		submitQuizAnsButton.setOnClickListener(submitAnswerListener);
		if(questionlist.equalsIgnoreCase("None")){
			noActivityTitle.setVisibility(View.VISIBLE);
			title.setVisibility(View.GONE);
			deleteQuestion1();
			deleteQuestion2();
			deleteQuestion3();
			deleteQuestion4();
			submitQuizAnsButton.setVisibility(View.GONE);
		}
		else{
			noActivityTitle.setVisibility(View.GONE);
			String[] question = questionlist.split("####");
			len  = question.length;
			quiztitle = Question.toQuestion(question[0]).getQuizTitle();
			
			if(question.length == 1){
				Question q1 = Question.toQuestion(question[0]);
				setQuestion1(q1);
				deleteQuestion2();
				deleteQuestion3();
				deleteQuestion4();
			}
			else if(question.length == 2){
				Question temp1 = Question.toQuestion(question[0]);
				Question temp2 = Question.toQuestion(question[1]);
				Question q1,q2;
				if(temp1.getCount() == 1){
					q1 = temp1;
					q2 = temp2;
				}
				else{
					q1 = temp2;
					q2 = temp1;
				}
				setQuestion1(q1);
				setQuestion2(q2);
				deleteQuestion3();
				deleteQuestion4();
			}
			else if(question.length == 3){
				Question temp1 = Question.toQuestion(question[0]);
				Question temp2 = Question.toQuestion(question[1]);
				Question temp3 = Question.toQuestion(question[2]);
				Question q1 = null,q2 = null,q3 = null;
				if(temp1.getCount() == 1)
					q1 = temp1;
				if(temp2.getCount() == 1)
					q1 = temp2;
				if(temp3.getCount() == 1)
					q1 = temp3;
				if(temp1.getCount() == 2)
					q2 = temp1;
				if(temp2.getCount() == 2)
					q2 = temp2;
				if(temp3.getCount() == 2)
					q2 = temp3;
				if(temp1.getCount() == 3)
					q3 = temp1;
				if(temp2.getCount() == 3)
					q3 = temp2;
				if(temp3.getCount() == 3)
					q3 = temp3;
				
				
				setQuestion1(q1);
				setQuestion2(q2);
				setQuestion3(q3);
				deleteQuestion4();
			}
			else if(question.length == 4){
				Question temp1 = Question.toQuestion(question[0]);
				Question temp2 = Question.toQuestion(question[1]);
				Question temp3 = Question.toQuestion(question[2]);
				Question temp4 = Question.toQuestion(question[3]);
				Question q1 = null,q2 = null,q3 = null,q4 = null;
				if(temp1.getCount() == 1)
					q1 = temp1;
				if(temp2.getCount() == 1)
					q1 = temp2;
				if(temp3.getCount() == 1)
					q1 = temp3;
				if(temp4.getCount() == 1)
					q1 = temp4;
				if(temp1.getCount() == 2)
					q2 = temp1;
				if(temp2.getCount() == 2)
					q2 = temp2;
				if(temp3.getCount() == 2)
					q2 = temp3;
				if(temp4.getCount() == 2)
					q2 = temp4;
				if(temp1.getCount() == 3)
					q3 = temp1;
				if(temp2.getCount() == 3)
					q3 = temp2;
				if(temp3.getCount() == 3)
					q3 = temp3;
				if(temp4.getCount() == 3)
					q3 = temp4;
				if(temp1.getCount() == 4)
					q4 = temp1;
				if(temp2.getCount() == 4)
					q4 = temp2;
				if(temp3.getCount() == 4)
					q4 = temp3;
				if(temp4.getCount() == 4)
					q4 = temp4;
				
				setQuestion1(q1);
				setQuestion2(q2);
				setQuestion3(q3);
				setQuestion4(q4);
			}
			
		}
	}
	
	private OnClickListener submitAnswerListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			if(answeredlist.size()==len){
				boolean clear = true;
				for(int count=0;count<len;count++){
					List<String> items = answeredlist.get(Integer.toString(count+1));
					if(items == null || items.size() == 0){
						clear = false;
						AlertDialog alert = new AlertDialog.Builder(StudentMainActivity.this).create();
						alert.setMessage("Answers can't be empty");
						alert.show();
					}
				}
				if(clear){
					new SubmitAnswer().execute();
				}

			}
			else{
				AlertDialog alert = new AlertDialog.Builder(StudentMainActivity.this).create();
				alert.setMessage("Answers can't be empty");
				alert.show();
			}
		}
		
	};
	private CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				String sentkey = (String) arg0.getTag();
				Log.i("checkBoxListener", sentkey);
				String ans = sentkey.substring(1,sentkey.length()).trim();
				String key = sentkey.substring(0, 1);
				if(answeredlist.containsKey(key)){
					List<String> selectedans = answeredlist.get(key);
					if(arg1){
						selectedans.add(ans);
					}
					else{
						selectedans.remove(ans);
					}
					answeredlist.put(key, selectedans);
				}
				else{
					List<String> selectedans = new ArrayList<String>();
					selectedans.add(ans);
					answeredlist.put(key, selectedans);
				}
				Log.i("Answer in checkBoxListener", answeredlist.toString());
		}
		
	};
	
	private void setQuestion1(Question que){
		Log.i("inside question1", que.toString());
		question1 = (TextView)findViewById(R.id.question1);
		answerRadioGroup1 = (RadioGroup)findViewById(R.id.answerRadioGroup1);
		answerRadio1_1 = (RadioButton)findViewById(R.id.answerRadio1_1);
		answerRadio1_2 = (RadioButton)findViewById(R.id.answerRadio1_2);
		answerRadio1_3 = (RadioButton)findViewById(R.id.answerRadio1_3);
		answerRadio1_4 = (RadioButton)findViewById(R.id.answerRadio1_4);
		answerCheckBox1_1 = (CheckBox)findViewById(R.id.answerCheckBox1_1);
		answerCheckBox1_2 = (CheckBox)findViewById(R.id.answerCheckBox1_2);
		answerCheckBox1_3 = (CheckBox)findViewById(R.id.answerCheckBox1_3);
		answerCheckBox1_4 = (CheckBox)findViewById(R.id.answerCheckBox1_4);
		
		
		
		answerRadioGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	    {
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
	        	//int id= checkedId.getCheckedRadioButtonId();
	            View radioButton = group.findViewById(checkedId);
	            int radioId = group.indexOfChild(radioButton);
	            RadioButton btn = (RadioButton) group.getChildAt(radioId);
	            String selectedtext = (String) btn.getText();
	            
	            if(answeredlist.containsKey("1")){
	            	answeredlist.remove("1");
	            	List<String> value = new ArrayList<String>();
	            	value.add(selectedtext);
	            	answeredlist.put("1", value);
	            }
	            else{
	            	List<String> value = new ArrayList<String>();
	            	value.add(selectedtext);
	            	answeredlist.put("1", value);
	            }
	            Log.i("Answer in answerRadioGroup1", answeredlist.toString());
	        }
	    });
		
		Log.i("sma ", que.getQuestion());
		question1.setText(que.getQuestion().replace("___", " ").trim());
		List<String> ans = que.getAnswers(); 
		if(que.getType() == 1){
			if(ans.size() == 2){
				answerRadio1_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio1_2.setText(ans.get(1).replace("]]", "").trim());
				answerRadio1_3.setVisibility(View.GONE);
				answerRadio1_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 3){
				answerRadio1_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio1_2.setText(ans.get(1).trim());
				answerRadio1_3.setText(ans.get(2).replace("]]", "").trim());
				answerRadio1_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 4){
				answerRadio1_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio1_2.setText(ans.get(1).trim());
				answerRadio1_3.setText(ans.get(2).trim());
				answerRadio1_4.setText(ans.get(3).replace("]]", "").trim());
			}
			answerCheckBox1_1.setVisibility(View.GONE);
			answerCheckBox1_2.setVisibility(View.GONE);
			answerCheckBox1_3.setVisibility(View.GONE);
			answerCheckBox1_4.setVisibility(View.GONE);
		}
		else if(que.getType() == 2){
			answerRadio1_1.setVisibility(View.GONE);
			answerRadio1_2.setVisibility(View.GONE);
			answerRadio1_3.setVisibility(View.GONE);
			answerRadio1_4.setVisibility(View.GONE);
			if(ans.size() == 2){
				answerCheckBox1_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox1_2.setText(ans.get(1).replace("]]", "").trim());
				answerCheckBox1_3.setVisibility(View.GONE);
				answerCheckBox1_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 3){
				answerCheckBox1_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox1_2.setText(ans.get(1).trim());
				answerCheckBox1_3.setText(ans.get(2).replace("]]", "").trim());
				answerCheckBox1_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 4){
				answerCheckBox1_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox1_2.setText(ans.get(1).trim());
				answerCheckBox1_3.setText(ans.get(2).trim());
				answerCheckBox1_4.setText(ans.get(3).replace("]]", "").trim());
			}
			
		}
		answerCheckBox1_1.setTag("1"+answerCheckBox1_1.getText());
		answerCheckBox1_2.setTag("1"+answerCheckBox1_2.getText());
		answerCheckBox1_3.setTag("1"+answerCheckBox1_3.getText());
		answerCheckBox1_4.setTag("1"+answerCheckBox1_4.getText());
		answerCheckBox1_1.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox1_2.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox1_3.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox1_4.setOnCheckedChangeListener(checkBoxListener);
	}
	
	
	private void setQuestion2(Question que){
		question2 = (TextView)findViewById(R.id.question2);
		answerRadioGroup2 = (RadioGroup)findViewById(R.id.answerRadioGroup2);
		answerRadio2_1 = (RadioButton)findViewById(R.id.answerRadio2_1);
		answerRadio2_2 = (RadioButton)findViewById(R.id.answerRadio2_2);
		answerRadio2_3 = (RadioButton)findViewById(R.id.answerRadio2_3);
		answerRadio2_4 = (RadioButton)findViewById(R.id.answerRadio2_4);
		answerCheckBox2_1 = (CheckBox)findViewById(R.id.answerCheckBox2_1);
		answerCheckBox2_2 = (CheckBox)findViewById(R.id.answerCheckBox2_2);
		answerCheckBox2_3 = (CheckBox)findViewById(R.id.answerCheckBox2_3);
		answerCheckBox2_4 = (CheckBox)findViewById(R.id.answerCheckBox2_4);
		
		answerRadioGroup2.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	    {
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
	        	//int id= checkedId.getCheckedRadioButtonId();
	            View radioButton = group.findViewById(checkedId);
	            int radioId = group.indexOfChild(radioButton);
	            RadioButton btn = (RadioButton) group.getChildAt(radioId);
	            String selectedtext = (String) btn.getText();
	            
	            if(answeredlist.containsKey("2")){
	            	answeredlist.remove("2");
	            	List<String> value = new ArrayList<String>();
	            	value.add(selectedtext);
	            	answeredlist.put("2", value);
	            }
	            else{
	            	List<String> value = new ArrayList<String>();
	            	value.add(selectedtext);
	            	answeredlist.put("2", value);
	            }
	            Log.i("Answer in answerRadioGroup2", answeredlist.toString());
	        }
	    });
		
	
		
		question2.setText(que.getQuestion().replace("___", " ").trim());
		List<String> ans = que.getAnswers(); 
		if(que.getType() == 1){
			if(ans.size() == 2){
				answerRadio2_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio2_2.setText(ans.get(1).replace("]]", "").trim());
				answerRadio2_3.setVisibility(View.GONE);
				answerRadio2_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 3){
				answerRadio2_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio2_2.setText(ans.get(1).trim());
				answerRadio2_3.setText(ans.get(2).replace("]]", "").trim());
				answerRadio2_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 4){
				answerRadio2_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio2_2.setText(ans.get(1).trim());
				answerRadio2_3.setText(ans.get(2).trim());
				answerRadio2_4.setText(ans.get(3).replace("]]", "").trim());
			}
			answerCheckBox2_1.setVisibility(View.GONE);
			answerCheckBox2_2.setVisibility(View.GONE);
			answerCheckBox2_3.setVisibility(View.GONE);
			answerCheckBox2_4.setVisibility(View.GONE);
		}
		else if(que.getType() == 2){
			answerRadio2_1.setVisibility(View.GONE);
			answerRadio2_2.setVisibility(View.GONE);
			answerRadio2_3.setVisibility(View.GONE);
			answerRadio2_4.setVisibility(View.GONE);
			if(ans.size() == 2){
				answerCheckBox2_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox2_2.setText(ans.get(1).replace("]]", "").trim());
				answerCheckBox2_3.setVisibility(View.GONE);
				answerCheckBox2_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 3){
				answerCheckBox2_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox2_2.setText(ans.get(1).trim());
				answerCheckBox2_3.setText(ans.get(2).replace("]]", "").trim());
				answerCheckBox2_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 4){
				answerCheckBox2_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox2_2.setText(ans.get(1).trim());
				answerCheckBox2_3.setText(ans.get(2).trim());
				answerCheckBox2_4.setText(ans.get(3).replace("]]", "").trim());
			}
			
		}
		
		answerCheckBox2_1.setTag("2"+answerCheckBox2_1.getText());
		answerCheckBox2_2.setTag("2"+answerCheckBox2_2.getText());
		answerCheckBox2_3.setTag("2"+answerCheckBox2_3.getText());
		answerCheckBox2_4.setTag("2"+answerCheckBox2_4.getText());
		answerCheckBox2_1.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox2_2.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox2_3.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox2_4.setOnCheckedChangeListener(checkBoxListener);
	}
	
	
	private void setQuestion3(Question que){
		question3 = (TextView)findViewById(R.id.question3);
		answerRadioGroup3 = (RadioGroup)findViewById(R.id.answerRadioGroup3);
		answerRadio3_1 = (RadioButton)findViewById(R.id.answerRadio3_1);
		answerRadio3_2 = (RadioButton)findViewById(R.id.answerRadio3_2);
		answerRadio3_3 = (RadioButton)findViewById(R.id.answerRadio3_3);
		answerRadio3_4 = (RadioButton)findViewById(R.id.answerRadio3_4);
		answerCheckBox3_1 = (CheckBox)findViewById(R.id.answerCheckBox3_1);
		answerCheckBox3_2 = (CheckBox)findViewById(R.id.answerCheckBox3_2);
		answerCheckBox3_3 = (CheckBox)findViewById(R.id.answerCheckBox3_3);
		answerCheckBox3_4 = (CheckBox)findViewById(R.id.answerCheckBox3_4);
		
		answerRadioGroup3.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	    {
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
	        	//int id= checkedId.getCheckedRadioButtonId();
	            View radioButton = group.findViewById(checkedId);
	            int radioId = group.indexOfChild(radioButton);
	            RadioButton btn = (RadioButton) group.getChildAt(radioId);
	            String selectedtext = (String) btn.getText();
	            
	            if(answeredlist.containsKey("3")){
	            	answeredlist.remove("3");
	            	List<String> value = new ArrayList<String>();
	            	value.add(selectedtext);
	            	answeredlist.put("3", value);
	            }
	            else{
	            	List<String> value = new ArrayList<String>();
	            	value.add(selectedtext);
	            	answeredlist.put("3", value);
	            }
	            Log.i("Answer in answerRadioGroup3", answeredlist.toString());
	        }
	    });
		
		
		
		question3.setText(que.getQuestion().replace("___", " ").trim());
		List<String> ans = que.getAnswers(); 
		if(que.getType() == 1){
			if(ans.size() == 2){
				answerRadio3_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio3_2.setText(ans.get(1).replace("]]", "").trim());
				answerRadio3_3.setVisibility(View.GONE);
				answerRadio3_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 3){
				answerRadio3_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio3_2.setText(ans.get(1).trim());
				answerRadio3_3.setText(ans.get(2).replace("]]", "").trim());
				answerRadio3_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 4){
				answerRadio3_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio3_2.setText(ans.get(1).trim());
				answerRadio3_3.setText(ans.get(2).trim());
				answerRadio3_4.setText(ans.get(3).replace("]]", "").trim());
			}	
			answerCheckBox3_1.setVisibility(View.GONE);
			answerCheckBox3_2.setVisibility(View.GONE);
			answerCheckBox3_3.setVisibility(View.GONE);
			answerCheckBox3_4.setVisibility(View.GONE);
		}
		else if(que.getType() == 2){
			answerRadio3_1.setVisibility(View.GONE);
			answerRadio3_2.setVisibility(View.GONE);
			answerRadio3_3.setVisibility(View.GONE);
			answerRadio3_4.setVisibility(View.GONE);
			if(ans.size() == 2){
				answerCheckBox3_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox3_2.setText(ans.get(1).replace("]]", "").trim());
				answerCheckBox3_3.setVisibility(View.GONE);
				answerCheckBox3_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 3){
				answerCheckBox3_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox3_2.setText(ans.get(1).trim());
				answerCheckBox3_3.setText(ans.get(2).replace("]]", "").trim());
				answerCheckBox3_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 4){
				answerCheckBox3_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox3_2.setText(ans.get(1).trim());
				answerCheckBox3_3.setText(ans.get(2).trim());
				answerCheckBox3_4.setText(ans.get(3).replace("]]", "").trim());
			}
			
		}
		answerCheckBox3_1.setTag("3"+answerCheckBox3_1.getText());
		answerCheckBox3_2.setTag("3"+answerCheckBox3_2.getText());
		answerCheckBox3_3.setTag("3"+answerCheckBox3_3.getText());
		answerCheckBox3_4.setTag("3"+answerCheckBox3_4.getText());
		answerCheckBox3_1.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox3_2.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox3_3.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox3_4.setOnCheckedChangeListener(checkBoxListener);
	}
	
	
	private void setQuestion4(Question que){
		question4 = (TextView)findViewById(R.id.question4);
		answerRadioGroup4 = (RadioGroup)findViewById(R.id.answerRadioGroup4);
		answerRadio4_1 = (RadioButton)findViewById(R.id.answerRadio4_1);
		answerRadio4_2 = (RadioButton)findViewById(R.id.answerRadio4_2);
		answerRadio4_3 = (RadioButton)findViewById(R.id.answerRadio4_3);
		answerRadio4_4 = (RadioButton)findViewById(R.id.answerRadio4_4);
		answerCheckBox4_1 = (CheckBox)findViewById(R.id.answerCheckBox4_1);
		answerCheckBox4_2 = (CheckBox)findViewById(R.id.answerCheckBox4_2);
		answerCheckBox4_3 = (CheckBox)findViewById(R.id.answerCheckBox4_3);
		answerCheckBox4_4 = (CheckBox)findViewById(R.id.answerCheckBox4_4);
		
		answerRadioGroup4.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	    {
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
	        	//int id= checkedId.getCheckedRadioButtonId();
	            View radioButton = group.findViewById(checkedId);
	            int radioId = group.indexOfChild(radioButton);
	            RadioButton btn = (RadioButton) group.getChildAt(radioId);
	            String selectedtext = (String) btn.getText();
	            
	            if(answeredlist.containsKey("4")){
	            	answeredlist.remove("4");
	            	List<String> value = new ArrayList<String>();
	            	value.add(selectedtext);
	            	answeredlist.put("4", value);
	            }
	            else{
	            	List<String> value = new ArrayList<String>();
	            	value.add(selectedtext);
	            	answeredlist.put("4", value);
	            }
	            Log.i("Answer in answerRadioGroup4", answeredlist.toString());
	        }
	    });
		
		
		question4.setText(que.getQuestion().replace("___", " ").trim());
		List<String> ans = que.getAnswers(); 
		if(que.getType() == 1){
			if(ans.size() == 2){
				answerRadio4_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio4_2.setText(ans.get(1).replace("]]", "").trim());
				answerRadio4_3.setVisibility(View.GONE);
				answerRadio4_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 3){
				answerRadio4_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio4_2.setText(ans.get(1).trim());
				answerRadio4_3.setText(ans.get(2).replace("]]", "").trim());
				answerRadio4_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 4){
				answerRadio4_1.setText(ans.get(0).replace("[[", "").trim());
				answerRadio4_2.setText(ans.get(1).trim());
				answerRadio4_3.setText(ans.get(2).trim());
				answerRadio4_4.setText(ans.get(3).replace("]]", "").trim());
			}	
			answerCheckBox4_1.setVisibility(View.GONE);
			answerCheckBox4_2.setVisibility(View.GONE);
			answerCheckBox4_3.setVisibility(View.GONE);
			answerCheckBox4_4.setVisibility(View.GONE);
		}
		else if(que.getType() == 2){
			answerRadio4_1.setVisibility(View.GONE);
			answerRadio4_2.setVisibility(View.GONE);
			answerRadio4_3.setVisibility(View.GONE);
			answerRadio4_4.setVisibility(View.GONE);
			if(ans.size() == 2){
				answerCheckBox4_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox4_2.setText(ans.get(1).replace("]]", "").trim());
				answerCheckBox4_3.setVisibility(View.GONE);
				answerCheckBox4_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 3){
				answerCheckBox4_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox4_2.setText(ans.get(1).trim());
				answerCheckBox4_3.setText(ans.get(2).replace("]]", "").trim());
				answerCheckBox4_4.setVisibility(View.GONE);
			}
			else if(ans.size() == 4){
				answerCheckBox4_1.setText(ans.get(0).replace("[[", "").trim());
				answerCheckBox4_2.setText(ans.get(1).trim());
				answerCheckBox4_3.setText(ans.get(2).trim());
				answerCheckBox4_4.setText(ans.get(3).replace("]]", "").trim());
			}
			
		}
		
		answerCheckBox4_1.setTag("4"+answerCheckBox4_1.getText());
		answerCheckBox4_2.setTag("4"+answerCheckBox4_2.getText());
		answerCheckBox4_3.setTag("4"+answerCheckBox4_3.getText());
		answerCheckBox4_4.setTag("4"+answerCheckBox4_4.getText());
		answerCheckBox4_1.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox4_2.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox4_3.setOnCheckedChangeListener(checkBoxListener);
		answerCheckBox4_4.setOnCheckedChangeListener(checkBoxListener);
	}
	
	private void deleteQuestion1(){
		question1 = (TextView)findViewById(R.id.question1);
		answerRadioGroup1 = (RadioGroup)findViewById(R.id.answerRadioGroup1);
		answerRadio1_1 = (RadioButton)findViewById(R.id.answerRadio1_1);
		answerRadio1_2 = (RadioButton)findViewById(R.id.answerRadio1_2);
		answerRadio1_3 = (RadioButton)findViewById(R.id.answerRadio1_3);
		answerRadio1_4 = (RadioButton)findViewById(R.id.answerRadio1_4);
		answerCheckBox1_1 = (CheckBox)findViewById(R.id.answerCheckBox1_1);
		answerCheckBox1_2 = (CheckBox)findViewById(R.id.answerCheckBox1_2);
		answerCheckBox1_3 = (CheckBox)findViewById(R.id.answerCheckBox1_3);
		answerCheckBox1_4 = (CheckBox)findViewById(R.id.answerCheckBox1_4);
		
		question1.setVisibility(View.GONE);
		answerRadioGroup1.setVisibility(View.GONE);
		answerRadio1_1.setVisibility(View.GONE);
		answerRadio1_2.setVisibility(View.GONE);
		answerRadio1_3.setVisibility(View.GONE);
		answerRadio1_4.setVisibility(View.GONE);
		answerCheckBox1_1.setVisibility(View.GONE);
		answerCheckBox1_2.setVisibility(View.GONE);
		answerCheckBox1_3.setVisibility(View.GONE);
		answerCheckBox1_4.setVisibility(View.GONE);
	}
	
	private void deleteQuestion2(){
		question2 = (TextView)findViewById(R.id.question2);
		answerRadioGroup2 = (RadioGroup)findViewById(R.id.answerRadioGroup2);
		answerRadio2_1 = (RadioButton)findViewById(R.id.answerRadio2_1);
		answerRadio2_2 = (RadioButton)findViewById(R.id.answerRadio2_2);
		answerRadio2_3 = (RadioButton)findViewById(R.id.answerRadio2_3);
		answerRadio2_4 = (RadioButton)findViewById(R.id.answerRadio2_4);
		answerCheckBox2_1 = (CheckBox)findViewById(R.id.answerCheckBox2_1);
		answerCheckBox2_2 = (CheckBox)findViewById(R.id.answerCheckBox2_2);
		answerCheckBox2_3 = (CheckBox)findViewById(R.id.answerCheckBox2_3);
		answerCheckBox2_4 = (CheckBox)findViewById(R.id.answerCheckBox2_4);
		
		question2.setVisibility(View.GONE);
		answerRadioGroup2.setVisibility(View.GONE);
		answerRadio2_1.setVisibility(View.GONE);
		answerRadio2_2.setVisibility(View.GONE);
		answerRadio2_3.setVisibility(View.GONE);
		answerRadio2_4.setVisibility(View.GONE);
		answerCheckBox2_1.setVisibility(View.GONE);
		answerCheckBox2_2.setVisibility(View.GONE);
		answerCheckBox2_3.setVisibility(View.GONE);
		answerCheckBox2_4.setVisibility(View.GONE);
	}
	
	private void deleteQuestion3(){
		question3 = (TextView)findViewById(R.id.question3);
		answerRadioGroup3 = (RadioGroup)findViewById(R.id.answerRadioGroup3);
		answerRadio3_1 = (RadioButton)findViewById(R.id.answerRadio3_1);
		answerRadio3_2 = (RadioButton)findViewById(R.id.answerRadio3_2);
		answerRadio3_3 = (RadioButton)findViewById(R.id.answerRadio3_3);
		answerRadio3_4 = (RadioButton)findViewById(R.id.answerRadio3_4);
		answerCheckBox3_1 = (CheckBox)findViewById(R.id.answerCheckBox3_1);
		answerCheckBox3_2 = (CheckBox)findViewById(R.id.answerCheckBox3_2);
		answerCheckBox3_3 = (CheckBox)findViewById(R.id.answerCheckBox3_3);
		answerCheckBox3_4 = (CheckBox)findViewById(R.id.answerCheckBox3_4);
		
		question3.setVisibility(View.GONE);
		answerRadioGroup3.setVisibility(View.GONE);
		answerRadio3_1.setVisibility(View.GONE);
		answerRadio3_2.setVisibility(View.GONE);
		answerRadio3_3.setVisibility(View.GONE);
		answerRadio3_4.setVisibility(View.GONE);
		answerCheckBox3_1.setVisibility(View.GONE);
		answerCheckBox3_2.setVisibility(View.GONE);
		answerCheckBox3_3.setVisibility(View.GONE);
		answerCheckBox3_4.setVisibility(View.GONE);
	}
	
	private void deleteQuestion4(){
		question4 = (TextView)findViewById(R.id.question4);
		answerRadioGroup4 = (RadioGroup)findViewById(R.id.answerRadioGroup4);
		answerRadio4_1 = (RadioButton)findViewById(R.id.answerRadio4_1);
		answerRadio4_2 = (RadioButton)findViewById(R.id.answerRadio4_2);
		answerRadio4_3 = (RadioButton)findViewById(R.id.answerRadio4_3);
		answerRadio4_4 = (RadioButton)findViewById(R.id.answerRadio4_4);
		answerCheckBox4_1 = (CheckBox)findViewById(R.id.answerCheckBox4_1);
		answerCheckBox4_2 = (CheckBox)findViewById(R.id.answerCheckBox4_2);
		answerCheckBox4_3 = (CheckBox)findViewById(R.id.answerCheckBox4_3);
		answerCheckBox4_4 = (CheckBox)findViewById(R.id.answerCheckBox4_4);
		
		question4.setVisibility(View.GONE);
		answerRadioGroup4.setVisibility(View.GONE);
		answerRadio4_1.setVisibility(View.GONE);
		answerRadio4_2.setVisibility(View.GONE);
		answerRadio4_3.setVisibility(View.GONE);
		answerRadio4_4.setVisibility(View.GONE);
		answerCheckBox4_1.setVisibility(View.GONE);
		answerCheckBox4_2.setVisibility(View.GONE);
		answerCheckBox4_3.setVisibility(View.GONE);
		answerCheckBox4_4.setVisibility(View.GONE);
	}

	
	private class SubmitAnswer extends AsyncTask<Void,Void,String>{
		String result;
		protected String doInBackground(Void... arg0) {
			try {
				String mansweredlist="";
				for (Map.Entry<String, List<String>> entry : answeredlist.entrySet()) {
				    String key = entry.getKey();
				    List<String> value = entry.getValue();
				    String valuetostr="";
				    for(String v:value)
				    	valuetostr+=v+",,";
				    mansweredlist+=key+":"+valuetostr.substring(0, valuetostr.length()-2)+"___";
				}
	       		String fullPath = new String(TNVMainActivity.SERVICE_URL);
        		fullPath+="post_answers?"+"username="+username+"&";
        		fullPath+="title="+quiztitle+"&";
        		fullPath+="answers="+mansweredlist.substring(0, mansweredlist.length()-3);
        		Log.i("StudentMainActivity fullpath", fullPath);
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
				Toast.makeText(getApplicationContext(), "Hulah! poll posted successfully", 
						   Toast.LENGTH_LONG).show();
				Intent goTOMain = new Intent(StudentMainActivity.this,TNVMainActivity.class);
				startActivity(goTOMain);
				finish();
				}
			else{
				AlertDialog alert = new AlertDialog.Builder(StudentMainActivity.this).create();
				alert.setMessage("Sorry, something went wrong. Try again");
				alert.show();
			}
		}
		
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student_main, menu);
		return true;
	}

}

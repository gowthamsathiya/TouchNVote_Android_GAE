package com.touchnvoteui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ResultTitleSelectionActivity extends Activity {

	private String quizlist;
	Spinner quizListSpinner;
	Button postQuizButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_title_selection);
		Bundle quiztitlelist = getIntent().getExtras();
		if (quiztitlelist != null) {
			quizlist = quiztitlelist.getString("quiztitlelist");
		}
		quizListSpinner = (Spinner)findViewById(R.id.quizTitleSpinner);

		if(quizlist!=null && !quizlist.equals("null")){
			List<String> quizlistarray = new ArrayList<String>();
			if(quizlist.contains("&&")){
				String[] quizfulllist = quizlist.split("&&");
				int i=0;
				for(int item=0;item<quizfulllist.length;item++){
					i++;
					Log.i("result list "+i,quizfulllist[item]);
					quizlistarray.add(quizfulllist[item]);
				}
			}
			else
				quizlistarray.add((String) quizlist);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ResultTitleSelectionActivity.this, android.R.layout.simple_spinner_item , quizlistarray);
			quizListSpinner.setAdapter(adapter);	
			adapter.notifyDataSetChanged();
		}
		else{
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ResultTitleSelectionActivity.this, android.R.layout.simple_spinner_item ,new String[] {"No test started"});
			quizListSpinner.setAdapter(adapter);
		}
		
		postQuizButton = (Button) findViewById(R.id.viewResultButton);
		postQuizButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				new ComputeResultTask().execute();				
			}
			
		});
		
	}
	
	private class ComputeResultTask extends AsyncTask<Void,Void,String>{

		private String result;
		@Override
		protected String doInBackground(Void... arg0) {
			try{
				String fullPath = new String(TNVMainActivity.SERVICE_URL);
				fullPath+="compute_result?";
				fullPath+="quiztitle="+(String)quizListSpinner.getSelectedItem();
				Log.i("path from ResultTitleSelectionActivity",fullPath.toString());
				DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpGet getRequest = new HttpGet(fullPath);
	            HttpResponse response = httpClient.execute(getRequest);
	            ParseResponse presponse=new ParseResponse();
        		result = presponse.getResult(response).toString();
	            //result = getResult(response).toString();
	            Log.i("ResultTextSelectionActivity result", result);
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
			Intent registerIntent = new Intent(ResultTitleSelectionActivity.this,DisplayResultActivity.class);
			registerIntent.putExtra("result", result);
			startActivity(registerIntent);
		}
			
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.post_question, menu);
		return true;
	}

}

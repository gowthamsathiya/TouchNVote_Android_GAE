package com.touchnvoteui;

import java.util.ArrayList;
import java.util.List;

import com.saulpower.piechart.adapter.PieChartAdapter;
import com.saulpower.piechart.views.PieChartView;
import com.saulpower.piechart.views.PieChartView.PieChartAnchor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DisplayResultActivity extends Activity {

	private String resultstring;
	private TextView noResultTitle;
	private PieChartView mChart;
	/*
	private Button answer1Button;
	private Button answer2Button;
	private Button answer3Button;
	private Button answer4Button;
	*/
	private TextView answer1TextView;
	private TextView answer2TextView;
	private TextView answer3TextView;
	private TextView answer4TextView;
	
	private TextView answerOption1TextView;
	private TextView answerOption2TextView;
	private TextView answerOption3TextView;
	private TextView answerOption4TextView;
	private TextView answerOption5TextView;
	private TextView answerOption6TextView;
	private TextView answerOption7TextView;
	private TextView answerOption8TextView;
	private TextView answerOption9TextView;
	private TextView answerOption10TextView;
	private TextView answerOption11TextView;
	private TextView answerOption12TextView;
	private TextView answerOption13TextView;
	private TextView answerOption14TextView;
	private TextView answerOption15TextView;
	private TextView answerOption16TextView;
	
	
	List<String> answercount1 =new ArrayList<String>();
	List<String> answercount2 =new ArrayList<String>();
	List<String> answercount3 =new ArrayList<String>();
	List<String> answercount4 =new ArrayList<String>();
	int total1 = 0,total2 = 0,total3 = 0,total4 = 0;
	String sendingoption="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_result);
		Bundle result = getIntent().getExtras();
		noResultTitle = (TextView)findViewById(R.id.noResultTitle);
		answer1TextView = (TextView)findViewById(R.id.answer1TextView);
		answer2TextView = (TextView)findViewById(R.id.answer2TextView);
		answer3TextView = (TextView)findViewById(R.id.answer3TextView);
		answer4TextView = (TextView)findViewById(R.id.answer4TextView);
		answerOption1TextView = (TextView)findViewById(R.id.answerOption1TextView);
		answerOption2TextView = (TextView)findViewById(R.id.answerOption2TextView);
		answerOption3TextView = (TextView)findViewById(R.id.answerOption3TextView);
		answerOption4TextView = (TextView)findViewById(R.id.answerOption4TextView);
		answerOption5TextView = (TextView)findViewById(R.id.answerOption5TextView);
		answerOption6TextView = (TextView)findViewById(R.id.answerOption6TextView);
		answerOption7TextView = (TextView)findViewById(R.id.answerOption7TextView);
		answerOption8TextView = (TextView)findViewById(R.id.answerOption8TextView);
		answerOption9TextView = (TextView)findViewById(R.id.answerOption9TextView);
		answerOption10TextView = (TextView)findViewById(R.id.answerOption10TextView);
		answerOption11TextView = (TextView)findViewById(R.id.answerOption11TextView);
		answerOption12TextView = (TextView)findViewById(R.id.answerOption12TextView);
		answerOption13TextView = (TextView)findViewById(R.id.answerOption13TextView);
		answerOption14TextView = (TextView)findViewById(R.id.answerOption14TextView);
		answerOption15TextView = (TextView)findViewById(R.id.answerOption15TextView);
		answerOption16TextView = (TextView)findViewById(R.id.answerOption16TextView);
		/*
		answer1Button = (Button)findViewById(R.id.answer1Button);
		answer2Button = (Button)findViewById(R.id.answer2Button);
		answer3Button = (Button)findViewById(R.id.answer3Button);
		answer4Button = (Button)findViewById(R.id.answer4Button);
		*/
		Log.i("Bundle result in DisplayResultActivity", result.getString("result"));
		if (result != null || !result.equals("null")) {

			resultstring = result.getString("result");
			
			String[] res = resultstring.split("///");
			for(String r:res){
				if(r.substring(0, 1).equalsIgnoreCase("1")){
					String[] values = r.split(",,");
					answercount1.add(values[0].substring(1, values[0].length())+":::"+values[1]);
					total1+=Integer.parseInt(values[1]);
					
				}
				else if(r.substring(0, 1).equalsIgnoreCase("2")){
					String[] values = r.split(",,");
					answercount2.add(values[0].substring(1, values[0].length())+":::"+values[1]);
					total2+=Integer.parseInt(values[1]);

				}
				else if(r.substring(0, 1).equalsIgnoreCase("3")){
					String[] values = r.split(",,");
					answercount3.add(values[0].substring(1, values[0].length())+":::"+values[1]);
					total3+=Integer.parseInt(values[1]);

				}
				else if(r.substring(0, 1).equalsIgnoreCase("4")){
					String[] values = r.split(",,");
					answercount4.add(values[0].substring(1, values[0].length())+":::"+values[1]);
					total4+=Integer.parseInt(values[1]);

				}
			}
			/*
			List<Float> slices = new ArrayList<Float>();
			for(String s:answercount1){
				String[] v = s.split(":::");
				float val = (float) Integer.parseInt(v[1])/total1;
				slices.add(val);
			}
			
			PieChartAdapter adapter = new PieChartAdapter(this, slices);
			
			mChart = (PieChartView) findViewById(R.id.chart1);
			//mChart.setDynamics(new FrictionDynamics(0.95f));
			mChart.setSnapToAnchor(PieChartAnchor.BOTTOM);
			mChart.setAdapter(adapter);
			mChart.onResume();
			*/
			
			if(answercount1!=null && answercount1.size()!=0){
				sendingoption = "";
				Log.i("answercount1 result", answercount1.toString());
				Log.i("answercount1 total", Integer.toString(total1));
				noResultTitle.setVisibility(View.INVISIBLE);
				if(answercount1.size()==2){
					answerOption1TextView.setText(answercount1.get(0).replace(":::", " - "));
					answerOption2TextView.setText(answercount1.get(1).replace(":::", " - "));
					answerOption3TextView.setVisibility(View.GONE);
					answerOption4TextView.setVisibility(View.GONE);
				}
				else if(answercount1.size()==3){
					answerOption1TextView.setText(answercount1.get(0).replace(":::", " - "));
					answerOption2TextView.setText(answercount1.get(1).replace(":::", " - "));
					answerOption3TextView.setText(answercount1.get(2).replace(":::", " - "));
					answerOption4TextView.setVisibility(View.GONE);
				}
				else if(answercount1.size()==4){
					answerOption1TextView.setText(answercount1.get(0).replace(":::", " - "));
					answerOption2TextView.setText(answercount1.get(1).replace(":::", " - "));
					answerOption3TextView.setText(answercount1.get(2).replace(":::", " - "));
					answerOption4TextView.setText(answercount1.get(3).replace(":::", " - "));
				}
			}
			else{
				answer1TextView.setVisibility(View.GONE);
				answerOption1TextView.setVisibility(View.GONE);
				answerOption2TextView.setVisibility(View.GONE);
				answerOption3TextView.setVisibility(View.GONE);
				answerOption4TextView.setVisibility(View.GONE);
			}
			if(answercount2!=null && answercount2.size()!=0){
				sendingoption = "";
				Log.i("answercount2 result", answercount2.toString());
				Log.i("answercount2 total", Integer.toString(total2));
				
			//	noResultTitle.setVisibility(View.INVISIBLE);
				if(answercount2.size()==2){
					answerOption5TextView.setText(answercount2.get(0).replace(":::", " - "));
					answerOption6TextView.setText(answercount2.get(1).replace(":::", " - "));
					answerOption7TextView.setVisibility(View.GONE);
					answerOption8TextView.setVisibility(View.GONE);
				}
				else if(answercount2.size()==3){
					answerOption5TextView.setText(answercount2.get(0).replace(":::", " - "));
					answerOption6TextView.setText(answercount2.get(1).replace(":::", " - "));
					answerOption7TextView.setText(answercount2.get(2).replace(":::", " - "));
					answerOption8TextView.setVisibility(View.GONE);
				}
				else if(answercount2.size()==4){
					answerOption5TextView.setText(answercount2.get(0).replace(":::", " - "));
					answerOption6TextView.setText(answercount2.get(1).replace(":::", " - "));
					answerOption7TextView.setText(answercount2.get(2).replace(":::", " - "));
					answerOption8TextView.setText(answercount2.get(3).replace(":::", " - "));
				}
			}
			else{
				answer2TextView.setVisibility(View.GONE);
				answerOption5TextView.setVisibility(View.GONE);
				answerOption6TextView.setVisibility(View.GONE);
				answerOption7TextView.setVisibility(View.GONE);
				answerOption8TextView.setVisibility(View.GONE);
			}
			if(answercount3!=null && answercount3.size()!=0){
				sendingoption = "";
				Log.i("answercount3 result", answercount3.toString());
				Log.i("answercount3 total", Integer.toString(total3));
				
			//	noResultTitle.setVisibility(View.INVISIBLE);
				if(answercount3.size()==2){
					answerOption9TextView.setText(answercount3.get(0).replace(":::", " - "));
					answerOption10TextView.setText(answercount3.get(1).replace(":::", " - "));
					answerOption11TextView.setVisibility(View.GONE);
					answerOption12TextView.setVisibility(View.GONE);
				}
				else if(answercount3.size()==3){
					answerOption9TextView.setText(answercount3.get(0).replace(":::", " - "));
					answerOption10TextView.setText(answercount3.get(1).replace(":::", " - "));
					answerOption11TextView.setText(answercount3.get(2).replace(":::", " - "));
					answerOption12TextView.setVisibility(View.GONE);
				}
				else if(answercount3.size()==4){
					answerOption9TextView.setText(answercount3.get(0).replace(":::", " - "));
					answerOption10TextView.setText(answercount3.get(1).replace(":::", " - "));
					answerOption11TextView.setText(answercount3.get(2).replace(":::", " - "));
					answerOption12TextView.setText(answercount3.get(3).replace(":::", " - "));
				}
			}
			else{
				answer3TextView.setVisibility(View.GONE);
				answerOption9TextView.setVisibility(View.GONE);
				answerOption10TextView.setVisibility(View.GONE);
				answerOption11TextView.setVisibility(View.GONE);
				answerOption12TextView.setVisibility(View.GONE);
			}
			if(answercount4!=null && answercount4.size()!=0){
				sendingoption = "";
				Log.i("answercount4 result", answercount4.toString());
				Log.i("answercount4 total", Integer.toString(total4));
				
				//noResultTitle.setVisibility(View.INVISIBLE);
				if(answercount4.size()==2){
					answerOption13TextView.setText(answercount4.get(0).replace(":::", " - "));
					answerOption14TextView.setText(answercount4.get(1).replace(":::", " - "));
					answerOption15TextView.setVisibility(View.GONE);
					answerOption16TextView.setVisibility(View.GONE);
				}
				else if(answercount4.size()==3){
					answerOption13TextView.setText(answercount4.get(0).replace(":::", " - "));
					answerOption14TextView.setText(answercount4.get(1).replace(":::", " - "));
					answerOption15TextView.setText(answercount4.get(2).replace(":::", " - "));
					answerOption16TextView.setVisibility(View.GONE);
				}
				else if(answercount4.size()==4){
					answerOption13TextView.setText(answercount4.get(0).replace(":::", " - "));
					answerOption14TextView.setText(answercount4.get(1).replace(":::", " - "));
					answerOption15TextView.setText(answercount4.get(2).replace(":::", " - "));
					answerOption16TextView.setText(answercount4.get(3).replace(":::", " - "));
				}
			}
			else{
				answer4TextView.setVisibility(View.GONE);
				answerOption13TextView.setVisibility(View.GONE);
				answerOption14TextView.setVisibility(View.GONE);
				answerOption15TextView.setVisibility(View.GONE);
				answerOption16TextView.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_result, menu);
		return true;
	}

}

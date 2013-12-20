package com.touchnvoteui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.saulpower.piechart.adapter.PieChartAdapter;
import com.saulpower.piechart.extra.Dynamics;
import com.saulpower.piechart.extra.FrictionDynamics;
import com.saulpower.piechart.views.PieChartView;
import com.saulpower.piechart.views.PieChartView.PieChartAnchor;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class ResultPieActivity extends Activity {

	private List<String> answercount1 = new ArrayList<String>();
	private int total1;
	private PieChartView mChart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_pie);
		List<Float> slices = new ArrayList<Float>();
		
		Bundle result = getIntent().getExtras();
		String answer1 = result.getString("answercount1");
		String option[] = answer1.split("///");
		for(String opt:option)
			answercount1.add(opt);
		
		Log.i("answer inside pie activity", answer1);
		Log.i("answer list inside pie activity", answercount1.toString());
		total1 = result.getInt("total1");
		Log.i("total inside pie activity", Integer.toString(total1));
		for(String s:answercount1){
			String[] v = s.split(":::");
			float val = (float) Integer.parseInt(v[1])/total1;
			slices.add(val);
		}
		
		Log.i("Slice: ",slices.toString());
		PieChartAdapter adapter = new PieChartAdapter(this, slices);
		
		mChart = (PieChartView) findViewById(R.id.chart1);
		mChart.setDynamics(new FrictionDynamics(0.95f));
		mChart.setSnapToAnchor(PieChartAnchor.BOTTOM);
		mChart.setAdapter(adapter);
		mChart.onResume();
		
	}

}

/**
* A very simple dynamics implementation with spring-like behavior
*/
class SimpleDynamics extends Dynamics {

/** The friction factor */
private float mFrictionFactor;

/**
 * Creates a SimpleDynamics object
 * 
 * @param frictionFactor The friction factor. Should be between 0 and 1.
 *            A higher number means a slower dissipating speed.
 * @param snapToFactor The snap to factor. Should be between 0 and 1. A
 *            higher number means a stronger snap.
 */
public SimpleDynamics(final float frictionFactor) {
    mFrictionFactor = frictionFactor;
}

@Override
protected void onUpdate(final int dt) {

    // then update the position based on the current velocity
    mPosition += mVelocity * dt / 1000;

    // and finally, apply some friction to slow it down
    mVelocity *= mFrictionFactor;
}
}


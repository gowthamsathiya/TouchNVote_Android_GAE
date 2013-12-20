package com.touchnvoteui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StoreExcelStudents extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_excel_students);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store_excel_students, menu);
		return true;
	}

}

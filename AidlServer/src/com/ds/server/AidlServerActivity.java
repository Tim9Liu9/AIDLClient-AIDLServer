package com.ds.server;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AidlServerActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent service = new Intent(AidlServerActivity.this,
						AidlService.class);
				startService(service);
				Toast.makeText(AidlServerActivity.this, "service started",
						Toast.LENGTH_LONG).show();
			}   

		});  

	}
}
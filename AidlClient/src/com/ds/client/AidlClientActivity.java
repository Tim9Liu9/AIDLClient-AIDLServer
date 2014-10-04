package com.ds.client;

import com.ds.server.IAidlService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AidlClientActivity extends Activity {

	IAidlService iservice; 

	private ServiceConnection connection = new ServiceConnection() {

		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			// 从远程service中获得AIDL实例化对象
			iservice = IAidlService.Stub.asInterface(service);
			Log.i("Client","Bind Success:" + iservice);
		}  

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			iservice = null;
			Log.i("Client","onServiceDisconnected");
		}
	};  
  
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final TextView tv = (TextView) findViewById(R.id.tv);
		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent service = new Intent(IAidlService.class.getName());
				bindService(service, connection, BIND_AUTO_CREATE);
				if (iservice != null) {  
					try {
						tv.setText("" + iservice.getType());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}

			}

		});

	}
}
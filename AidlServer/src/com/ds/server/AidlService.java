package com.ds.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AidlService extends Service {
	
	private int i = 6;

	private IAidlService.Stub mBinder = new IAidlService.Stub() {

		@Override
		public int getType() throws RemoteException {
			// TODO Auto-generated method stub
			return ++i;
		}
	};

	private void Log(String str) { 
		Log.d("AidlService", "------ " + str + "------");
	}

	@Override
	public void onCreate() {
		Log("service create");   
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log("service start id=" + startId);
	}

	@Override
	public IBinder onBind(Intent t) {
		Log("service on bind");
		return mBinder;
	}

	@Override
	public void onDestroy() {
		Log("service on destroy");
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log("service on unbind");
		return super.onUnbind(intent);
	}

	public void onRebind(Intent intent) {
		Log("service on rebind");
		super.onRebind(intent);
	}

}

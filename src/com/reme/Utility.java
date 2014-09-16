package com.reme;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

public class Utility {

	public static void vibrate(int ms, Activity activity) {
		Vibrator vib = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(80);
	}
	
}

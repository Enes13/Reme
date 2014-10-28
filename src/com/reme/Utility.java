package com.reme;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.SeekBar;

public class Utility {

	public static void vibrate(int ms, Activity activity) {
		Vibrator vib = (Vibrator) activity
				.getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(80);
	}

	public static void getUserLocation(Activity activity) {
		LocationManager lm = (LocationManager) activity
				.getSystemService(Context.LOCATION_SERVICE);

		ArrayList<String> providerlist = (ArrayList<String>) lm
				.getAllProviders();
		for (int i = 0; i < providerlist.size(); i++) {
			System.out.println(providerlist.get(i));
		}
	}

	public static boolean isVisible(View v) {
		return v.getVisibility() == View.VISIBLE;
	}

	public static void toggleVisibilty(View v) {
		if (isVisible(v)) {
			v.setVisibility(View.INVISIBLE);
		} else {
			v.setVisibility(View.VISIBLE);
		}
	}

	private Location getLastKnownLocationtByProvider(String provider,
			Activity activity) {
		LocationManager lm = (LocationManager) activity
				.getSystemService(Context.LOCATION_SERVICE);
		LocationListener ll = new LocationListener() {

			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				print("Status " + arg0 + " changed.");

			}

			@Override
			public void onProviderEnabled(String arg0) {
				print("Provider" + arg0 + " enabled.");
			}

			@Override
			public void onProviderDisabled(String arg0) {
				print("Provider " + arg0 + " disabled.");

			}

			@Override
			public void onLocationChanged(Location arg0) {
				print("Location changed to " + arg0.toString());

			}
		};
		String p;
		if (provider.equals("gps")) {
			p = LocationManager.GPS_PROVIDER;
		} else if (provider.equals("network")) {
			p = LocationManager.NETWORK_PROVIDER;
		} else {
			p = provider;
		}
		lm.requestSingleUpdate(p, ll, null);
		Location l = lm.getLastKnownLocation(p);
		print("New Location received from provider " + p + ".");
		return l;
	}

	public static Location getLastKnownLocation(Activity activity) {
		return null;
	}

	private void print(String s) {
		System.out.println(s);
	}

}

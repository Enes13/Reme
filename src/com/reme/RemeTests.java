package com.reme;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import junit.framework.TestCase;

public class RemeTests extends TestCase {

	private void testLocationManager() {
		Activity activity = new Activity();
		LocationManager lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		
		ArrayList<String> providerlist = (ArrayList<String>) lm.getAllProviders();
		for(int i = 0; i < providerlist.size(); i++) {
			System.out.println(providerlist.get(i));
		}
	}
	
}

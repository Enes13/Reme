package com.reme;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class HomeScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homescreen);

		init_hold_to_add();
		initInvisibleButton();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.intro, menu);
		return true;
	}

	private void init_hold_to_add() {
		try {
			InputStream is = getAssets().open("hold_to_add.png");
			Drawable d = Drawable.createFromStream(is, null);

			ImageView iv = (ImageView) findViewById(R.id.imageView1);
			iv.setImageDrawable(d);
		} catch (IOException e) {

		}

	}

	private void initInvisibleButton() {

		Button b = (Button) findViewById(R.id.add_button);
		b.setBackgroundColor(Color.TRANSPARENT);
		b.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				Utility.vibrate(80, HomeScreen.this);
				HomeScreen.this.startEventSettingsActivity();
				return false;
			}
		});
	}

	public void startEventSettingsActivity() {
		Intent i = new Intent(getApplicationContext(), EventSettings.class);
		startActivity(i);
	}

}

package com.reme;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EventSettings extends Activity {

	private String buttonState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventsettings);

		init_or_button();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.intro, menu);
		return true;
	}

	private void init_or_button() {
		this.buttonState = "OR";
		Button b = (Button) findViewById(R.id.or_button);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Button b = (Button) findViewById(R.id.or_button);
				if (EventSettings.this.getButtonState().equals("OR")) {
					EventSettings.this.setButtonState("AND");
					b.setText(R.string.and_capitalized);
				} else {
					EventSettings.this.setButtonState("OR");
					b.setText("OR");
				}

			}
		});
	}

	public String getButtonState() {
		return buttonState;
	}

	public void setButtonState(String buttonState) {
		this.buttonState = buttonState;
	}

}

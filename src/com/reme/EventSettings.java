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

	//	setColors();
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
	
//	private void setColors() {
//		TextView tv1 = (TextView) findViewById(R.id.textView1);
//		TextView tv2 = (TextView) findViewById(R.id.textView2);
//		TextView tv3 = (TextView) findViewById(R.id.textView3);
//		
//		EditText et1 = (EditText) findViewById(R.id.editText1);
//		EditText et2 = (EditText) findViewById(R.id.editText2);
//		
//		Button b1 = (Button) findViewById(R.id.or_button);
//		
//		b1.setTextColor(Color.WHITE);
//		tv1.setTextColor(Color.WHITE);
//		tv2.setTextColor(Color.WHITE);
//		tv3.setTextColor(Color.WHITE);
//		et1.setBackgroundColor(Color.GRAY);
//		et1.setTextColor(Color.WHITE);
//		et2.setBackgroundColor(Color.GRAY);
//		et2.setTextColor(Color.WHITE);
//		
//	}

	public String getButtonState() {
		return buttonState;
	}

	public void setButtonState(String buttonState) {
		this.buttonState = buttonState;
	}

}

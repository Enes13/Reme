package com.reme;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class EventSettings extends Activity {

	private String buttonState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventsettings);

		init_or_button();
		initDistanceSeekBar();
		initSelectDateButton();
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

	private void initDistanceSeekBar() {
		SeekBar sb = (SeekBar) findViewById(R.id.distanceseekbar);
		int value = sb.getProgress();
		TextView tv = (TextView) findViewById(R.id.distancetext);
		tv.setText(value + "m");

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				EventSettings.this.setDistanceTestViewValue(progress);

			}
		});
	}

	private void setDistanceTestViewValue(int value) {
		TextView tv = (TextView) findViewById(R.id.distancetext);
		tv.setText(value + "m");
	}

	private void initSelectDateButton() {
		setCurrentDate();
		final DatePicker dp = (DatePicker) findViewById(R.id.datePicker1);
		Calendar c = Calendar.getInstance();
		dp.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						EditText DD = (EditText) findViewById(R.id.DD);
						EditText MM = (EditText) findViewById(R.id.MM);
						EditText YY = (EditText) findViewById(R.id.YY);

						DD.setText(Integer.toString(dayOfMonth));
						MM.setText(Integer.toString(monthOfYear + 1));
						YY.setText(Integer.toString(year));
					}
				});
		Button b = (Button) findViewById(R.id.selectdatebutton);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				toggleVisibilty(dp);
			}
		});
	}

	private void setCurrentDate() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		EditText DD = (EditText) findViewById(R.id.DD);
		EditText MM = (EditText) findViewById(R.id.MM);
		EditText YY = (EditText) findViewById(R.id.YY);

		DD.setText(Integer.toString(day));
		MM.setText(Integer.toString(month + 1));
		YY.setText(Integer.toString(year));
	}

	private boolean isVisible(View v) {
		return v.getVisibility() == View.VISIBLE;
	}

	private void toggleVisibilty(View v) {
		if (this.isVisible(v)) {
			v.setVisibility(View.INVISIBLE);
		} else {
			v.setVisibility(View.VISIBLE);
		}
	}

	public int getDistanceTestViewValue() {
		SeekBar sb = (SeekBar) findViewById(R.id.distanceseekbar);
		return sb.getProgress();
	}

	public String getButtonState() {
		return buttonState;
	}

	public void setButtonState(String buttonState) {
		this.buttonState = buttonState;
	}

}

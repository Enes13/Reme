package com.reme;

import java.util.Calendar;

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
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class EventSettings extends Activity {

	private String buttonState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventsettings);

		initTimeFields();
		init_or_button();
		initDistanceSeekBar();
		initSelectDateButton();
		initSelectTimeButton();
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

	private void initTimeFields() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		EditText DD = (EditText) findViewById(R.id.DD);
		EditText MM = (EditText) findViewById(R.id.MM);
		EditText YY = (EditText) findViewById(R.id.YY);
		EditText hh = (EditText) findViewById(R.id.hh);
		EditText mm = (EditText) findViewById(R.id.mm);

		DD.setText(Integer.toString(day));
		MM.setText(Integer.toString(month + 1));
		YY.setText(Integer.toString(year));
		hh.setText(Integer.toString(c.get(Calendar.HOUR_OF_DAY)));
		mm.setText(Integer.toString(c.get(Calendar.MINUTE)));
	}

	private void initSelectTimeButton() {
		final Button b = (Button) findViewById(R.id.selecttimebutton);
		final TimePicker tp = (TimePicker) findViewById(R.id.timePicker1);

		tp.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				EditText hh = (EditText) findViewById(R.id.hh);
				EditText mm = (EditText) findViewById(R.id.mm);

				hh.setText(Integer.toString(hourOfDay));
				mm.setText(Integer.toString(minute));

			}
		});

		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EventSettings.this.toggleVisibilty(tp);
			}
		});
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

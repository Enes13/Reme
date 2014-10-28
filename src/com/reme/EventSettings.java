package com.reme;

import java.util.Calendar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
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
	private boolean mapIsVisble;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventsettings);

		Utility.getUserLocation(this);

		initSelectSpecificDateButton();
		initSelectDayButton();

		initTimeFields();
		init_or_button();
		initDistanceSeekBar();
		initMap();
		initSelectLocationButton();
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

	private void initSelectLocationButton() {
		Button b = (Button) findViewById(R.id.selectlocationbutton);

		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EventSettings.this.toggleMapVisibilty();
				System.out.println("Button pressed.");

			}
		});
	}

	private void initMap() {
		FragmentManager fm = getFragmentManager();
		Fragment map = fm.findFragmentById(R.id.mapfragment);
		map.setMenuVisibility(true);
	}

	private void hideMap() {
		FragmentManager fm = getFragmentManager();
		Fragment map = fm.findFragmentById(R.id.mapfragment);
		fm.beginTransaction()
				.setCustomAnimations(android.R.animator.fade_in,
						android.R.animator.fade_out).hide(map).commit();
		this.mapIsVisble = false;
		System.out.println("Map hidden.");
	}

	private void showMap() {
		FragmentManager fm = getFragmentManager();
		Fragment map = fm.findFragmentById(R.id.mapfragment);
		fm.beginTransaction()
				.setCustomAnimations(android.R.animator.fade_in,
						android.R.animator.fade_out).show(map).commit();
		this.mapIsVisble = true;
		System.out.println("Map shown.");
	}

	private void toggleMapVisibilty() {
		if (this.mapIsVisble) {
			this.hideMap();
		} else {
			this.showMap();
		}
	}

	private void initDistanceSeekBar() {
		SeekBar sb = (SeekBar) findViewById(R.id.distanceseekbar);
		int value = sb.getProgress();
		TextView tv = (TextView) findViewById(R.id.distancetext);
		tv.setText(value + "m");

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

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

				Utility.toggleVisibilty(dp);
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
				Utility.toggleVisibilty(tp);
			}
		});
	}

	private void initSelectSpecificDateButton() {
		Button b = (Button) findViewById(R.id.selectspecificdatebutton);

		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				removeDateButtons();

				initSelectDateButton();
				initSelectTimeButton();

				createCalendarField();
				createTimeField();
			}
		});
	}

	private void initSelectDayButton() {
		Button b = (Button) findViewById(R.id.selectdaybutton);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				removeDateButtons();

				initSelectTimeButton();

				createDayField();
				createTimeField();

			}
		});
	}

	private void createDayField() {
		CheckBox mon = (CheckBox) findViewById(R.id.mon);
		CheckBox tue = (CheckBox) findViewById(R.id.tue);
		CheckBox wed = (CheckBox) findViewById(R.id.wed);
		CheckBox thu = (CheckBox) findViewById(R.id.thu);
		CheckBox fri = (CheckBox) findViewById(R.id.fri);
		CheckBox sat = (CheckBox) findViewById(R.id.sat);
		CheckBox sun = (CheckBox) findViewById(R.id.sun);

		Utility.toggleVisibilty(mon);
		Utility.toggleVisibilty(tue);
		Utility.toggleVisibilty(wed);
		Utility.toggleVisibilty(thu);
		Utility.toggleVisibilty(fri);
		Utility.toggleVisibilty(sat);
		Utility.toggleVisibilty(sun);
	}

	private void createTimeField() {
		EditText hh = (EditText) findViewById(R.id.hh);
		EditText mm = (EditText) findViewById(R.id.mm);

		TextView doubledot1 = (TextView) findViewById(R.id.doubledot1);
		TextView time = (TextView) findViewById(R.id.time);

		Button selectTime = (Button) findViewById(R.id.selecttimebutton);

		Utility.toggleVisibilty(hh);
		Utility.toggleVisibilty(mm);
		Utility.toggleVisibilty(doubledot1);
		Utility.toggleVisibilty(selectTime);
		Utility.toggleVisibilty(time);

	}

	private void createCalendarField() {
		TextView date = (TextView) findViewById(R.id.date);
		TextView dot1 = (TextView) findViewById(R.id.dot1);
		TextView dot2 = (TextView) findViewById(R.id.dot2);

		EditText DD = (EditText) findViewById(R.id.DD);
		EditText MM = (EditText) findViewById(R.id.MM);
		EditText YY = (EditText) findViewById(R.id.YY);

		Button selectDate = (Button) findViewById(R.id.selectdatebutton);

		Utility.toggleVisibilty(date);
		Utility.toggleVisibilty(dot1);
		Utility.toggleVisibilty(dot2);
		Utility.toggleVisibilty(DD);
		Utility.toggleVisibilty(MM);
		Utility.toggleVisibilty(YY);
		Utility.toggleVisibilty(selectDate);
	}

	private void removeDateButtons() {
		final Button b = (Button) findViewById(R.id.selectspecificdatebutton);
		final Button b2 = (Button) findViewById(R.id.selectdaybutton);

		b.setVisibility(View.GONE);
		b2.setVisibility(View.GONE);
	}

	private int getDistanceTestViewValue() {
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

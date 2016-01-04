package us.pdinc.oss.votereg.md;

import java.util.Calendar;

import android.R.integer;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BirthdayAndSexActivity extends Activity {

	private Context con;
	private RadioGroup radiogroup;
	private RadioButton male, feMale;
	private TextView Output;
	private Button changeDate;
	private int year;
	private int month;
	private int day;
	static final int DATE_PICKER_ID = 1111;
	private static final int DATE_DIALOG_ID = 1111;
	private RadioButton radioSexButton;
	public static int butID;

	private int mYear;
	private int mMonth;
	private int mDay;
	private int maxYear;
	private int maxMonth;
	private int maxDay;
	private int minYear;
	private int minMonth;
	private int minDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.birthdayandsex);
		con = this;
		radiogroup = (RadioGroup) findViewById(R.id.RadioGroupSex);
		male = (RadioButton) findViewById(R.id.radioMale);
		feMale = (RadioButton) findViewById(R.id.radioFemale);
		Output = (TextView) findViewById(R.id.Output);
		changeDate = (Button) findViewById(R.id.changeDate);

		// setBirthDay();

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		maxYear = mYear - 16;
		maxMonth = mMonth;
		maxDay = mDay;

		minYear = mYear - 100;
		minMonth = mMonth;
		minDay = mDay;

		// display the current date (this method is below)
		updateDisplay(maxYear, maxMonth, maxDay);

		changeDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// On button click show datepicker dialog
				showDialog(DATE_DIALOG_ID);

			}

		});

	}

	private void updateDisplay(int year, int month, int day) {
		// TODO Auto-generated method stub
		Output.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(month + 1).append("-").append(day).append("-")
				.append(year).append(" "));

	}

	private void setBirthDay() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// Show current date

		Output.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("-").append(day).append("-")
				.append(year).append(" "));

		// Button listener to show date picker dialog

	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, YourNameActivity.class);
		startActivity(next);
		finish();

	}

	public void setNext(View v) {
		addListenerOnButton();
	}

	public void addListenerOnButton() {

		// get selected radio button from radioGroup
		int selectedId = radiogroup.getCheckedRadioButtonId();

		// find the radiobutton by returned id
		radioSexButton = (RadioButton) findViewById(selectedId);

		butID = radiogroup.getCheckedRadioButtonId();

		if (butID == -1) {
			Toast.makeText(con, "Male or Female", Toast.LENGTH_SHORT).show();
			// qNo = qNo;
		} else {
			Toast.makeText(con, Output.getText(), Toast.LENGTH_SHORT).show();
			Toast.makeText(con, radioSexButton.getText(), Toast.LENGTH_SHORT)
					.show();

			Intent next = new Intent(con, IdNumbarActivity.class);
			startActivity(next);
			finish();
		}

	}

	// @Override
	// protected Dialog onCreateDialog(int id) {
	// switch (id) {
	// case DATE_PICKER_ID:
	//
	// // open datepicker dialog.
	// // set date picker for current date
	// // add pickerListener listner to date picker
	// return new DatePickerDialog(this, pickerListener, year, month, day);
	// }
	// return null;
	// }
	//
	// private DatePickerDialog.OnDateSetListener pickerListener = new
	// DatePickerDialog.OnDateSetListener() {
	//
	// // when dialog box is closed, below method will be called.
	// @Override
	// public void onDateSet(DatePicker view, int selectedYear,
	// int selectedMonth, int selectedDay) {
	//
	// year = selectedYear;
	// month = selectedMonth;
	// day = selectedDay;
	//
	// // Show selected date
	// Output.setText(new StringBuilder().append(month + 1).append("-")
	// .append(day).append("-").append(year).append(" "));
	//
	// }
	// };

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			// mYear = year;
			// mMonth = monthOfYear;
			// mDay = dayOfMonth;
			//
			// updateDisplay(mYear, mMonth, mDay);

			if (year > maxYear || monthOfYear > maxMonth && year == maxYear
					|| dayOfMonth > maxDay && year == maxYear
					&& monthOfYear == maxMonth) {

				view.updateDate(maxYear, maxMonth, maxDay);
				updateDisplay(maxYear, maxMonth, maxDay);

			} else if (year < minYear || monthOfYear < minMonth
					&& year == minYear || dayOfMonth < minDay
					&& year == minYear && monthOfYear == minMonth) {

				view.updateDate(minYear, minMonth, minDay);
				updateDisplay(minYear, minMonth, minDay);
			} else {

				view.updateDate(year, monthOfYear, dayOfMonth);
				updateDisplay(year, monthOfYear, dayOfMonth);
			}

		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear - 16,
					mMonth, mDay);
		}
		return null;
	}
}

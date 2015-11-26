package us.pdinc.oss.votereg.md;

import java.util.Calendar;

import us.pdinc.oss.votereg.md.utls.AlertMessage;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class NameonLastRegistrationActivity extends Activity {

	private Context con;
	private EditText lastName, firstname, middlename, title;
	private TextView Output;
	private Button changeDate;
	private int year;
	private int month;
	private int day;
	static final int DATE_PICKER_ID = 1111;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.nameonlastregistration);
		con = this;
		lastName = (EditText) findViewById(R.id.LastNameOn);
		firstname = (EditText) findViewById(R.id.FirstNameOn);
		middlename = (EditText) findViewById(R.id.MiddleNameOn);
		title = (EditText) findViewById(R.id.TitleNameOn);

		Output = (TextView) findViewById(R.id.OutputNameOn);
		changeDate = (Button) findViewById(R.id.changeDateNameOn);

		setBirthDay();
	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, SignatureAcivity.class);
		startActivity(next);
		finish();

	}

	public void setNext(View v) {
		checkData();
	}

	public void checkData() {
		if (TextUtils.isEmpty(lastName.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.lastName));
			return;
		}
		if (TextUtils.isEmpty(firstname.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.firstName));
			return;
		} else {
			Intent next = new Intent(con,
					AddressonLastRegistrationActivity.class);
			startActivity(next);
			finish();
		}

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

		changeDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// On button click show datepicker dialog
				showDialog(DATE_PICKER_ID);

			}

		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:

			// open datepicker dialog.
			// set date picker for current date
			// add pickerListener listner to date picker
			return new DatePickerDialog(this, pickerListener, year, month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// Show selected date
			Output.setText(new StringBuilder().append(month + 1).append("-")
					.append(day).append("-").append(year).append(" "));

		}
	};

}
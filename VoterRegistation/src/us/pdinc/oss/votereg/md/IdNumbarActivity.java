package us.pdinc.oss.votereg.md;

import us.pdinc.oss.votereg.md.utls.AlertMessage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class IdNumbarActivity extends Activity implements
		OnCheckedChangeListener {

	private Context con;
	private CheckBox checkBoxID, checkBoxSSN;
	private EditText driversLicenseid, socialSecurity;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String sdriversLicenseid = "driversLicenseidKey";
	public static final String ssocialSecurity = "socialSecurityKey";

	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.idnumbar);
		con = this;
		checkBoxID = (CheckBox) findViewById(R.id.checkBoxID);
		checkBoxSSN = (CheckBox) findViewById(R.id.checkBoxSSN);
		driversLicenseid = (EditText) findViewById(R.id.DriversLicenseid);
		socialSecurity = (EditText) findViewById(R.id.SocialSecurity);

		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		if (sharedpreferences.contains(sdriversLicenseid)) {
			driversLicenseid.setText(sharedpreferences.getString(
					sdriversLicenseid, ""));
		}
		if (sharedpreferences.contains(ssocialSecurity)) {
			socialSecurity.setText(sharedpreferences.getString(ssocialSecurity,
					""));
		}
		checkBoxID.setOnCheckedChangeListener(this);
		checkBoxSSN.setOnCheckedChangeListener(this);
		loadSavedPreferences();

	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, BirthdayAndSexActivity.class);
		startActivity(next);
		finish();

	}

	public void setNext(View v) {
		checkData();

	}

	public void checkData() {

		// streetNumber, streetName, apt, cityTown, zipCode, county;

		if (checkBoxID.isChecked() == false) {
			if (TextUtils.isEmpty(driversLicenseid.getText().toString().trim())) {
				AlertMessage.showMessage(con, getString(R.string.Status),
						getString(R.string.driversLicenseid));
				return;
			}
		}

		if (checkBoxSSN.isChecked() == false) {
			if (TextUtils.isEmpty(socialSecurity.getText().toString().trim())) {
				AlertMessage.showMessage(con, getString(R.string.Status),
						getString(R.string.socialSecurity));
				return;
			}
		}

		if (checkBoxID.isChecked() == false & checkBoxSSN.isChecked() == false) {
			edittextCheck();

		}

		else {
			Intent next = new Intent(con, YourResidenceAddressActivity.class);
			startActivity(next);
			savePreferences();
			finish();
		}

	}

	public void edittextCheck() {
		if (TextUtils.isEmpty(driversLicenseid.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.driversLicenseid));
			return;
		}
		if (TextUtils.isEmpty(socialSecurity.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.socialSecurity));
			return;
		} else {
			Intent next = new Intent(con, YourResidenceAddressActivity.class);
			startActivity(next);
			savePreferences();
			finish();
		}

	}

	private void savePreferences() {
		String driversLicenseids = driversLicenseid.getText().toString();
		String socialSecuritys = socialSecurity.getText().toString();

		SharedPreferences.Editor editor = sharedpreferences.edit();

		editor.putString(sdriversLicenseid, driversLicenseids);
		editor.putString(ssocialSecurity, socialSecuritys);

		editor.commit();

		// Toast.makeText(con, "Save", 1000).show();

	}

	private void loadSavedPreferences() {

		SharedPreferences sharedPreferences = PreferenceManager

		.getDefaultSharedPreferences(this);

		boolean checkBoxValue1 = sharedPreferences.getBoolean(
				"CheckBox_Value1", false);

		// String name = sharedPreferences.getString("storedName", "YourName");

		if (checkBoxValue1) {

			checkBoxID.setChecked(true);

		} else {

			checkBoxID.setChecked(false);

		}

		boolean checkBoxValue2 = sharedPreferences.getBoolean(
				"CheckBox_Value2", false);

		// String name = sharedPreferences.getString("storedName", "YourName");

		if (checkBoxValue2) {

			checkBoxSSN.setChecked(true);

		} else {

			checkBoxSSN.setChecked(false);

		}

	}

	private void savePreferences(String key, boolean value) {

		SharedPreferences sharedPreferences = PreferenceManager

		.getDefaultSharedPreferences(this);

		Editor editor = sharedPreferences.edit();

		editor.putBoolean(key, value);

		editor.commit();

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.checkBoxID:
			savePreferences("CheckBox_Value1", checkBoxID.isChecked());
			break;
		case R.id.checkBoxSSN:
			savePreferences("CheckBox_Value2", checkBoxSSN.isChecked());
			break;
		}
	}

}
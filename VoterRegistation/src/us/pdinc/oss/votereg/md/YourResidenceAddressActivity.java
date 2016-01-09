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
import android.widget.CompoundButton.OnCheckedChangeListener;

public class YourResidenceAddressActivity extends Activity implements
		OnCheckedChangeListener {

	private Context con;
	private EditText streetNumber, streetName, apt, cityTown, zipCode, county;
	private CheckBox baltimoreCityresident;

	public static final String MyPREFERENCES = "MyPrefs";
	public static final String sstreetNumber = "streetNumberKey";
	public static final String sstreetName = "streetNameKey";
	public static final String sapt = "aptKey";
	public static final String scityTown = "cityTownKey";
	public static final String szipCode = "zipCodeKey";
	public static final String scounty = "countyKey";
	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yourresidenceaddress);
		con = this;
		streetNumber = (EditText) findViewById(R.id.StreetNumber);
		streetName = (EditText) findViewById(R.id.StreetName);
		apt = (EditText) findViewById(R.id.Apt);
		cityTown = (EditText) findViewById(R.id.CityTown);

		zipCode = (EditText) findViewById(R.id.ZipCode);
		county = (EditText) findViewById(R.id.County);
		baltimoreCityresident = (CheckBox) findViewById(R.id.checkBoxBaltimoreCityresident);

		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		if (sharedpreferences.contains(sstreetNumber)) {
			streetNumber
					.setText(sharedpreferences.getString(sstreetNumber, ""));
		}
		if (sharedpreferences.contains(sstreetName)) {
			streetName.setText(sharedpreferences.getString(sstreetName, ""));
		}
		if (sharedpreferences.contains(sapt)) {
			apt.setText(sharedpreferences.getString(sapt, ""));
		}
		if (sharedpreferences.contains(scityTown)) {
			cityTown.setText(sharedpreferences.getString(scityTown, ""));
		}
		if (sharedpreferences.contains(szipCode)) {
			zipCode.setText(sharedpreferences.getString(szipCode, ""));
		}
		if (sharedpreferences.contains(scounty)) {
			county.setText(sharedpreferences.getString(scounty, ""));
		}
		loadSavedPreferences();
	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, IdNumbarActivity.class);
		startActivity(next);
		finish();

	}

	public void setNext(View v) {
		checkData();
	}

	public void checkData() {

		// streetNumber, streetName, apt, cityTown, zipCode, county;

		if (TextUtils.isEmpty(streetNumber.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.StreetNumber));
			return;
		}
		if (TextUtils.isEmpty(streetName.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.StreetName));
			return;
		}

		if (TextUtils.isEmpty(cityTown.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.CityTown));
			return;
		}
		if (TextUtils.isEmpty(zipCode.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.ZipCode));
			return;
		}
		if (TextUtils.isEmpty(county.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.County));
			return;
		}

		else {
			Intent next = new Intent(con, YourMailingAddressActivity.class);
			startActivity(next);
			savePreferences();
			finish();
		}

	}

	private void savePreferences() {
		// streetNumber, streetName, apt, cityTown, zipCode, county;

		String streetNumbers = streetNumber.getText().toString();
		String streetNames = streetName.getText().toString();
		String apts = apt.getText().toString();
		String cityTowns = cityTown.getText().toString();
		String zipCodes = zipCode.getText().toString();
		String countys = county.getText().toString();

		SharedPreferences.Editor editor = sharedpreferences.edit();

		editor.putString(sstreetNumber, streetNumbers);
		editor.putString(sstreetName, streetNames);
		editor.putString(sapt, apts);
		editor.putString(scityTown, cityTowns);

		editor.putString(szipCode, zipCodes);
		editor.putString(scounty, countys);
		editor.commit();

		// Toast.makeText(con, "Save", 1000).show();

	}

	private void loadSavedPreferences() {

		SharedPreferences sharedPreferences = PreferenceManager

		.getDefaultSharedPreferences(this);

		boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value",
				false);

		// String name = sharedPreferences.getString("storedName", "YourName");

		if (checkBoxValue) {

			baltimoreCityresident.setChecked(true);

		} else {

			baltimoreCityresident.setChecked(false);

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
		case R.id.checkBoxBaltimoreCityresident:
			savePreferences("CheckBox_Value", baltimoreCityresident.isChecked());
			break;

		}
	}

}
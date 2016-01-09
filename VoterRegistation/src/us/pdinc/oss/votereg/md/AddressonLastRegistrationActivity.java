package us.pdinc.oss.votereg.md;

import us.pdinc.oss.votereg.md.utls.AlertMessage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class AddressonLastRegistrationActivity extends Activity {

	private Context con;
	private EditText streetNumber, streetName, apt, cityTown, zipCode, county;
	
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
		setContentView(R.layout.addressonlastregistration);
		con = this;
		streetNumber = (EditText) findViewById(R.id.StreetNumberOn);
		streetName = (EditText) findViewById(R.id.StreetNameOn);
		apt = (EditText) findViewById(R.id.AptOn);
		cityTown = (EditText) findViewById(R.id.CityTownOn);

		zipCode = (EditText) findViewById(R.id.ZipCodeOn);
		county = (EditText) findViewById(R.id.CountyOn);
		
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
	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, NameonLastRegistrationActivity.class);
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
		if (TextUtils.isEmpty(apt.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.Apt));
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
			Intent next = new Intent(con, SubmissionActivity.class);
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
	
	

}
package us.pdinc.oss.votereg.md;

import us.pdinc.oss.votereg.md.utls.AlertMessage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class AddressonLastRegistrationActivity extends Activity {

	private Context con;
	private EditText streetNumber, streetName, apt, cityTown, zipCode, county;

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
			finish();
		}

	}

}
package us.pdinc.oss.votereg.md;

import us.pdinc.oss.votereg.md.utls.AlertMessage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;

public class YourResidenceAddressActivity extends Activity {

	private Context con;
	private EditText streetNumber, streetName, apt, cityTown, zipCode, county;
	private CheckBox baltimoreCityresident;

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
			finish();
		}

	}

}
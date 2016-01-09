package us.pdinc.oss.votereg.md;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CheckallThatApplyActivity extends Activity implements
		OnCheckedChangeListener {

	private Context con;
	private CheckBox newRegistration, nameChange, partyAffiliationChange,
			addressChange;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.checkallthatapply);
		con = this;
		newRegistration = (CheckBox) findViewById(R.id.checkBoxNewRegistration);
		nameChange = (CheckBox) findViewById(R.id.checkBoxNameChange);
		partyAffiliationChange = (CheckBox) findViewById(R.id.checkBoxPartyAffiliationChange);
		addressChange = (CheckBox) findViewById(R.id.checkBoxAddressChange);

		newRegistration.setOnCheckedChangeListener(this);
		nameChange.setOnCheckedChangeListener(this);
		partyAffiliationChange.setOnCheckedChangeListener(this);
		addressChange.setOnCheckedChangeListener(this);

		loadSavedPreferences();

	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {

		Intent next = new Intent(con, EligibilityActivity.class);
		startActivity(next);
		finish();

	}

	public void setNext(View v) {
		if (newRegistration.isChecked() & nameChange.isChecked()
				& partyAffiliationChange.isChecked()
				& addressChange.isChecked() == true) {

			Intent next = new Intent(con, YourNameActivity.class);
			startActivity(next);
			finish();
		} else {
			Toast.makeText(con, "Please Check All", 1000).show();
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.checkBoxNewRegistration:
			savePreferences("newRegistration", newRegistration.isChecked());
			break;
		case R.id.checkBoxNameChange:
			savePreferences("nameChange", nameChange.isChecked());
			break;
		case R.id.checkBoxPartyAffiliationChange:
			savePreferences("partyAffiliationChange", partyAffiliationChange.isChecked());
			break;
		case R.id.checkBoxAddressChange:
			savePreferences("addressChange", addressChange.isChecked());
			break;

		}
	}

	private void loadSavedPreferences() {

		SharedPreferences sharedPreferences = PreferenceManager

		.getDefaultSharedPreferences(this);

		boolean checkBoxValuenew = sharedPreferences.getBoolean(
				"newRegistration", false);

		// String name = sharedPreferences.getString("storedName", "YourName");

		if (checkBoxValuenew) {

			newRegistration.setChecked(true);

		} else {

			newRegistration.setChecked(false);

		}

		boolean checkBoxValuenameChange = sharedPreferences.getBoolean(
				"nameChange", false);

		// String name = sharedPreferences.getString("storedName", "YourName");

		if (checkBoxValuenameChange) {

			nameChange.setChecked(true);

		} else {

			nameChange.setChecked(false);

		}
		
		boolean checkBoxValuePartyChange = sharedPreferences.getBoolean(
				"partyAffiliationChange", false);

		// String name = sharedPreferences.getString("storedName", "YourName");

		if (checkBoxValuePartyChange) {

			partyAffiliationChange.setChecked(true);

		} else {

			partyAffiliationChange.setChecked(false);

		}
		
		boolean checkBoxValueaddressChange = sharedPreferences.getBoolean(
				"addressChange", false);

		// String name = sharedPreferences.getString("storedName", "YourName");

		if (checkBoxValueaddressChange) {

			addressChange.setChecked(true);

		} else {

			addressChange.setChecked(false);

		}


	}
	
	private void savePreferences(String key, boolean value) {

		SharedPreferences sharedPreferences = PreferenceManager

		.getDefaultSharedPreferences(this);

		Editor editor = sharedPreferences.edit();

		editor.putBoolean(key, value);

		editor.commit();

	}
	

}
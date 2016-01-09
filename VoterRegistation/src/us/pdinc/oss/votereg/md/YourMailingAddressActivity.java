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

public class YourMailingAddressActivity extends Activity implements
		OnCheckedChangeListener {

	private Context con;
	private CheckBox mymailingaddress;
	private EditText mailingaddress;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String smailingaddress = "mailingaddressKay";

	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yourmailingaddress);
		con = this;
		mailingaddress = (EditText) findViewById(R.id.Mailingaddress);
		mymailingaddress = (CheckBox) findViewById(R.id.checkBoxMymailingaddress);

		mymailingaddress.setOnCheckedChangeListener(this);
		loadSavedPreferences();

		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		if (sharedpreferences.contains(smailingaddress)) {
			mailingaddress.setText(sharedpreferences.getString(smailingaddress,
					""));
		}
	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, YourResidenceAddressActivity.class);
		startActivity(next);
		finish();

	}

	public void setNext(View v) {
		checkData();
	}

	public void checkData() {

		// streetNumber, streetName, apt, cityTown, zipCode, county;

		if (mymailingaddress.isChecked() == true) {
			Toast.makeText(con, "Please UNCheck and write your mailingaddress",
					1000).show();
		} else {

			if (TextUtils.isEmpty(mailingaddress.getText().toString().trim())) {
				AlertMessage.showMessage(con, getString(R.string.Status),
						getString(R.string.Mailingaddress));
				return;
			}

			else {
				Intent next = new Intent(con, PartyActivity.class);
				startActivity(next);
				savePreferences();
				finish();
			}
		}

	}

	private void loadSavedPreferences() {

		SharedPreferences sharedPreferences = PreferenceManager

		.getDefaultSharedPreferences(this);

		boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value",
				false);

		// String name = sharedPreferences.getString("storedName", "YourName");

		if (checkBoxValue) {

			mymailingaddress.setChecked(true);

		} else {

			mymailingaddress.setChecked(false);

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
		case R.id.checkBoxMymailingaddress:
			savePreferences("CheckBox_Value", mymailingaddress.isChecked());
			break;

		}
	}

	private void savePreferences() {
		String mailingaddresss = mailingaddress.getText().toString();

		SharedPreferences.Editor editor = sharedpreferences.edit();

		editor.putString(smailingaddress, mailingaddresss);

		editor.commit();

		// Toast.makeText(con, "Save", 1000).show();

	}

}
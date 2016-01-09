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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Toast;

public class EligibilityActivity extends Activity implements
		OnCheckedChangeListener {

	private Context con;
	private CheckBox citizenCheck;
	private ImageView idAttached;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.eligibility);
		con = this;
		citizenCheck = (CheckBox) findViewById(R.id.checkBoxcitizen);
		idAttached = (ImageView) findViewById(R.id.attachedImage);
		citizenCheck.setOnCheckedChangeListener(this);
		loadSavedPreferences();

	}

	public void setCancel(View v) {
		finish();
	}

	public void setNext(View v) {
		if (citizenCheck.isChecked() == true) {
			Intent next = new Intent(con, CheckallThatApplyActivity.class);
			startActivity(next);
			finish();
		} else {
			Toast.makeText(con, "Please Check citizen", 1000).show();
		}

	}

	private void loadSavedPreferences() {

		SharedPreferences sharedPreferences = PreferenceManager

		.getDefaultSharedPreferences(this);

		boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value",
				false);

		// String name = sharedPreferences.getString("storedName", "YourName");

		if (checkBoxValue) {

			citizenCheck.setChecked(true);

		} else {

			citizenCheck.setChecked(false);

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
		case R.id.checkBoxcitizen:
			savePreferences("CheckBox_Value", citizenCheck.isChecked());
			break;

		}
	}

}
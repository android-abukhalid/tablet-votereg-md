package us.pdinc.oss.votereg.md;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class IdNumbarActivity extends Activity {

	private Context con;
	private CheckBox checkBoxID, checkBoxSSN;
	private EditText driversLicenseid, socialSecurity;

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
		if (checkBoxID.isChecked() & checkBoxSSN.isChecked() == true) {

			Intent next = new Intent(con, YourResidenceAddressActivity.class);
			startActivity(next);
			finish();
		} else {
			Toast.makeText(con, "Please Check", 1000).show();
		}

	}

}
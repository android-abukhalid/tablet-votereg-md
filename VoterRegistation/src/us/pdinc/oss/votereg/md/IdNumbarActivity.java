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

		else {
			Intent next = new Intent(con, YourResidenceAddressActivity.class);
			startActivity(next);
			finish();
		}

	}

}
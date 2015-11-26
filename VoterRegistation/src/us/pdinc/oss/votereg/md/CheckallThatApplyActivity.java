package us.pdinc.oss.votereg.md;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.Toast;

public class CheckallThatApplyActivity extends Activity {

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

}
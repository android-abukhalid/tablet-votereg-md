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

public class YourMailingAddressActivity extends Activity {

	private Context con;
	private CheckBox mymailingaddress;
	private EditText mailingaddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yourmailingaddress);
		con = this;
		mailingaddress = (EditText) findViewById(R.id.Mailingaddress);
		mymailingaddress = (CheckBox) findViewById(R.id.checkBoxMymailingaddress);

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
			Toast.makeText(con, "Please UNCheck and write your mailingaddress", 1000).show();
		} else {

			if (TextUtils.isEmpty(mailingaddress.getText().toString().trim())) {
				AlertMessage.showMessage(con, getString(R.string.Status),
						getString(R.string.Mailingaddress));
				return;
			}

			else {
				Intent next = new Intent(con, PartyActivity.class);
				startActivity(next);
				finish();
			}
		}

	}

}
package us.pdinc.oss.votereg.md;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class ContactInfoActivity extends Activity {

	private Context con;
	private EditText daytimePhone, email;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String sdaytimePhone = "daytimePhoneKey";
	public static final String semail = "emailKey";

	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contactinfo);
		con = this;
		daytimePhone = (EditText) findViewById(R.id.DaytimePhone);
		email = (EditText) findViewById(R.id.Email);

		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		if (sharedpreferences.contains(sdaytimePhone)) {
			daytimePhone
					.setText(sharedpreferences.getString(sdaytimePhone, ""));
		}
		if (sharedpreferences.contains(semail)) {
			email.setText(sharedpreferences.getString(semail, ""));
		}

	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, PartyActivity.class);
		startActivity(next);
		finish();

	}

	public void setNext(View v) {
		Intent next = new Intent(con, PollingQuestionsActivity.class);
		startActivity(next);
		savePreferences();
		finish();
	}

	private void savePreferences() {
		String daytimePhones = daytimePhone.getText().toString();
		String emails = email.getText().toString();

		SharedPreferences.Editor editor = sharedpreferences.edit();

		editor.putString(sdaytimePhone, daytimePhones);
		editor.putString(semail, emails);

		editor.commit();

		// Toast.makeText(con, "Save", 1000).show();

	}

}
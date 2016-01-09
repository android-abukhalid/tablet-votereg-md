package us.pdinc.oss.votereg.md;

import us.pdinc.oss.votereg.md.utls.AlertMessage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class YourNameActivity extends Activity {

	private Context con;
	private EditText lastName, firstname, middlename, suffix;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String slastName = "lastNameKey";
	public static final String sfirstname = "firstnameKey";
	public static final String smiddlename = "middlenameKey";
	public static final String ssuffix = "suffixKey";
	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yourname);
		con = this;
		lastName = (EditText) findViewById(R.id.LastName);
		firstname = (EditText) findViewById(R.id.FirstName);
		middlename = (EditText) findViewById(R.id.MiddleName);
		suffix = (EditText) findViewById(R.id.Suffix);

		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);
		

		
		if (sharedpreferences.contains(slastName)) {
			lastName.setText(sharedpreferences.getString(slastName, ""));
	    }
		if (sharedpreferences.contains(sfirstname)) {
			firstname.setText(sharedpreferences.getString(sfirstname, ""));
	    }
		if (sharedpreferences.contains(smiddlename)) {
			middlename.setText(sharedpreferences.getString(smiddlename, ""));
	    }
		if (sharedpreferences.contains(ssuffix)) {
			suffix.setText(sharedpreferences.getString(ssuffix, ""));
	    }
	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, CheckallThatApplyActivity.class);
		startActivity(next);
		finish();

	}

	public void setNext(View v) {

		checkData();
		savePreferences();
	}

	public void checkData() {
		if (TextUtils.isEmpty(lastName.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.lastName));
			return;
		}
		if (TextUtils.isEmpty(firstname.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.firstName));
			return;
		} else {
			Intent next = new Intent(con, BirthdayAndSexActivity.class);
			startActivity(next);
			finish();
		}

	}

	private void savePreferences() {
		String lastNames = lastName.getText().toString();
		String firstnames = firstname.getText().toString();
		String middlenames = middlename.getText().toString();
		String sssuffix = suffix.getText().toString();

		SharedPreferences.Editor editor = sharedpreferences.edit();

		editor.putString(slastName, lastNames);
		editor.putString(sfirstname, firstnames);
		editor.putString(smiddlename, middlenames);
		editor.putString(ssuffix, sssuffix);
		editor.commit();

		//Toast.makeText(con, "Save", 1000).show();

	}

}
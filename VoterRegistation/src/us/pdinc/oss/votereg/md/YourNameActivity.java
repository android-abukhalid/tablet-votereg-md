package us.pdinc.oss.votereg.md;

import us.pdinc.oss.votereg.md.utls.AlertMessage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class YourNameActivity extends Activity {

	private Context con;
	private EditText lastName, firstname, middlename, suffix;

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

}
package us.pdinc.oss.votereg.md;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class EligibilityActivity extends Activity {

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

}
package us.pdinc.oss.votereg.md;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {

	private Context con;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		con = this;

	}

	public void setCancel(View v) {
		finish();
	}

	public void setNext(View v) {
		Intent next = new Intent(con, EligibilityActivity.class);
		startActivity(next);
		finish();

	}

}

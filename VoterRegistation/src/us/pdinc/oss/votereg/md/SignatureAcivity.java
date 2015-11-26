package us.pdinc.oss.votereg.md;

import java.util.Calendar;

import us.pdinc.oss.votereg.md.utls.AlertMessage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignatureAcivity extends Activity {

	private Context con;
	private EditText signature;
	private TextView Output;
	private int year;
	private int month;
	private int day;
	static final int DATE_PICKER_ID = 1111;
	String clear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.signature);
		con = this;
		Output = (TextView) findViewById(R.id.currentDate);
		signature = (EditText) findViewById(R.id.SignatureCanvas);
		// if (signature.getText() == null) {
		// writeTestBitmap(signature.getText().toString());
		// }

		clear = signature.getText().toString();

		canvas();
		setDate();

	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, PollingQuestionsActivity.class);
		startActivity(next);
		finish();

	}

	public void setClear(View v) {

		signature.setText("");
	}

	public void setNext(View v) {
		checkData();
	}

	public void checkData() {
		if (TextUtils.isEmpty(signature.getText().toString().trim())) {
			AlertMessage.showMessage(con, getString(R.string.Status),
					getString(R.string.Signature));
			return;
		} else {
			Intent next = new Intent(con, NameonLastRegistrationActivity.class);
			startActivity(next);
			finish();
		}

	}

	private void setDate() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// Show current date

		Output.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("-").append(day).append("-")
				.append(year).append(" "));

		// Button listener to show date picker dialog

	}

	public void canvas() {
		String captionString = signature.getText().toString();
		if (captionString != null) {

			Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
			paintText.setColor(Color.BLUE);
			paintText.setTextSize(50);
			paintText.setStyle(Style.FILL);
			paintText.setShadowLayer(10f, 10f, 10f, Color.BLACK);

			Rect rectText = new Rect();
			paintText.getTextBounds(captionString, 0, captionString.length(),
					rectText);

			Canvas canvas = new Canvas();
			canvas.drawARGB(255, 0, 255, 0);

			canvas.drawText(captionString, 0, rectText.height(), paintText);

			Toast.makeText(getApplicationContext(),
					"drawText: " + captionString, Toast.LENGTH_LONG).show();

		} else {
			Toast.makeText(getApplicationContext(), "caption empty!",
					Toast.LENGTH_LONG).show();
		}
	}

}
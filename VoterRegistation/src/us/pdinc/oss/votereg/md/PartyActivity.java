package us.pdinc.oss.votereg.md;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class PartyActivity extends Activity {

	private Context con;
	private RadioButton democraticParty, republicanParty, greenParty,
			libertarianParty, unaffiliated, otherSpecify;
	private EditText specify;
	public static int butID;
	private RadioGroup radiogroup;
	private RadioButton radioButton;
	
	private static final String SELECTED_INDEX = "SelectedIndex";

	private OnCheckedChangeListener checkedChangedListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			saveSelectedIndex(checkedId);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.party);
		con = this;
		specify = (EditText) findViewById(R.id.OtherSpecify);
		democraticParty = (RadioButton) findViewById(R.id.checkBoxDemocraticParty);
		republicanParty = (RadioButton) findViewById(R.id.checkBoxRepublicanParty);
		greenParty = (RadioButton) findViewById(R.id.checkBoxGreenParty);
		libertarianParty = (RadioButton) findViewById(R.id.checkBoxLibertarianParty);
		unaffiliated = (RadioButton) findViewById(R.id.checkBoxUnaffiliated);
		otherSpecify = (RadioButton) findViewById(R.id.checkBoxOtherSpecify);

		radiogroup = (RadioGroup) findViewById(R.id.RadioGroupParty);
		
		
		radiogroup.setOnCheckedChangeListener(checkedChangedListener);
		RadioButton rbtn = ((RadioButton) radiogroup
				.findViewById(getSelectedValue()));
		if (rbtn != null) {
			rbtn.setChecked(true);
		}
		
		
	}

	public void setCancel(View v) {
		finish();
	}

	public void setBack(View v) {
		Intent next = new Intent(con, YourMailingAddressActivity.class);
		startActivity(next);
		finish();

	}

	public void setNext(View v) {
		addListenerOnButton();

	}

	public void addListenerOnButton() {

		// get selected radio button from radioGroup
		int selectedId = radiogroup.getCheckedRadioButtonId();

		// find the radiobutton by returned id
		radioButton = (RadioButton) findViewById(selectedId);

		butID = radiogroup.getCheckedRadioButtonId();

		if (butID == -1) {
			Toast.makeText(con, "please select one", Toast.LENGTH_SHORT).show();
			// qNo = qNo;
		} else {

			Toast.makeText(con, radioButton.getText(), Toast.LENGTH_SHORT)
					.show();
			Intent next = new Intent(con, ContactInfoActivity.class);
			startActivity(next);
			finish();
		}

	}
	
	private int getSelectedValue() {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		return pref.getInt(SELECTED_INDEX, -1);
	}

	private void saveSelectedIndex(int value) {
		SharedPreferences.Editor editor = PreferenceManager
				.getDefaultSharedPreferences(this).edit();
		editor.putInt(SELECTED_INDEX, value);
		editor.commit();
	}
	

}
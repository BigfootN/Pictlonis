package org.pictlonis.activity.usersettings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.pictlonis.R;
import org.pictlonis.activity.netmenu.NetMenuActivity;
import org.pictlonis.data.User;
import org.pictlonis.utils.CommonViews;
import org.w3c.dom.Text;

/**
 * Created by bigfoot on 24/11/17.
 */

public class UserSettingsActivity extends Activity implements UserSettingsView, View.OnClickListener {
	private Button valBtn;
	private EditText fnameText;
	private EditText lnameText;
	private EditText emailText;
	private EditText unameText;
	private UserSettingsPresenter presenter;

	private void fillTextView(EditText textView, String text) {
		if (text != null)
			textView.setText(text);
	}

	private void initValBtn() {
		valBtn = findViewById(R.id.save_btn);
		valBtn.setOnClickListener(this);
	}

	private void initViews() {
		fnameText = findViewById(R.id.fname_edit);
		lnameText = findViewById(R.id.lname_edit);
		emailText = findViewById(R.id.mail_edit);
		unameText = findViewById(R.id.name_edit);
	}

	private void fillInformation() {
		User user;

		user = presenter.getCurrentUser();
		fillTextView(fnameText, user.getFirstName());
		fillTextView(lnameText, user.getLastName());
		fillTextView(unameText, user.getName());
		fillTextView(emailText, user.getEmail());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_settings_layout);
		presenter = new UserSettingsPresenterImpl(this);
		initValBtn();
		initViews();
		fillInformation();
	}

	@Override
	public void onFailure(String msg) {
		AlertDialog.Builder dialog;

		dialog = CommonViews.createAlertDialogErr(getApplicationContext(), "Erreur", msg);
		dialog.show();
	}

	@Override
	public void goToNetMenu() {
		Intent i;

		i = new Intent(this, NetMenuActivity.class);
		startActivity(i);
	}

	@Override
	public void onClick(View v) {
		String fnameStr;
		String lnameStr;
		String emailStr;
		String unameStr;

		fnameStr = fnameText.getText().toString();
		lnameStr = lnameText.getText().toString();
		unameStr = unameText.getText().toString();
		emailStr = emailText.getText().toString();

		presenter.validateSettings(unameStr, fnameStr, lnameStr, emailStr);
	}
}

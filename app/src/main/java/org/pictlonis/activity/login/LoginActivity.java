package org.pictlonis.activity.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.pictlonis.R;
import org.pictlonis.activity.netmenu.NetMenuActivity;
import org.pictlonis.data.GameInformation;
import org.pictlonis.utils.CommonViews;

/**
 * Created by bigfoot on 03/11/17.
 */

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {
	private RelativeLayout layout;
	private EditText uName;
	private EditText pwd;
	private Button valBtn;
	private LoginPresenter presenter;

	private void initLayout() {
		setContentView(R.layout.login_layout);
		layout = findViewById(R.id.login_layout);
	}

	private void initBtn() {
		valBtn = findViewById(R.id.login_btn);
		valBtn.setOnClickListener(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		presenter = new LoginPresenterImpl(this);
		uName = findViewById(R.id.uname_in);
		pwd = findViewById(R.id.pwd_in);
		initBtn();
	}

	@Override
	public void onSuccess() {
		Intent i;

		i = new Intent(this, NetMenuActivity.class);
		startActivity(i);
	}

	@Override
	public void onFailure(String msg) {
		AlertDialog.Builder alertDialog;

		alertDialog = CommonViews.createAlertDialogErr(this, "Erreur !", msg);
		alertDialog.show();
	}

	@Override
	public void onClick(View v) {
		presenter.validateLogin(uName.getText().toString(), pwd.getText().toString());
	}
}

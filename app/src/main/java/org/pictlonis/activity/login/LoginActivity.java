package org.pictlonis.activity.login;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.pictlonis.R;

/**
 * Created by bigfoot on 03/11/17.
 */

public class LoginActivity extends Activity implements LoginView {
	private RelativeLayout layout;
	private EditText uName;
	private EditText pwd;

	private void initLayout() {
		setContentView(R.layout.login_layout);
		layout = findViewById(R.id.login_layout);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		uName = findViewById(R.id.uname_in);
		pwd = findViewById(R.id.pwd_in);
	}

	@Override
	public void onSuccess() {

	}

	@Override
	public void onFailure(String msg) {

	}
}

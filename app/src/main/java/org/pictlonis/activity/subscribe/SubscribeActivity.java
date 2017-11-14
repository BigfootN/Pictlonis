package org.pictlonis.activity.subscribe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.pictlonis.R;
import org.pictlonis.activity.main.MainMenuActivity;
import org.pictlonis.utils.CommonViews;

/**
 * Created by bigfoot on 12/11/17.
 */

public class SubscribeActivity extends Activity implements SubscribeView, View.OnClickListener{
	private RelativeLayout layout;
	private EditText pwd;
	private EditText uname;
	private Button valBtn;
	private SubscribePresenter presenter;

	private void initLayout() {
		setContentView(R.layout.subscribe_layout);
		layout = findViewById(R.id.subscribe_layout);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		pwd = layout.findViewById(R.id.pwd_sub);
		uname = layout.findViewById(R.id.uname_sub);
		valBtn = layout.findViewById(R.id.subscribe_btn);
		valBtn.setOnClickListener(this);
		presenter = new SubscribePresenterImpl(this);
	}

	@Override
	public void onSuccess() {
		Intent i;

		i = new Intent(this, MainMenuActivity.class);
		startActivity(i);
	}

	@Override
	public void onFailure(String msg) {
		AlertDialog.Builder alertDialog;

		alertDialog = CommonViews.createAlertDialogErr(this, "Erreur!", msg);
		alertDialog.show();
	}

	@Override
	public void onClick(View v) {
		presenter.validateSubscribtion(uname.getText().toString(), pwd.getText().toString());
	}
}

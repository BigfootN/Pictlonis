package org.pictlonis.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.pictlonis.R;
import org.pictlonis.activity.client.ClientSettingActivity;
import org.pictlonis.activity.host.HostSettingActivity;
import org.pictlonis.activity.login.LoginActivity;
import org.pictlonis.activity.subscribe.SubscribeActivity;
import org.pictlonis.data.GameInformation;

/**
 * Created by bigfoot on 31/10/17.
 */

public class MainMenuActivity extends Activity implements View.OnClickListener, MainMenuView {
	private MainMenuPresenter presenter;
	private Button loginBtn;
	private Button subscribeBtn;

	private void initLayout() {
		setContentView(R.layout.main_menu);
	}

	private void initButtons() {
		subscribeBtn = findViewById(R.id.subscribe);
		loginBtn = findViewById(R.id.login);

		loginBtn.setOnClickListener(this);
		subscribeBtn.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		initButtons();
		try {
			GameInformation.getInstance().setContext(getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		presenter = new MainMenuPresenterImpl(this);
	}

	@Override
	public void onClick(View v) {
		presenter.manageButtonActions(v);
	}

	@Override
	public void goToLogin() {
		Intent i;

		i = new Intent(this, LoginActivity.class);
		startActivity(i);
	}

	@Override
	public void gotToSubscribe() {
		Intent i;

		i = new Intent(this, SubscribeActivity.class);
		startActivity(i);
	}
}

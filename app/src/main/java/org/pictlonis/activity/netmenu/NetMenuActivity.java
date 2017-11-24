package org.pictlonis.activity.netmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.pictlonis.R;
import org.pictlonis.activity.client.ClientSettingActivity;
import org.pictlonis.activity.host.HostSettingActivity;
import org.pictlonis.activity.usersettings.UserSettingsActivity;

/**
 * Created by bigfoot on 31/10/17.
 */

public class NetMenuActivity extends Activity implements View.OnClickListener, NetMenuView {
	private NetMenuPresenter presenter;
	private Button clientBtn;
	private Button hostBtn;
	private Button userSettingsBtn;

	private void initLayout() {
		setContentView(R.layout.net_menu);
	}

	private void initButtons() {
		clientBtn = findViewById(R.id.clt_btn);
		hostBtn = findViewById(R.id.host_btn);
		userSettingsBtn = findViewById(R.id.usersettings_btn);

		clientBtn.setOnClickListener(this);
		hostBtn.setOnClickListener(this);
		userSettingsBtn.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		initButtons();
		presenter = new NetMenuPresenterImpl(this);
	}

	@Override
	public void onClick(View v) {
		presenter.manageButtonActions(v);
	}

	@Override
	public void goToHostMenu() {
		Intent i;

		i = new Intent(this, HostSettingActivity.class);
		startActivity(i);
	}

	@Override
	public void goToClientMenu() {
		Intent i;

		i = new Intent(this, ClientSettingActivity.class);
		startActivity(i);
	}

	@Override
	public void goToUserSettings() {
		Intent i;

		i = new Intent(this, UserSettingsActivity.class);
		startActivity(i);
	}
}

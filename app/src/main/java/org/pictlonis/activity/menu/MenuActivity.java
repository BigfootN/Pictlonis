package org.pictlonis.activity.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.pictlonis.R;
import org.pictlonis.activity.client.ClientSettingActivity;
import org.pictlonis.activity.host.HostSettingActivity;

/**
 * Created by bigfoot on 31/10/17.
 */

public class MenuActivity extends Activity implements View.OnClickListener, MenuView {
	private RelativeLayout layout;
	private MenuPresenter presenter;
	private Button clientBtn;
	private Button hostBtn;

	private void initLayout() {
		setContentView(R.layout.main_menu);
		layout = (RelativeLayout) findViewById(R.id.main_menu);
	}

	private void initButtons() {
		clientBtn = findViewById(R.id.clt_btn);
		hostBtn = findViewById(R.id.host_btn);

		clientBtn.setOnClickListener(this);
		hostBtn.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		initButtons();
		presenter = new MenuPresenterImpl(this);
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
	public void gotToClientMenu() {
		Intent i;

		i = new Intent(this, ClientSettingActivity.class);
		startActivity(i);
	}
}

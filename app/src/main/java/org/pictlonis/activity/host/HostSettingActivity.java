package org.pictlonis.activity.host;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.pictlonis.R;
import org.pictlonis.activity.wait.WaitActivity;
import org.pictlonis.utils.CommonViews;

/**
 * Created by bigfoot on 31/10/17.
 */

public class HostSettingActivity extends Activity implements HostSettingView, View.OnClickListener {
	private RelativeLayout layout;
	private HostSettingPresenter presenter;
	private EditText portText;
	private EditText nbClients;
	private Button validateButton;

	private void initValButton() {
		validateButton = (Button) findViewById(R.id.submit_btn);
		validateButton.setOnClickListener(this);
	}

	private void initLayout() {
		setContentView(R.layout.host_menu);
		layout = (RelativeLayout) findViewById(R.id.host_menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		presenter = new HostSettingPresenterImpl(this);

		portText = (EditText) layout.findViewById(R.id.port_in);
		nbClients = (EditText) layout.findViewById(R.id.nb_clt_in);
		initValButton();
	}

	@Override
	public void onSuccess() {
		Intent i;

		i = new Intent(this, WaitActivity.class);
		startActivity(i);
	}

	@Override
	public void onFailure(String msg) {
		AlertDialog.Builder alertDialog;

		alertDialog = CommonViews.createAlertDialogErr(this, "Erreur creation", msg);
		alertDialog.show();
	}

	@Override
	public void onClick(View v) {
		presenter.validateSettings(nbClients.getText().toString(), portText.getText().toString());
	}
}

package org.pictlonis.activity.client;

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

public class ClientSettingActivity extends Activity implements ClientSettingView, View.OnClickListener {
	private RelativeLayout layout;
	private AlertDialog.Builder alertDialog;
	private Button submitBtn;
	private EditText ipTxt;
	private EditText portTxt;
	private ClientSettingPresenter presenter;

	private void createDialog(String msg) {
		alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
		alertDialog.setTitle("Connexion refusee");
		alertDialog.setMessage(msg);
		alertDialog.setIcon(android.R.drawable.ic_dialog_dialer);
	}

	private void initLayout() {
		setContentView(R.layout.client_menu);
		layout = (RelativeLayout) findViewById(R.id.client_menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();

		ipTxt = layout.findViewById(R.id.ip_in);
		portTxt = layout.findViewById(R.id.port_in);
		submitBtn = layout.findViewById(R.id.submit_btn);
		submitBtn.setOnClickListener(this);

		presenter = new ClientSettingPresenterImpl(this);
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

		alertDialog = CommonViews.createAlertDialogErr(this, "Connexion refusee", msg);
		alertDialog.show();
	}

	@Override
	public void onClick(View v) {
		presenter.validateSettings(ipTxt.getText().toString(), portTxt.getText().toString());
	}
}

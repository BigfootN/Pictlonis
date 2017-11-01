package org.pictlonis.activity.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.RelativeLayout;

import org.pictlonis.R;

/**
 * Created by bigfoot on 31/10/17.
 */

public class ClientSettingActivity extends Activity implements ClientSettingView {
	private RelativeLayout layout;
	private AlertDialog.Builder alertDialog;

	private void createDialog(String msg) {
		alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
		alertDialog.setTitle("Connexion refusee");
		alertDialog.setMessage(msg);
		alertDialog.setIcon(android.R.drawable.ic_dialog_dialer);
	}

	private void initLayout() {
		setContentView(R.layout.client_menu);
		layout = (RelativeLayout) findViewById(R.id.host_menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
	}

	@Override
	public void onSuccess() {
		// aller a la partie draw
	}

	@Override
	public void onFailure(String msg) {
		createDialog(msg);
		alertDialog.show();
	}
}

package org.pictlonis.activity.host;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.pictlonis.R;

/**
 * Created by bigfoot on 31/10/17.
 */

public class HostSettingActivity extends Activity implements HostSettingView, View.OnClickListener {
	private RelativeLayout layout;
	private AlertDialog.Builder alertDialog;
	private HostSettingPresenter presenter;
	private EditText portText;
	private EditText nbClients;
	private Button validateButton;

	private void createDialog(String msg) {
		alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
		alertDialog.setTitle("Connexion refusee");
		alertDialog.setMessage(msg);
		alertDialog.setIcon(android.R.drawable.ic_dialog_dialer);
	}

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
		/*Intent i;

		/*i = new Intent(this, /*start drawing -- connection success*///);
		//startActivity(i);
	}

	@Override
	public void onFailure(String msg) {
		createDialog(msg);
		alertDialog.show();
	}

	@Override
	public void onClick(View v) {
		presenter.validateSettings(nbClients.getText().toString(), portText.getText().toString());
	}
}

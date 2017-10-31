package org.pictlonis.activity.client;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import org.pictlonis.R;

/**
 * Created by bigfoot on 31/10/17.
 */

public class ClientSettingActivity extends Activity {
	private RelativeLayout layout;

	private void initLayout() {
		setContentView(R.layout.client_menu);
		layout = (RelativeLayout) findViewById(R.id.host_menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
	}
}

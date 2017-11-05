package org.pictlonis.activity.wait;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.pictlonis.R;
import org.pictlonis.activity.draw.DrawActivity;

/**
 * Created by bigfoot on 05/11/17.
 */

public class WaitActivity extends Activity implements WaitView {
	private int nbConnected;
	private ProgressBar progBar;
	private TextView txtView;

	private void initLayout() {
		setContentView(R.layout.wait_layout);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		progBar = findViewById(R.id.progressBar);
		txtView = findViewById(R.id.nbConnText);
		nbConnected = 0;
	}

	@Override
	public void onAllConnected() {
		Intent i;

		i = new Intent(this, DrawActivity.class);
		startActivity(i);
	}

	@Override
	public void onFailure(String msg) {

	}
}

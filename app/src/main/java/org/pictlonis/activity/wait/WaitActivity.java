package org.pictlonis.activity.wait;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
	private WaitPresenter presenter;
	Handler handler;

	private void initPresenter() {
		handler = new Handler();
		presenter = new WaitPresenterImpl(this);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!presenter.allPlayersConnected()) {
					try {
						Thread.sleep(500);
						handler.post(new Runnable() {

							@Override
							public void run() {
								presenter.informNbPlayer();

							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

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
		initPresenter();
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

	@Override
	public void setNbPlayer(String msg) {
		txtView.setText(msg);
	}
}

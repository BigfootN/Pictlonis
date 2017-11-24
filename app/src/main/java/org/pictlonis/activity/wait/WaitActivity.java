package org.pictlonis.activity.wait;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.pictlonis.R;
import org.pictlonis.activity.client.ClientSettingActivity;
import org.pictlonis.activity.draw.DrawActivity;
import org.pictlonis.activity.host.HostSettingActivity;
import org.pictlonis.data.GameInformation;

/**
 * Created by bigfoot on 05/11/17.
 */

public class WaitActivity extends Activity implements WaitView {
	private TextView txtView;
	private WaitPresenter presenter;
	Handler handler;

	private class WaitTask implements Runnable {
		private Thread t;

		private void goToDrawActivity() {
			onAllConnected();
		}

		private void postExecute() {
			goToDrawActivity();
		}

		@Override
		public void run() {
			do {
				try {
					Thread.sleep(50);
					handler.post(new Runnable() {

						@Override
						public void run() {
							presenter.informNbPlayer();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (!presenter.allPlayersConnected());

			postExecute();
		}

		public void start() {
			if (t == null) {
				t = new Thread(this);
				t.start();
			}
		}
	}

	private void initPresenter() {
		handler = new Handler();
		presenter = new WaitPresenterImpl(this);
		WaitTask task;

		task = new WaitTask();
		task.start();
	}

	private void initLayout() {
		setContentView(R.layout.wait_layout);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		txtView = findViewById(R.id.nbConnText);
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

	@Override
	public void onBackPressed() {
		Intent i;
		GameInformation inst;

		inst = GameInformation.getInstance();
		try {
			GameInformation.getInstance().getNode().close();
			if (inst.getGameType() == GameInformation.NodeType.CLIENT)
				i = new Intent(this, ClientSettingActivity.class);
			else
				i = new Intent(this, HostSettingActivity.class);

			inst.setNbConnected(0);
			startActivity(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

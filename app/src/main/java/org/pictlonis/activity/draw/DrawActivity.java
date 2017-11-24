package org.pictlonis.activity.draw;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.pictlonis.R;
import org.pictlonis.chat.ChatView;
import org.pictlonis.chat.ChatViewInteractor;
import org.pictlonis.chat.ChatViewInteractorImpl;
import org.pictlonis.chat.ChatViewPresenter;
import org.pictlonis.chat.ChatViewPresenterImpl;
import org.pictlonis.data.GameInformation;
import org.pictlonis.net.message.MessageThread;
import org.pictlonis.net.operations.SocketOperation;
import org.pictlonis.utils.draw.DrawOperation;
import org.pictlonis.utils.draw.DrawOperations;

import static android.view.inputmethod.EditorInfo.IME_NULL;

public class DrawActivity extends Activity implements TextView.OnEditorActionListener, DrawingView, ChatView {
	private Drawer dv;
	private EditText txtChat;
	private LinearLayout scrollLayout;
	private ChatViewPresenter chatPresenter;

	private void initViews() {
		dv = findViewById(R.id.drawer);
		dv.setActivity(this);
		GameInformation.getInstance().setDrawer(dv);

		txtChat = findViewById(R.id.textChat);
		txtChat.setOnEditorActionListener(this);
		GameInformation.getInstance().setChat(this);

		scrollLayout = findViewById(R.id.scrollLayout);
	}

	private void initLayout() {
		setContentView(R.layout.drawing_layout);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		initViews();

		chatPresenter = new ChatViewPresenterImpl(getApplicationContext(), this);
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE)
			chatPresenter.validateMessage(txtChat.getText().toString());
		return true;
	}

	@Override
	public void onFailure(String msg) {

	}

	@Override
	public void addMessage(String msg) {
		final TextView view;
		final ScrollView scroll;

		view = new TextView(this.getApplicationContext());
		view.setText(msg);

		scroll = findViewById(R.id.chatScroll);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				scrollLayout.addView(view);
				scroll.fullScroll(View.FOCUS_DOWN);
			}
		});
	}
}

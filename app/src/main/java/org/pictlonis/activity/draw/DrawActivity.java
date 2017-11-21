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
import org.pictlonis.data.GameInformation;
import org.pictlonis.net.message.MessageThread;
import org.pictlonis.net.operations.SocketOperation;
import org.pictlonis.utils.draw.DrawOperation;
import org.pictlonis.utils.draw.DrawOperations;

public class DrawActivity extends Activity implements TextView.OnEditorActionListener, DrawingView, ChatView {
	private Drawer dv;
	private EditText txtv;
	private DrawPresenter presenter;
	private ScrollView scrollView;
	private ChatViewInteractor chatPresenter;

	private void initViews() {
		dv = findViewById(R.id.drawer);
		dv.setActivity(this);
		GameInformation.getInstance().setDrawer(dv);

		txtv = findViewById(R.id.textChat);
		txtv.setOnEditorActionListener(this);

		scrollView = findViewById(R.id.chatScroll);
	}

	private void initLayout() {
		setContentView(R.layout.drawing_layout);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		initViews();

		presenter = new DrawPresenterImpl(this);
		chatPresenter = new ChatViewInteractorImpl(this);
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_SEND
				|| event.getKeyCode() == KeyEvent.KEYCODE_ENTER
				&& event.getAction() == KeyEvent.ACTION_DOWN)
			presenter.validateMessage(v.getText().toString());
		return true;
	}

	@Override
	public void onFailure(String msg) {

	}

	@Override
	public ScrollView getScrollView() {
		return scrollView;
	}
}

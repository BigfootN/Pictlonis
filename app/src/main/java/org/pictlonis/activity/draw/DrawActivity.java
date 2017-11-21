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
	private RelativeLayout layout;
	private EditText txtv;
	private int drawHeightPerc;
	private int drawWidthPerc;
	private int scrollHeightPerc;
	private int scrollWidthPerc;
	private DrawPresenter presenter;
	private ScrollView scrollView;
	private ChatViewInteractor chatPresenter;

	private void setDrawingViewDim(int heightPerc, int widthPerc) {
		drawHeightPerc = heightPerc;
		drawWidthPerc = widthPerc;
	}

	private void setScrollViewDim(int heightPerc, int widthPerc) {
		scrollHeightPerc = heightPerc;
		scrollWidthPerc = widthPerc;
	}

	private void addViewBottom(View view) {
		RelativeLayout rl;
		RelativeLayout.LayoutParams rlParams;

		rl = layout;
		rlParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		rlParams.addRule(RelativeLayout.ALIGN_BOTTOM, 1);

		rl.addView(view, rlParams);
	}

	private void addViewBottomWeight(View view, int weight) {
		RelativeLayout rl;
		RelativeLayout.LayoutParams rlParams;
		ViewGroup.LayoutParams viewParams;
		int height;
		int width;

		viewParams = view.getLayoutParams();
		height = viewParams.height;
		width = viewParams.width;

		rl = layout;
		rlParams = new RelativeLayout.LayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, weight));
		rlParams.addRule(RelativeLayout.ALIGN_BOTTOM, 1);

		rl.addView(view, rlParams);
	}

	private void initScreenDim() {
		final View view = findViewById(R.id.drawing_layout);
		ViewTreeObserver viewTreeObserver;

		viewTreeObserver = view.getViewTreeObserver();
		if (viewTreeObserver.isAlive()) {
			viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

				@Override
				public void onGlobalLayout() {
					LayoutInflater inflater;
					ViewGroup parent;
					View tmp;
					RelativeLayout rl;
					RelativeLayout.LayoutParams rlParams;

					rl = (RelativeLayout) layout;
					rlParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

					inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					tmp = inflater.inflate(R.layout.drawing_layout, null);
					parent = (ViewGroup) findViewById(R.id.drawing_layout);

					view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

					int screenHeight = view.getHeight();
					int screenWidth = view.getWidth();

					int dvHeight = (screenHeight * drawHeightPerc) / 100;
					int dvWidth = (screenWidth * drawWidthPerc) / 100;

					int svHeight = (screenHeight * scrollHeightPerc) / 100;
					int svWidth = (screenHeight * scrollWidthPerc) / 100;

					int txtvHeight = screenHeight - dvHeight - svHeight;
					int txtvWidth = screenWidth;

					dv.setLayoutParams(new LinearLayout.LayoutParams(dvWidth, dvHeight));
					scrollView.setLayoutParams(new LinearLayout.LayoutParams(svWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
					txtv.setLayoutParams(new LinearLayout.LayoutParams(txtvWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

					addViewBottom(txtv);
					addViewBottom(scrollView);
					addViewBottomWeight(dv, 1);
				}
			});
		}
	}

	private void initViews() {
		dv = new Drawer(this);
		GameInformation.getInstance().setDrawer(dv);

		txtv = new EditText(this);
		txtv.setOnEditorActionListener(this);

		scrollView = new ScrollView(this);
	}

	private void initLayout() {
		setContentView(R.layout.drawing_layout);
		layout = (RelativeLayout) findViewById(R.id.drawing_layout);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		initViews();
		setDrawingViewDim(50, 100);
		setScrollViewDim(30, 100);
		initScreenDim();

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

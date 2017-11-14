package org.pictlonis.activity.draw;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.pictlonis.R;
import org.pictlonis.chat.ChatView;
import org.pictlonis.chat.ChatViewInteractor;
import org.pictlonis.chat.ChatViewInteractorImpl;

public class DrawActivity extends Activity implements TextView.OnEditorActionListener, DrawingView, ChatView{
	private Drawer dv;
	private LinearLayout layout;
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

	private void initScreenDim() {
		final View view = findViewById(R.id.drawing_layout);
		ViewTreeObserver viewTreeObserver;

		viewTreeObserver = view.getViewTreeObserver();
		if (viewTreeObserver.isAlive()) {
			viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

				@Override
				public void onGlobalLayout() {
					view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

					int screenHeight = view.getHeight();
					int screenWidth = view.getWidth();

					int dvHeight = (screenHeight * drawHeightPerc) / 100;
					int dvWidth = (screenWidth * drawWidthPerc) / 100;

					int svHeight = (screenHeight * scrollHeightPerc) / 100;
					int svWidth = (screenHeight * scrollWidthPerc) / 100;

					dv.setLayoutParams(new LinearLayout.LayoutParams(dvWidth, dvHeight));
					scrollView.setLayoutParams(new LinearLayout.LayoutParams(svWidth, screenHeight));
					txtv.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

					layout.addView(dv, 0);
					layout.addView(scrollView, 1);
					layout.addView(txtv, 2);
				}
			});
		}
	}

	private void initViews() {
		dv = new Drawer(this);

		txtv = new EditText(this);
		txtv.setOnEditorActionListener(this);

		scrollView = new ScrollView(this);
	}

	private void initLayout() {
		setContentView(R.layout.main_menu);
		layout = findViewById(R.id.drawing_layout);
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

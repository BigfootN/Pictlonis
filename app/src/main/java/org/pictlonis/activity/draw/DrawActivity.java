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
import android.widget.TextView;

import org.pictlonis.R;

public class DrawActivity extends Activity implements TextView.OnEditorActionListener, DrawingView{
	private Drawer dv;
	private LinearLayout layout;
	private EditText txtv;
	private int heightPerc;
	private int widthPerc;
	private DrawPresenter presenter;

	private void setDrawingViewDim(int heightPerc, int widthPerc) {
		this.heightPerc = heightPerc;
		this.widthPerc = widthPerc;
	}

	private void initScreenDim() {
		final View view = findViewById(R.id.main_layout);
		ViewTreeObserver viewTreeObserver;

		viewTreeObserver = view.getViewTreeObserver();
		if (viewTreeObserver.isAlive()) {
			viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

				@Override
				public void onGlobalLayout() {
					view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

					int screenHeight = view.getHeight();
					int screenWidth = view.getWidth();
					int dvHeight = (screenHeight * heightPerc) / 100;
					int dvWidth = (screenWidth * widthPerc) / 100;

					dv.setLayoutParams(new LinearLayout.LayoutParams(dvWidth, dvHeight));
					txtv.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

					layout.addView(dv, 0);
					layout.addView(txtv, 1);
				}
			});
		}
	}

	private void initViews() {
		dv = new Drawer(this);

		txtv = new EditText(this);
		txtv.setOnEditorActionListener(this);
	}

	private void initLayout() {
		setContentView(R.layout.main_layout);
		layout = findViewById(R.id.main_layout);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
		initViews();
		setDrawingViewDim(60, 100);
		initScreenDim();
		presenter = new DrawPresenterImpl(this);
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
}

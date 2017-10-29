package org.pictlonis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private DrawingView dv;
	private LinearLayout layout;
	private EditText txtv;
	private int heightPerc;
	private int widthPerc;

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
		dv = new DrawingView(this);
		txtv = new EditText(this);
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
	}
}

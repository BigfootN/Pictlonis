package org.pictlonis;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by bigfoot on 21/10/17.
 */

public class EditTextView extends View {
	Context  context;
	Activity host;
	int      height;
	int      width;

	private void getDim() {
		width = host.getWindow().getDecorView().getWidth();
		height = host.getWindow().getDecorView().getHeight();
	}

	private void setDim() {
		getLayoutParams().height = (height * 10) / 100;
		getLayoutParams().width = width;
	}

	private void init() {
		getDim();
		setDim();
	}

	public EditTextView(Context context) {
		super(context);
		this.context = context;
		host = (Activity) context;
		init();
	}


}

package org.pictlonis.activity.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bigfoot on 13/10/17.
 */

public class Drawer extends View {
	Context context;
	private Bitmap bitmap;
	private Canvas canvas;
	private Paint  bitmapPainter;
	private Paint painter;
	private PathManager mPathManager;

	private void initPainter() {
		painter = new Paint();
		painter.setAntiAlias(true);
		painter.setDither(true);
		painter.setColor(Color.GREEN);
		painter.setStyle(Paint.Style.STROKE);
		painter.setStrokeJoin(Paint.Join.ROUND);
		painter.setStrokeCap(Paint.Cap.ROUND);
		painter.setStrokeWidth(5);
	}

	private void touch_start(float x, float y) {
		mPathManager.newPath(x, y);
	}

	private void touch_move(float x, float y) {
		mPathManager.addPoint(x, y);
	}

	private void touch_up() {
		Path path;

		path = mPathManager.getCurrentPath();
		canvas.drawPath(path, painter);
		path.reset();
	}

	public Drawer(Context c) {
		super(c);
		context = c;
		bitmapPainter = new Paint(Paint.DITHER_FLAG);
		mPathManager = new PathManager();
		initPainter();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				touch_start(x, y);
				invalidate();
				break;
			case MotionEvent.ACTION_MOVE:
				touch_move(x, y);
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				touch_up();
				invalidate();
				break;
		}
		return true;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Path path;

		path = mPathManager.getCurrentPath();
		canvas.drawBitmap(bitmap, 0, 0, bitmapPainter);
		canvas.drawPath(path, painter);
	}

	public PathManager getPathManager() {
		return mPathManager;
	}
}

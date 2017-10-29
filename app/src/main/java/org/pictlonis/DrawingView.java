package org.pictlonis;

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

public class DrawingView extends View {
	public int width;
	public int height;
	Context context;
	private Bitmap bitmap;
	private Canvas canvas;
	private Path   path;
	private Paint  bitmapPaint;
	private Paint  paint;
	private Paint  circlePaint;
	private Path   circlePath;
	private float  x, y;

	private void initPainter() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setColor(Color.GREEN);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(12);
	}

	private void initCirclePaint() {
		circlePaint = new Paint();
		circlePaint.setAntiAlias(true);
		circlePaint.setColor(Color.BLUE);
		circlePaint.setStyle(Paint.Style.STROKE);
		circlePaint.setStrokeJoin(Paint.Join.MITER);
		circlePaint.setStrokeWidth(4f);
	}

	private void touch_start(float x, float y) {
		path.reset();
		path.moveTo(x, y);
		this.x = x;
		this.y = y;
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - this.x);
		float dy = Math.abs(y - this.y);

		path.quadTo(this.x, this.y, (x + this.x) / 2, (y + this.y) / 2);
		this.x = x;
		this.y = y;
	}

	private void touch_up() {
		path.lineTo(x, y);
		circlePath.reset();
		canvas.drawPath(path, paint);
		path.reset();
	}

	public DrawingView(Context c) {
		super(c);
		context = c;
		path = new Path();
		circlePath = new Path();
		bitmapPaint = new Paint(Paint.DITHER_FLAG);
		initCirclePaint();
		initPainter();
		this.paint = paint;
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

		canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
		canvas.drawPath(path, paint);
	}
}

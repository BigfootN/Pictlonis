package org.pictlonis.activity.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import org.pictlonis.data.GameInformation;
import org.pictlonis.net.message.NetworkMessage;
import org.pictlonis.net.message.PictlonisMessage;

/**
 * Created by bigfoot on 13/10/17.
 */

public class Drawer extends View {
	private Context context;
	private Bitmap bitmap;
	private Canvas canvas;
	private Paint  bitmapPainter;
	private Paint painter;
	private PathManager mPathManager;
	private GameInformation gameInfoInstance;

	public enum PosType {
		START,
		MOVE,
		UP
	};

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

	private void sendCurPos(float x, float y, PosType type) {
		String msg;
		PointF point;

		point = new PointF(x, y);
		msg = null;

		if (type == PosType.MOVE)
			msg = PictlonisMessage.curPoint(point);
		else if (type == PosType.START)
			msg = PictlonisMessage.startPoint(point);
		else if (type == PosType.UP)
			msg = PictlonisMessage.lastPoint(point);

		try {
			gameInfoInstance.getNode().sendMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Drawer(Context c) {
		super(c);
		context = c;
		bitmapPainter = new Paint(Paint.DITHER_FLAG);
		mPathManager = new PathManager();
		initPainter();
		gameInfoInstance = GameInformation.getInstance();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x;
		float y;
		boolean isPlayer;

		isPlayer = gameInfoInstance.isPlayer();
		if (isPlayer) {
			x = event.getX();
			y = event.getY();
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					touch_start(x, y);
					sendCurPos(x, y, PosType.START);
					break;
				case MotionEvent.ACTION_MOVE:
					touch_move(x, y);
					sendCurPos(x, y, PosType.MOVE);
					break;
				case MotionEvent.ACTION_UP:
					touch_up();
					sendCurPos(x, y, PosType.UP);
					break;
			}
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

	public void touch_start(float x, float y) {
		mPathManager.newPath(x, y);
		invalidate();
	}

	public void touch_move(float x, float y) {
		mPathManager.addPoint(x, y);
		invalidate();
	}

	public void touch_up() {
		Path path;

		path = mPathManager.getCurrentPath();
		canvas.drawPath(path, painter);
		path.reset();
		invalidate();
	}
}

package org.pictlonis.utils.draw;

import android.graphics.PointF;

/**
 * Created by bigfoot on 21/11/17.
 */

public class DrawOperation {
	private DrawOperationType type;
	private PointF point;

	public enum DrawOperationType {
		NEW,
		MOVE,
		UP
	}

	public DrawOperation(DrawOperationType type, PointF point) {
		this.type = type;
		this.point = point;
	}

	public PointF getPoint() {
		return point;
	}

	public DrawOperationType getType() {
		return type;
	}
}

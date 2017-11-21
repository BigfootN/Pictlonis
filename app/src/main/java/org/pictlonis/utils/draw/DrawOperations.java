package org.pictlonis.utils.draw;

import org.pictlonis.activity.draw.Drawer;
import org.pictlonis.net.message.MessageInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by bigfoot on 21/11/17.
 */

public class DrawOperations {
	private ArrayList<DrawOperation> listOps;
	private final static DrawOperations instance = new DrawOperations();


	private DrawOperations() {
		listOps = new ArrayList();
	}

	public static DrawOperations getInstance() {
		return instance;
	}

	public void addDrawOperation(DrawOperation newDrawOp) {
		synchronized (listOps) {
			listOps.add(newDrawOp);
		}
	}

	public DrawOperation getLastOperation() {
		DrawOperation ret;

		ret = null;

		synchronized (listOps) {
			if (!listOps.isEmpty())
				ret = listOps.get(listOps.size() - 1);
		}

		return ret;
	}
}

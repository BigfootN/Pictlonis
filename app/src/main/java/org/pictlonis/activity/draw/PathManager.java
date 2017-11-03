package org.pictlonis.activity.draw;

import android.graphics.Path;
import java.util.ArrayList;

/**
 * Created by bigfoot on 03/11/17.
 */

public class PathManager {
	private ArrayList<Path> mPaths;
	private Path mCurrentPath;

	private void addNewPath() {
		mCurrentPath = new Path();
		mCurrentPath.reset();

		mPaths.add(mCurrentPath);
	}

	public PathManager() {
		mPaths = new ArrayList<Path>();

		addNewPath();
	}

	public void addPoint(float x, float y) {
		mCurrentPath.lineTo(x, y);
		mCurrentPath.moveTo(x, y);
	}

	public void newPath(float x, float y) {
		addNewPath();

		mCurrentPath.moveTo(x, y);
	}

	public Path getCurrentPath() {
		return mCurrentPath;
	}

	public ArrayList<Path> getPaths() {
		return mPaths;
	}
}

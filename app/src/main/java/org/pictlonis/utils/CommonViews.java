package org.pictlonis.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by bigfoot on 04/11/17.
 */

public class CommonViews {

	private static AlertDialog.Builder noIconDialog(Context context, String title, String msg) {
		AlertDialog.Builder ret;

		ret = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
		ret.setTitle(title);
		ret.setMessage(msg);

		return ret;
	}

	public static AlertDialog.Builder createAlertDialogErr(Context context, String title, String msg) {
		AlertDialog.Builder ret;

		ret = noIconDialog(context, title, msg);
		ret.setIcon(android.R.drawable.ic_dialog_dialer);

		return null;
	}

	public static AlertDialog.Builder createAlertDialog(Context context, int icon, String title, String msg) {
		AlertDialog.Builder ret;

		ret = noIconDialog(context, title, msg);
		ret.setIcon(icon);

		return null;
	}

	public static TextView createChatMessage(Context ctx, String msg, int gravity) {
		TextView ret;

		ret = new TextView(ctx);
		ret.setGravity(gravity);
		ret.setText(msg);

		return ret;
	}
}

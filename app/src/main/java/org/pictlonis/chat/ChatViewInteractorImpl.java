package org.pictlonis.chat;

import android.content.Context;
import android.widget.ScrollView;
import android.widget.TextView;

import org.pictlonis.utils.CommonViews;

/**
 * Created by bigfoot on 04/11/17.
 */

public class ChatViewInteractorImpl implements ChatViewInteractor{
	private ScrollView view;
	private int idx;
	private Context context;

	public ChatViewInteractorImpl(ChatView view) {
		this.view = view.getScrollView();
		context = this.view.getContext();
		idx = 0;
	}

	@Override
	public void addMessage(String sender, String message, int gravity) {
		TextView textView;
		String messageSent;

		messageSent = sender + ": " + message;
		textView = CommonViews.createChatMessage(context, messageSent, gravity);
		view.addView(textView, idx);
		idx++;
	}
}

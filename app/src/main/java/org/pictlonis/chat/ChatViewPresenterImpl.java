package org.pictlonis.chat;

import android.content.Context;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by bigfoot on 04/11/17.
 */

public class ChatViewPresenterImpl implements ChatViewPresenter {
	private ChatView view;
	ChatViewInteractor interactor;
	Context ctx;

	public ChatViewPresenterImpl(Context ctx, ChatView view) {
		this.view = view;
		this.ctx = ctx;
		interactor = new ChatViewInteractorImpl();
	}

	@Override
	public void validateMessage(String message) {
		if (message != null) {
			view.addMessage(message);
			interactor.sendMessage(message);
		}
	}
}

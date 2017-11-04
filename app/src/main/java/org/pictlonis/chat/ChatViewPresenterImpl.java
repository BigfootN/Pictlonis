package org.pictlonis.chat;

/**
 * Created by bigfoot on 04/11/17.
 */

public class ChatViewPresenterImpl implements ChatViewPresenter {
	private ChatView view;
	ChatViewInteractor interactor;

	public ChatViewPresenterImpl(ChatView view) {
		this.view = view;
		interactor = new ChatViewInteractorImpl(view);
	}

	@Override
	public void validateMessage(String sender, String message, int gravity) {
		if (sender != null
				&& message != null
				&& gravity != 0)
			interactor.addMessage(sender, message, gravity);

	}
}

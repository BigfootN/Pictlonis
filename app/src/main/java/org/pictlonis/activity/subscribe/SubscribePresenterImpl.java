package org.pictlonis.activity.subscribe;

import org.pictlonis.data.User;
import org.pictlonis.utils.Utils;

/**
 * Created by bigfoot on 13/11/17.
 */

public class SubscribePresenterImpl implements SubscribePresenter {
	private SubscribeView view;
	private SubscribeInteractor interactor;

	public SubscribePresenterImpl(SubscribeView view) {
		this.view = view;
		interactor = new SubscribeInteractorImpl();
	}

	@Override
	public void validateSubscribtion(String uname, String fname, String lname, String email, String pwd) {
		User user;
		if (uname == null || pwd == null)
			view.onFailure("Informations manquantes!");
		else if (Utils.containsWhiteSpace(uname))
			view.onFailure("Nom d'utilisateur contient des espaces!");
		else {
			try {
				user = new User(uname, pwd, email, fname, lname);
				interactor.saveUser(user);
				view.onSuccess();
			} catch (Exception e) {
				view.onFailure(e.getMessage());
			}
		}
	}
}

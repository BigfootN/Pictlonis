package org.pictlonis.activity.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bigfoot on 09/11/17.
 */

public class LoginPresenterImpl implements LoginPresenter {
	private LoginView view;
	private LoginInteractor interactor;

	private boolean containsWhiteSpace(String str) {
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile("\\s");
		matcher = pattern.matcher(str);

		return matcher.find();
	}

	public LoginPresenterImpl(LoginView view) {
		this.view = view;
		interactor = new LoginInteractorImpl();
	}

	@Override
	public void validateLogin(String uname, String pwd) {
		if (uname == null || pwd == null) {
			view.onFailure("Nom d'utilisateur ou mot de passe vide");
		} else if (containsWhiteSpace(uname)) {
			view.onFailure("Nom d'utilisateur contient des espaces");
		} else {
			if (interactor.loginIsValid(uname, pwd))
				view.onSuccess();
			else
				view.onFailure("Identifiants non valides");
		}
	}
}

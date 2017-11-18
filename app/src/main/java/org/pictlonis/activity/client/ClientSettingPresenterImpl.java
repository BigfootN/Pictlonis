package org.pictlonis.activity.client;

import org.pictlonis.utils.Utils;

/**
 * Created by bigfoot on 31/10/17.
 */

public class ClientSettingPresenterImpl implements ClientSettingPresenter {
	private ClientSettingView view;
	private ClientSettingInteractor interactor;
	private int portParsed;


	private boolean isValidIp(String ip) {
		if (ip == null)
			return false;

		if (ip.length() < 7)
			return false;

		return true;
	}

	private boolean isValidPort(String port) {
		if (!Utils.isNumeric(port))
			return false;

		portParsed = ((Double) Double.parseDouble(port)).intValue();
		return true;
	}

	public ClientSettingPresenterImpl(ClientSettingView view) {
		this.view = view;
		interactor = new ClientSettingInteractorImpl();
	}

	@Override
	public void validateSettings(String ip, String port) {
		if (!isValidIp(ip))
			view.onFailure("Addresse IP incorrecte!");
		else if (!isValidPort(port))
			view.onFailure("Port incorrecte !");
		else {
			try {
				interactor.connect(ip, portParsed).launch();
				view.onSuccess();
			} catch (Exception e) {
				view.onFailure(e.getMessage());
			}
		}
	}
}

package org.pictlonis.activity.host;

import org.pictlonis.Utils;

/**
 * Created by bigfoot on 31/10/17.
 */

public class HostSettingPresenterImpl implements HostSettingPresenter {
	private HostSettingView view;
	private HostSettingInteractor interactor;
	private int nbClientsParsed;
	private int portParsed;

	private boolean isValidNbClients(String nbClients) {
		if (!Utils.isNumeric(nbClients))
			return false;

		nbClientsParsed = ((Double) Double.parseDouble(nbClients)).intValue();
		if (nbClientsParsed < 0 || nbClientsParsed > 4)
			return false;

		return true;
	}

	private boolean isValidPort(String port) {
		if (!Utils.isNumeric(port))
			return false;


		portParsed = ((Double) Double.parseDouble(port)).intValue();
		if (portParsed < 0 || portParsed > 65535)
			return false;

		return true;
	}


	public HostSettingPresenterImpl(HostSettingView view) {
		this.view = view;
		interactor = new HostSettingInteractorImpl();
	}

	@Override
	public void validateSettings(String nbClients, String port) {
		if (!isValidPort(port))
			view.onFailure("Port non valide!");
		else if (!isValidNbClients(nbClients)) {
			view.onFailure("Nombre de clients non valide!");
		} else {
			try {
				interactor.createServer(portParsed, nbClientsParsed);
				view.onSuccess();
			} catch (Exception e) {
				view.onFailure(e.getMessage());
			}
		}
	}
}

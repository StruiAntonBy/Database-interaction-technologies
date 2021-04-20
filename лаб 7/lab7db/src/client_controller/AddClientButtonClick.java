package client_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.ClientEditFrame;
import view.ClientsListFrame;

public class AddClientButtonClick implements ActionListener {
	private ClientsListFrame clientsListFrame;
	private IocContainer container;

	public AddClientButtonClick(ClientsListFrame clientsListFrame, IocContainer container) {
		this.clientsListFrame = clientsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ClientEditFrame(clientsListFrame, container);
	}
}
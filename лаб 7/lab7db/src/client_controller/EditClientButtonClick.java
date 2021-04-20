package client_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Client;
import ioc.IocContainer;
import view.ClientEditFrame;
import view.ClientsListFrame;

public class EditClientButtonClick implements ActionListener {
	private ClientsListFrame clientsListFrame;
	private IocContainer container;

	public EditClientButtonClick(ClientsListFrame clientsListFrame, IocContainer container) {
		this.clientsListFrame = clientsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Client client = clientsListFrame.getSelectedClient();
		if(client != null) {
			new ClientEditFrame(clientsListFrame, client, container);
		} else {
			JOptionPane.showMessageDialog(clientsListFrame, "No client is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}

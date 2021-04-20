package client_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Client;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ClientService;
import service.ServiceException;
import view.ClientEditFrame;

public class SaveClientButtonClick implements ActionListener {
	private ClientEditFrame clientEditFrame;
	private IocContainer container;

	public SaveClientButtonClick(ClientEditFrame clientEditFrame, IocContainer container) {
		this.clientEditFrame = clientEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Client client = clientEditFrame.getClient();
		if(client != null) {
			try {
				ClientService service = container.getClientService();
				service.save(client);
				JOptionPane.showMessageDialog(clientEditFrame, "Data was saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				clientEditFrame.update(service.getAll());
				clientEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(clientEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
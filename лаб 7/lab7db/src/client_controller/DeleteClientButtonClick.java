package client_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.ClientService;
import service.ServiceException;
import view.ClientEditFrame;

public class DeleteClientButtonClick implements ActionListener {
	private ClientEditFrame clientEditFrame;
	private IocContainer container;

	public DeleteClientButtonClick(ClientEditFrame clientEditFrame, IocContainer container) {
		this.clientEditFrame = clientEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = clientEditFrame.getClientId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(clientEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					ClientService service = container.getClientService();
					service.delete(id);
					JOptionPane.showMessageDialog(clientEditFrame, "Data was successfully deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
					clientEditFrame.update(service.getAll());
					clientEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(clientEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}

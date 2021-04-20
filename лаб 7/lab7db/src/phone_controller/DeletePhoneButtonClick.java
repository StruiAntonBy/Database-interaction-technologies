package phone_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.PhoneService;
import service.ServiceException;
import view.PhoneEditFrame;

public class DeletePhoneButtonClick implements ActionListener {
	private PhoneEditFrame phoneEditFrame;
	private IocContainer container;

	public DeletePhoneButtonClick(PhoneEditFrame phoneEditFrame, IocContainer container) {
		this.phoneEditFrame = phoneEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = phoneEditFrame.getPhoneId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(phoneEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					PhoneService service = container.getPhoneService();
					service.delete(id);
					JOptionPane.showMessageDialog(phoneEditFrame, "Data was successfully deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
					phoneEditFrame.update(service.getAll());
					phoneEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(phoneEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
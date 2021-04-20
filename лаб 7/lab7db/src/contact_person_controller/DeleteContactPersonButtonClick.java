package contact_person_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import service.ContactPersonService;
import view.ContactPersonEditFrame;

public class DeleteContactPersonButtonClick implements ActionListener {
	private ContactPersonEditFrame personEditFrame;
	private IocContainer container;

	public DeleteContactPersonButtonClick(ContactPersonEditFrame personEditFrame, IocContainer container) {
		this.personEditFrame = personEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = personEditFrame.getPersonId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(personEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					ContactPersonService service = container.getContactPersonService();
					service.delete(id);
					JOptionPane.showMessageDialog(personEditFrame, "Data was successfully deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
					personEditFrame.update(service.getAll());
					personEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(personEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}

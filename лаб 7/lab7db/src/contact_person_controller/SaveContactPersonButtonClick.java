package contact_person_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.ContactPerson;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ContactPersonService;
import service.ServiceException;
import view.ContactPersonEditFrame;

public class SaveContactPersonButtonClick implements ActionListener {
	private ContactPersonEditFrame personEditFrame;
	private IocContainer container;

	public SaveContactPersonButtonClick(ContactPersonEditFrame personEditFrame, IocContainer container) {
		this.personEditFrame = personEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		ContactPerson person = personEditFrame.getTester();
		if(person != null) {
			try {
				ContactPersonService service = container.getContactPersonService();
				service.save(person);
				JOptionPane.showMessageDialog(personEditFrame, "Data was saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				personEditFrame.update(service.getAll());
				personEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(personEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

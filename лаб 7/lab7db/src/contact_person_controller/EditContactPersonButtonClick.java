package contact_person_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.ContactPerson;
import ioc.IocContainer;
import view.ContactPersonEditFrame;
import view.ContactPersonsListFrame;

public class EditContactPersonButtonClick implements ActionListener {
	private ContactPersonsListFrame personsListFrame;
	private IocContainer container;

	public EditContactPersonButtonClick(ContactPersonsListFrame personsListFrame, IocContainer container) {
		this.personsListFrame = personsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ContactPerson person = personsListFrame.getSelectedPerson();
		if(person != null) {
			new ContactPersonEditFrame(personsListFrame, person, container);
		} else {
			JOptionPane.showMessageDialog(personsListFrame, "No contact person is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
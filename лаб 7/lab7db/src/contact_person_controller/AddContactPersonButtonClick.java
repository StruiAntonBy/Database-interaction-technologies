package contact_person_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.ContactPersonEditFrame;
import view.ContactPersonsListFrame;

public class AddContactPersonButtonClick implements ActionListener {
	private ContactPersonsListFrame personsListFrame;
	private IocContainer container;

	public AddContactPersonButtonClick(ContactPersonsListFrame personsListFrame, IocContainer container) {
		this.personsListFrame = personsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ContactPersonEditFrame(personsListFrame, container);
	}
}

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.ContactPerson;
import ioc.IocContainer;
import table_model.ContactPersonListTableModel;
import contact_person_controller.AddContactPersonButtonClick;
import contact_person_controller.EditContactPersonButtonClick;

public class ContactPersonsListFrame extends JFrame {
	private ContactPersonListTableModel model;
	private JTable personsListTable;

	public ContactPersonsListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of contact persons");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new ContactPersonListTableModel();
		personsListTable = new JTable(model);
		personsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(personsListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addContactPersonButton = new JButton("Add");
			addContactPersonButton.setPreferredSize(new Dimension(100, 30));
			addContactPersonButton.addActionListener(new AddContactPersonButtonClick(this, container));
			buttonsPanel.add(addContactPersonButton);
			JButton editContactPersonButton = new JButton("Edit");
			editContactPersonButton.setPreferredSize(new Dimension(100, 30));
			editContactPersonButton.addActionListener(new EditContactPersonButtonClick(this, container));
			buttonsPanel.add(editContactPersonButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setPersons(List<ContactPerson> persons) {
		model.setPersons(persons);
	}

	public ContactPerson getSelectedPerson() {
		int index = personsListTable.getSelectedRow();
		if(index != -1) {
			return model.getPerson(index);
		}
		return null;
	}
}
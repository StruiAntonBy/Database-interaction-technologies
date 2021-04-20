package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.ContactPerson;
import ioc.IocContainer;
import contact_person_controller.DeleteContactPersonButtonClick;
import contact_person_controller.SaveContactPersonButtonClick;

public class ContactPersonEditFrame extends JDialog{
	private ContactPerson person;
	private JTextField surnameTextField,nameTextField,middlenameTextField;

	private ContactPersonEditFrame(JFrame owner, ContactPerson person, String title, IocContainer container) {
		super(owner, title);
		this.person = person;
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 325);
		setResizable(false);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(surnameLabel, constraints);
		add(surnameLabel);

		surnameTextField = new JTextField(person.getSurname());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(surnameTextField, constraints);
		add(surnameTextField);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(nameLabel, constraints);
		add(nameLabel);

		nameTextField = new JTextField(person.getName());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(nameTextField, constraints);
		add(nameTextField);
		
		JLabel middlenameLabel = new JLabel("Middle name:");
		middlenameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(middlenameLabel, constraints);
		add(middlenameLabel);
		
		middlenameTextField = new JTextField(person.getMiddleName());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(middlenameTextField, constraints);
		add(middlenameTextField);

		JButton saveButton = new JButton("Save");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveContactPersonButtonClick(this, container));
		add(saveButton);

		if(person.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 3;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteContactPersonButtonClick(this, container));
			add(deleteButton);
		} else {
			JLabel emptyLabel = new JLabel();
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(emptyLabel, constraints);
			add(emptyLabel);
		}

		setVisible(true);
	}

	public ContactPersonEditFrame(JFrame owner, ContactPerson person, IocContainer container) {
		this(owner, person, String.format("Editing the contact person %s %s %s", person.getSurname(), person.getName(), person.getMiddleName()), container);
	}

	public ContactPersonEditFrame(JFrame owner, IocContainer container) {
		this(owner, new ContactPerson(), "Adding an contact person", container);
	}

	public ContactPerson getTester() {
		String surname = surnameTextField.getText();
		if(surname.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Surname» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		person.setSurname(surname);
		String name = new String(nameTextField.getText());
		if(name.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Name» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		person.setName(name);
		String middlename = new String(middlenameTextField.getText());
		if(middlename.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Middle name» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		person.setMiddleName(middlename);
		return person;
	}

	public Long getPersonId() {
		return person != null ? person.getId() : null;
	}

	public void update(List<ContactPerson> persons) {
		((ContactPersonsListFrame)getOwner()).setPersons(persons);
	}
}
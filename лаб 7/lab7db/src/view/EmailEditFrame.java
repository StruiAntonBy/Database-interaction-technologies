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
import javax.swing.text.PlainDocument;

import domain.Email;
import helper.DigitFilter;
import ioc.IocContainer;
import email_controller.DeleteEmailButtonClick;
import email_controller.SaveEmailButtonClick;

public class EmailEditFrame extends JDialog{
	private Email email;
	private JTextField loginTextField,contactpersonidTextField;

	private EmailEditFrame(JFrame owner, Email email, String title, IocContainer container) {
		super(owner, title);
		this.email = email;
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

		JLabel loginLabel = new JLabel("Login:");
		loginLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(loginLabel, constraints);
		add(loginLabel);

		loginTextField = new JTextField(email.getLogin());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(loginTextField, constraints);
		add(loginTextField);

		JLabel contactpersonidLabel = new JLabel("Contact person id:");
		contactpersonidLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(contactpersonidLabel, constraints);
		add(contactpersonidLabel);
		
		if(email.getContactPersonId()==null) {
			contactpersonidTextField = new JTextField();
		}
		else {
			contactpersonidTextField = new JTextField(String.valueOf(email.getContactPersonId()));
		}
		PlainDocument doc = (PlainDocument) contactpersonidTextField.getDocument();
		doc.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(contactpersonidTextField, constraints);
		add(contactpersonidTextField);
		
		JButton saveButton = new JButton("Save");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveEmailButtonClick(this, container));
		add(saveButton);

		if(email.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteEmailButtonClick(this, container));
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

	public EmailEditFrame(JFrame owner, Email email, IocContainer container) {
		this(owner, email, String.format("Editing the e-mail %s %s",email.getLogin(),email.getContactPersonId()), container);
	}

	public EmailEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Email(), "Adding an e-mail", container);
	}

	public Email getEmail() {
		String login = loginTextField.getText();
		if(login.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Login» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		email.setLogin(login);
		String contactpersonid = new String(contactpersonidTextField.getText());
		if(contactpersonid.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Contact person id» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		email.setContactPersonId(Long.parseLong(contactpersonid));
		return email;
	}

	public Long getEmailId() {
		return email != null ? email.getId() : null;
	}

	public void update(List<Email> emails) {
		((EmailsListFrame)getOwner()).setEmails(emails);
	}
}
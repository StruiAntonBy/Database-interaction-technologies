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

import domain.Phone;
import helper.DigitFilter;
import ioc.IocContainer;
import phone_controller.DeletePhoneButtonClick;
import phone_controller.SavePhoneButtonClick;

public class PhoneEditFrame extends JDialog{
	private Phone phone;
	private JTextField numberTextField,contactpersonidTextField;

	private PhoneEditFrame(JFrame owner, Phone phone, String title, IocContainer container) {
		super(owner, title);
		this.phone = phone;
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

		JLabel numberLabel = new JLabel("Number:");
		numberLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(numberLabel, constraints);
		add(numberLabel);

		numberTextField = new JTextField(phone.getNumber());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(numberTextField, constraints);
		add(numberTextField);

		JLabel contactpersonidLabel = new JLabel("Contact person id:");
		contactpersonidLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(contactpersonidLabel, constraints);
		add(contactpersonidLabel);
		
		if(phone.getContactPersonId()==null) {
			contactpersonidTextField = new JTextField();
		}
		else {
			contactpersonidTextField = new JTextField(String.valueOf(phone.getContactPersonId()));
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
		saveButton.addActionListener(new SavePhoneButtonClick(this, container));
		add(saveButton);

		if(phone.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeletePhoneButtonClick(this, container));
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

	public PhoneEditFrame(JFrame owner, Phone phone, IocContainer container) {
		this(owner, phone, String.format("Editing the phone %s %s",phone.getNumber(),phone.getContactPersonId()), container);
	}

	public PhoneEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Phone(), "Adding an phone", container);
	}

	public Phone getPhone() {
		String number = numberTextField.getText();
		if(number.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Number» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		phone.setNumber(number);
		String contactpersonid = new String(contactpersonidTextField.getText());
		if(contactpersonid.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Contact person id» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		phone.setContactPersonId(Long.parseLong(contactpersonid));
		return phone;
	}

	public Long getPhoneId() {
		return phone != null ? phone.getId() : null;
	}

	public void update(List<Phone> phones) {
		((PhonesListFrame)getOwner()).setPhones(phones);
	}
}
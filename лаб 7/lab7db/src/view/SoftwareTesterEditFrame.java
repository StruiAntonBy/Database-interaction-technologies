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

import domain.SoftwareTester;
import helper.DigitFilter;
import ioc.IocContainer;
import software_tester_controller.DeleteSoftwareTesterButtonClick;
import software_tester_controller.SaveSoftwareTesterButtonClick;

public class SoftwareTesterEditFrame extends JDialog{
	private SoftwareTester tester;
	private JTextField surnameTextField,nameTextField,middlenameTextField,workexperienceTextField;

	private SoftwareTesterEditFrame(JFrame owner, SoftwareTester tester, String title, IocContainer container) {
		super(owner, title);
		this.tester = tester;
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

		surnameTextField = new JTextField(tester.getSurname());
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

		nameTextField = new JTextField(tester.getName());
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
		
		middlenameTextField = new JTextField(tester.getMiddleName());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(middlenameTextField, constraints);
		add(middlenameTextField);
		
		JLabel workexperienceLabel = new JLabel("Work experience:");
		workexperienceLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(workexperienceLabel, constraints);
		add(workexperienceLabel);
		
		workexperienceTextField = new JTextField(String.valueOf(tester.getWorkExperience()));
		PlainDocument doc = (PlainDocument) workexperienceTextField.getDocument();
		doc.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(workexperienceTextField, constraints);
		add(workexperienceTextField);

		JButton saveButton = new JButton("Save");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveSoftwareTesterButtonClick(this, container));
		add(saveButton);

		if(tester.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 4;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteSoftwareTesterButtonClick(this, container));
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

	public SoftwareTesterEditFrame(JFrame owner, SoftwareTester tester, IocContainer container) {
		this(owner, tester, String.format("Editing the tester %s %s %s %s", tester.getSurname(), tester.getName(), tester.getMiddleName(), tester.getWorkExperience()), container);
	}

	public SoftwareTesterEditFrame(JFrame owner, IocContainer container) {
		this(owner, new SoftwareTester(), "Adding an user", container);
	}

	public SoftwareTester getTester() {
		String surname = surnameTextField.getText();
		if(surname.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Surname» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		tester.setSurname(surname);
		String name = new String(nameTextField.getText());
		if(name.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Name» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		tester.setName(name);
		String middlename = new String(middlenameTextField.getText());
		if(middlename.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Middle name» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		tester.setMiddleName(middlename);
		int work_experience = Integer.parseInt(workexperienceTextField.getText());
		tester.setWorkExperience(work_experience);
		return tester;
	}

	public Long getTesterId() {
		return tester != null ? tester.getId() : null;
	}

	public void update(List<SoftwareTester> testers) {
		((SoftwareTestersListFrame)getOwner()).setTesters(testers);
	}
}
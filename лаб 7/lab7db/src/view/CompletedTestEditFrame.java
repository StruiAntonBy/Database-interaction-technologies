package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import domain.CompletedTest;
import helper.DigitFilter;
import ioc.IocContainer;
import completed_test_controller.DeleteCompletedTestButtonClick;
import completed_test_controller.SaveCompletedTestButtonClick;

public class CompletedTestEditFrame extends JDialog{
	private CompletedTest test;
	private JTextField testeridTextField,planedtestidTextField,startdateandtimeTextField,lengthTextField;
	private JRadioButton leftButton,rightButton;

	private CompletedTestEditFrame(JFrame owner, CompletedTest test, String title, IocContainer container) {
		super(owner, title);
		this.test = test;
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 500);
		setResizable(false);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		JLabel testeridLabel = new JLabel("Tester id:");
		testeridLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(testeridLabel, constraints);
		add(testeridLabel);

		if(test.getTesterId()==null) {
			testeridTextField = new JTextField();
		}
		else {
			testeridTextField = new JTextField(String.valueOf(test.getTesterId()));
		}
		PlainDocument doc = (PlainDocument) testeridTextField.getDocument();
		doc.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(testeridTextField, constraints);
		add(testeridTextField);

		JLabel planedtestidLabel = new JLabel("Planed test id:");
		planedtestidLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(planedtestidLabel, constraints);
		add(planedtestidLabel);

		if(test.getPlanedTestId()==null) {
			planedtestidTextField = new JTextField();
		}
		else {
			planedtestidTextField = new JTextField(String.valueOf(test.getPlanedTestId()));
		}
		PlainDocument doc1 = (PlainDocument) planedtestidTextField.getDocument();
		doc1.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(planedtestidTextField, constraints);
		add(planedtestidTextField);
		
		JLabel startdateandtimeLabel = new JLabel("Start date:");
		startdateandtimeLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(startdateandtimeLabel, constraints);
		add(startdateandtimeLabel);
		
		startdateandtimeTextField = new JTextField(test.getStartDateAndTime());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(startdateandtimeTextField, constraints);
		add(startdateandtimeTextField);
		
		JLabel lengthLabel = new JLabel("Length:");
		lengthLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(lengthLabel, constraints);
		add(lengthLabel);
		
		lengthTextField = new JTextField(String.valueOf(test.getLength()));
		PlainDocument doc2 = (PlainDocument) lengthTextField.getDocument();
		doc2.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(lengthTextField, constraints);
		add(lengthTextField);
		
		JLabel resultLabel = new JLabel("Result:");
		resultLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(resultLabel, constraints);
		add(resultLabel);
		
		ButtonGroup group = new ButtonGroup();
		if(test.getResult()==null) {
			test.setResult("");
		}
		switch(test.getResult()) {
			case "ïðîøåë":
				leftButton = new JRadioButton("passed", true);
				rightButton = new JRadioButton("not pass", false);
				break;
			case "íå ïðîøåë":
				leftButton = new JRadioButton("passed", false);
				rightButton = new JRadioButton("not pass", true);
				break;
			default:
				leftButton = new JRadioButton("passed", false);
				rightButton = new JRadioButton("not pass", false);
				break;
		}
		group.add(leftButton);
		group.add(rightButton);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(leftButton, constraints);
		add(leftButton);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(rightButton, constraints);
		add(rightButton);

		JButton saveButton = new JButton("Save");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveCompletedTestButtonClick(this, container));
		add(saveButton);

		if(test.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 5;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteCompletedTestButtonClick(this, container));
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

	public CompletedTestEditFrame(JFrame owner, CompletedTest test, IocContainer container) {
		this(owner, test, String.format("Editing the completed test %s %s %s %s %s", test.getTesterId(), test.getPlanedTestId(), test.getStartDateAndTime(), test.getLength(), test.getResult()), container);
	}

	public CompletedTestEditFrame(JFrame owner, IocContainer container) {
		this(owner, new CompletedTest(), "Adding an completed test", container);
	}

	public CompletedTest getTest() {
		String testerid = testeridTextField.getText();
		if(testerid.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Tester id» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		test.setTesterId(Long.parseLong(testerid));
		String planedtestid = new String(planedtestidTextField.getText());
		if(planedtestid.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Planed test id» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		test.setPlanedTestId(Long.parseLong(planedtestid));
		String startdateandtime = new String(startdateandtimeTextField.getText());
		if(startdateandtime.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Start date» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		test.setStartDateAndTime(startdateandtime);
		String length = new String(lengthTextField.getText());
		if(length.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Length» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		test.setLength(Integer.parseInt(length));
		if(leftButton.isSelected()) {
			test.setResult("ïðîøåë");
		}
		else if(rightButton.isSelected()) {
			test.setResult("íå ïðîøåë");
		}
		else {
			JOptionPane.showMessageDialog(getOwner(), "The «Result» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return test;
	}

	public Long getTestId() {
		return test != null ? test.getId() : null;
	}

	public void update(List<CompletedTest> tests) {
		((CompletedTestsListFrame)getOwner()).setTests(tests);
	}
}
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

import domain.PlanedTest;
import helper.DigitFilter;
import ioc.IocContainer;
import planed_test_controller.DeletePlanedTestButtonClick;
import planed_test_controller.SavePlanedTestButtonClick;

public class PlanedTestEditFrame extends JDialog{
	private PlanedTest test;
	private JTextField requirementidTextField,descriptionoftheperformanceTextField,expectedresultTextField,plannedtimeTextField;
	private JRadioButton leftButton,centerButton,rightButton;

	private PlanedTestEditFrame(JFrame owner, PlanedTest test, String title, IocContainer container) {
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

		JLabel requirementidLabel = new JLabel("Requirement id:");
		requirementidLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(requirementidLabel, constraints);
		add(requirementidLabel);

		if(test.getRequirementId()==null) {
			requirementidTextField = new JTextField();
		}
		else {
			requirementidTextField = new JTextField(String.valueOf(test.getRequirementId()));
		}
		PlainDocument doc = (PlainDocument) requirementidTextField.getDocument();
		doc.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(requirementidTextField, constraints);
		add(requirementidTextField);

		JLabel descriptionoftheperformanceLabel = new JLabel("Description of the performance:");
		descriptionoftheperformanceLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(descriptionoftheperformanceLabel, constraints);
		add(descriptionoftheperformanceLabel);

		descriptionoftheperformanceTextField = new JTextField(test.getDescriptionOfThePerformance());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(descriptionoftheperformanceTextField, constraints);
		add(descriptionoftheperformanceTextField);
		
		JLabel expectedresultLabel = new JLabel("Expected result:");
		expectedresultLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(expectedresultLabel, constraints);
		add(expectedresultLabel);
		
		expectedresultTextField = new JTextField(test.getExpectedResult());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(expectedresultTextField, constraints);
		add(expectedresultTextField);
		
		JLabel plannedtimeLabel = new JLabel("Planned time:");
		plannedtimeLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(plannedtimeLabel, constraints);
		add(plannedtimeLabel);
		
		plannedtimeTextField = new JTextField(String.valueOf(test.getPlannedTime()));
		PlainDocument doc1 = (PlainDocument) plannedtimeTextField.getDocument();
		doc1.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(plannedtimeTextField, constraints);
		add(plannedtimeTextField);
		
		JLabel leveltestLabel = new JLabel("Level test:");
		leveltestLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(leveltestLabel, constraints);
		add(leveltestLabel);
		
		ButtonGroup group = new ButtonGroup();
		if(test.getLevelTest()==null) {
			test.setLevelTest("");
		}
		switch(test.getLevelTest()) {
			case "ãëóáîêèé":
				leftButton = new JRadioButton("deep", true);
				centerButton = new JRadioButton("medium", false);
				rightButton = new JRadioButton("surface", false);
				break;
			case "ñðåäíèé":
				leftButton = new JRadioButton("deep", false);
				centerButton = new JRadioButton("medium", true);
				rightButton = new JRadioButton("surface", false);
				break;
			case "ïîâåðõíîñòíûé":
				leftButton = new JRadioButton("deep", false);
				centerButton = new JRadioButton("medium", false);
				rightButton = new JRadioButton("surface", true);
				break;
			default:
				leftButton = new JRadioButton("deep", false);
				centerButton = new JRadioButton("medium", false);
				rightButton = new JRadioButton("surface", false);
				break;
		}
		group.add(leftButton);
		group.add(centerButton);
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
		layout.setConstraints(centerButton, constraints);
		add(centerButton);
		constraints.gridwidth = 1;
		constraints.gridx = 3;
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
		saveButton.addActionListener(new SavePlanedTestButtonClick(this, container));
		add(saveButton);

		if(test.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 5;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeletePlanedTestButtonClick(this, container));
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

	public PlanedTestEditFrame(JFrame owner, PlanedTest test, IocContainer container) {
		this(owner, test, String.format("Editing the planed test %s %s %s %s %s", test.getRequirementId(),test.getDescriptionOfThePerformance(),test.getExpectedResult(),test.getPlannedTime(),test.getLevelTest()), container);
	}

	public PlanedTestEditFrame(JFrame owner, IocContainer container) {
		this(owner, new PlanedTest(), "Adding an planed test", container);
	}

	public PlanedTest getTest() {
		String requirementid = requirementidTextField.getText();
		if(requirementid.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Requirement id» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		test.setRequirementId(Long.parseLong(requirementid));
		String descriptionoftheperformance = new String(descriptionoftheperformanceTextField.getText());
		if(descriptionoftheperformance.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Description of the performance» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		test.setDescriptionOfThePerformance(descriptionoftheperformance);
		String expectedresult = new String(expectedresultTextField.getText());
		if(expectedresult.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Expected result» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		test.setExpectedResult(expectedresult);
		String plannedtime = new String(plannedtimeTextField.getText());
		if(plannedtime.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Planned time» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		test.setPlannedTime(Integer.parseInt(plannedtime));
		if(leftButton.isSelected()) {
			test.setLevelTest("ãëóáîêèé");
		}
		else if(centerButton.isSelected()) {
			test.setLevelTest("ñðåäíèé");
		}
		else if(rightButton.isSelected()) {
			test.setLevelTest("ïîâåðõíîñòíûé");
		}
		else {
			JOptionPane.showMessageDialog(getOwner(), "The «Level test» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return test;
	}

	public Long getTestId() {
		return test != null ? test.getId() : null;
	}

	public void update(List<PlanedTest> tests) {
		((PlanedTestsListFrame)getOwner()).setTests(tests);
	}
}
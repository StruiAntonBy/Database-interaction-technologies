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

import domain.Requirement;
import helper.DigitFilter;
import ioc.IocContainer;
import requirement_controller.DeleteRequirementButtonClick;
import requirement_controller.SaveRequirementButtonClick;

public class RequirementEditFrame extends JDialog{
	private Requirement requirement;
	private JTextField projectidTextField,requirementTextField,startdateTextField,plannedtimeTextField;
	private JRadioButton leftButtonThePriorityOf,centerButtonThePriorityOf,rightButtonThePriorityOf;
	private JRadioButton leftButtonLevelOfCriticalityForTheClient,centerButtonLevelOfCriticalityForTheClient,rightButtonLevelOfCriticalityForTheClient;
	private JRadioButton ButtonMarkOfCompletion1,ButtonMarkOfCompletion2,ButtonMarkOfCompletion3,ButtonMarkOfCompletion4,ButtonMarkOfCompletion5;
	private JRadioButton leftButtonTheProbabilityOfChange,centerButtonTheProbabilityOfChange,rightButtonTheProbabilityOfChange;
	
	private RequirementEditFrame(JFrame owner, Requirement requirement, String title, IocContainer container) {
		super(owner, title);
		this.requirement = requirement;
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1400, 800);
		setResizable(false);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		JLabel projectidLabel = new JLabel("Project id:");
		projectidLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(projectidLabel, constraints);
		add(projectidLabel);

		if(requirement.getProjectId()==null) {
			projectidTextField = new JTextField();
		}
		else {
			projectidTextField = new JTextField(String.valueOf(requirement.getProjectId()));
		}
		PlainDocument doc = (PlainDocument) projectidTextField.getDocument();
		doc.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(projectidTextField, constraints);
		add(projectidTextField);

		JLabel requirementLabel = new JLabel("Requirement:");
		requirementLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(requirementLabel, constraints);
		add(requirementLabel);

		requirementTextField = new JTextField(requirement.getRequirement());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(requirementTextField, constraints);
		add(requirementTextField);
		
		JLabel startdateLabel = new JLabel("Start date:");
		startdateLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(startdateLabel, constraints);
		add(startdateLabel);
		
		startdateTextField = new JTextField(requirement.getStartDate());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(startdateTextField, constraints);
		add(startdateTextField);
		
		JLabel plannedtimeLabel = new JLabel("Planned time:");
		plannedtimeLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(plannedtimeLabel, constraints);
		add(plannedtimeLabel);
		
		plannedtimeTextField = new JTextField(String.valueOf(requirement.getPlannedTime()));
		PlainDocument doc1 = (PlainDocument) plannedtimeTextField.getDocument();
		doc1.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(plannedtimeTextField, constraints);
		add(plannedtimeTextField);
		
		JLabel thepriorityofLabel = new JLabel("The priority of:");
		thepriorityofLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(thepriorityofLabel, constraints);
		add(thepriorityofLabel);
		
		ButtonGroup group = new ButtonGroup();
		if(requirement.getThePriorityOf()==null) {
			requirement.setThePriorityOf("");
		}
		switch(requirement.getThePriorityOf()) {
			case "высокий":
				leftButtonThePriorityOf = new JRadioButton("high", true);
				centerButtonThePriorityOf = new JRadioButton("middle", false);
				rightButtonThePriorityOf = new JRadioButton("low", false);
				break;
			case "средний":
				leftButtonThePriorityOf = new JRadioButton("high", false);
				centerButtonThePriorityOf = new JRadioButton("middle", true);
				rightButtonThePriorityOf = new JRadioButton("low", false);
				break;
			case "низкий":
				leftButtonThePriorityOf = new JRadioButton("high", false);
				centerButtonThePriorityOf = new JRadioButton("middle", false);
				rightButtonThePriorityOf = new JRadioButton("low", true);
				break;
			default:
				leftButtonThePriorityOf = new JRadioButton("high", false);
				centerButtonThePriorityOf = new JRadioButton("middle", false);
				rightButtonThePriorityOf = new JRadioButton("low", false);
				break;
		}
		group.add(leftButtonThePriorityOf);
		group.add(centerButtonThePriorityOf);
		group.add(rightButtonThePriorityOf);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(leftButtonThePriorityOf, constraints);
		add(leftButtonThePriorityOf);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(centerButtonThePriorityOf, constraints);
		add(centerButtonThePriorityOf);
		constraints.gridwidth = 1;
		constraints.gridx = 3;
		constraints.gridy = 4;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(rightButtonThePriorityOf, constraints);
		add(rightButtonThePriorityOf);
		
		JLabel levelofcriticalityfortheclientLabel = new JLabel("Level of criticality for the client:");
		levelofcriticalityfortheclientLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(levelofcriticalityfortheclientLabel, constraints);
		add(levelofcriticalityfortheclientLabel);
		
		ButtonGroup group1 = new ButtonGroup();
		if(requirement.getLevelOfCriticalityForTheClient()==null) {
			requirement.setLevelOfCriticalityForTheClient("");
		}
		switch(requirement.getLevelOfCriticalityForTheClient()) {
			case "высокий":
				leftButtonLevelOfCriticalityForTheClient = new JRadioButton("high", true);
				centerButtonLevelOfCriticalityForTheClient = new JRadioButton("middle", false);
				rightButtonLevelOfCriticalityForTheClient = new JRadioButton("low", false);
				break;
			case "средний":
				leftButtonLevelOfCriticalityForTheClient = new JRadioButton("high", false);
				centerButtonLevelOfCriticalityForTheClient = new JRadioButton("middle", true);
				rightButtonLevelOfCriticalityForTheClient = new JRadioButton("low", false);
				break;
			case "низкий":
				leftButtonLevelOfCriticalityForTheClient = new JRadioButton("high", false);
				centerButtonLevelOfCriticalityForTheClient = new JRadioButton("middle", false);
				rightButtonLevelOfCriticalityForTheClient = new JRadioButton("low", true);
				break;
			default:
				leftButtonLevelOfCriticalityForTheClient = new JRadioButton("high", false);
				centerButtonLevelOfCriticalityForTheClient = new JRadioButton("middle", false);
				rightButtonLevelOfCriticalityForTheClient = new JRadioButton("low", false);
				break;
		}
		group1.add(leftButtonLevelOfCriticalityForTheClient);
		group1.add(centerButtonLevelOfCriticalityForTheClient);
		group1.add(rightButtonLevelOfCriticalityForTheClient);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(leftButtonLevelOfCriticalityForTheClient, constraints);
		add(leftButtonLevelOfCriticalityForTheClient);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(centerButtonLevelOfCriticalityForTheClient, constraints);
		add(centerButtonLevelOfCriticalityForTheClient);
		constraints.gridwidth = 1;
		constraints.gridx = 3;
		constraints.gridy = 5;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(rightButtonLevelOfCriticalityForTheClient, constraints);
		add(rightButtonLevelOfCriticalityForTheClient);
		
		JLabel markofcompletionLabel = new JLabel("A mark of completion:");
		markofcompletionLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(markofcompletionLabel, constraints);
		add(markofcompletionLabel);
		
		ButtonGroup group2 = new ButtonGroup();
		if(requirement.getMarkOfCompletion()==null) {
			requirement.setMarkOfCompletion("");
		}
		switch(requirement.getMarkOfCompletion()) {
			case "":
				ButtonMarkOfCompletion1 = new JRadioButton("empty", true);
				ButtonMarkOfCompletion2 = new JRadioButton("implementation completed", false);
				ButtonMarkOfCompletion3 = new JRadioButton("tested and closed as successful", false);
				ButtonMarkOfCompletion4 = new JRadioButton("tested and closed as not successful", false);
				ButtonMarkOfCompletion5 = new JRadioButton("tested and returned for revision", false);
				break;
			case "реализация завершена":
				ButtonMarkOfCompletion1 = new JRadioButton("empty", false);
				ButtonMarkOfCompletion2 = new JRadioButton("implementation completed", true);
				ButtonMarkOfCompletion3 = new JRadioButton("tested and closed as successful", false);
				ButtonMarkOfCompletion4 = new JRadioButton("tested and closed as not successful", false);
				ButtonMarkOfCompletion5 = new JRadioButton("tested and returned for revision", false);
				break;
			case "протестирован и закрыт как успешный":
				ButtonMarkOfCompletion1 = new JRadioButton("empty", false);
				ButtonMarkOfCompletion2 = new JRadioButton("implementation completed", false);
				ButtonMarkOfCompletion3 = new JRadioButton("tested and closed as successful", true);
				ButtonMarkOfCompletion4 = new JRadioButton("tested and closed as not successful", false);
				ButtonMarkOfCompletion5 = new JRadioButton("tested and returned for revision", false);
				break;
			case "протестирован и закрыт как не успешный":
				ButtonMarkOfCompletion1 = new JRadioButton("empty", false);
				ButtonMarkOfCompletion2 = new JRadioButton("implementation completed", false);
				ButtonMarkOfCompletion3 = new JRadioButton("tested and closed as successful", false);
				ButtonMarkOfCompletion4 = new JRadioButton("tested and closed as not successful", true);
				ButtonMarkOfCompletion5 = new JRadioButton("tested and returned for revision", false);
				break;
			case "протестирован и возвращен на доработку":
				ButtonMarkOfCompletion1 = new JRadioButton("empty", false);
				ButtonMarkOfCompletion2 = new JRadioButton("implementation completed", false);
				ButtonMarkOfCompletion3 = new JRadioButton("tested and closed as successful", false);
				ButtonMarkOfCompletion4 = new JRadioButton("tested and closed as not successful", false);
				ButtonMarkOfCompletion5 = new JRadioButton("tested and returned for revision", true);
				break;
			default:
				ButtonMarkOfCompletion1 = new JRadioButton("empty", false);
				ButtonMarkOfCompletion2 = new JRadioButton("implementation completed", false);
				ButtonMarkOfCompletion3 = new JRadioButton("tested and closed as successful", false);
				ButtonMarkOfCompletion4 = new JRadioButton("tested and closed as not successful", false);
				ButtonMarkOfCompletion5 = new JRadioButton("tested and returned for revision", false);
				break;
		}
		group2.add(ButtonMarkOfCompletion1);
		group2.add(ButtonMarkOfCompletion2);
		group2.add(ButtonMarkOfCompletion3);
		group2.add(ButtonMarkOfCompletion4);
		group2.add(ButtonMarkOfCompletion5);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(ButtonMarkOfCompletion1, constraints);
		add(ButtonMarkOfCompletion1);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(ButtonMarkOfCompletion2, constraints);
		add(ButtonMarkOfCompletion2);
		constraints.gridwidth = 1;
		constraints.gridx = 3;
		constraints.gridy = 6;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(ButtonMarkOfCompletion3, constraints);
		add(ButtonMarkOfCompletion3);
		constraints.gridwidth = 1;
		constraints.gridx = 4;
		constraints.gridy = 6;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(ButtonMarkOfCompletion4, constraints);
		add(ButtonMarkOfCompletion4);
		constraints.gridwidth = 1;
		constraints.gridx = 5;
		constraints.gridy = 6;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(ButtonMarkOfCompletion5, constraints);
		add(ButtonMarkOfCompletion5);
		
		JLabel theprobabilityofachangeLabel = new JLabel("The probability of a change:");
		theprobabilityofachangeLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(theprobabilityofachangeLabel, constraints);
		add(theprobabilityofachangeLabel);
		
		ButtonGroup group3 = new ButtonGroup();
		if(requirement.getTheProbabilityOfChange()==null) {
			requirement.setTheProbabilityOfChange("");
		}
		switch(requirement.getTheProbabilityOfChange()) {
			case "высокий":
				leftButtonTheProbabilityOfChange = new JRadioButton("high", true);
				centerButtonTheProbabilityOfChange = new JRadioButton("middle", false);
				rightButtonTheProbabilityOfChange = new JRadioButton("low", false);
				break;
			case "средний":
				leftButtonTheProbabilityOfChange = new JRadioButton("high", false);
				centerButtonTheProbabilityOfChange = new JRadioButton("middle", true);
				rightButtonTheProbabilityOfChange = new JRadioButton("low", false);
				break;
			case "низкий":
				leftButtonTheProbabilityOfChange = new JRadioButton("high", false);
				centerButtonTheProbabilityOfChange = new JRadioButton("middle", false);
				rightButtonTheProbabilityOfChange = new JRadioButton("low", true);
				break;
			default:
				leftButtonTheProbabilityOfChange = new JRadioButton("high", false);
				centerButtonTheProbabilityOfChange = new JRadioButton("middle", false);
				rightButtonTheProbabilityOfChange = new JRadioButton("low", false);
				break;
		}
		group3.add(leftButtonTheProbabilityOfChange);
		group3.add(centerButtonTheProbabilityOfChange);
		group3.add(rightButtonTheProbabilityOfChange);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(leftButtonTheProbabilityOfChange, constraints);
		add(leftButtonTheProbabilityOfChange);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 7;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(centerButtonTheProbabilityOfChange, constraints);
		add(centerButtonTheProbabilityOfChange);
		constraints.gridwidth = 1;
		constraints.gridx = 3;
		constraints.gridy = 7;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(rightButtonTheProbabilityOfChange, constraints);
		add(rightButtonTheProbabilityOfChange);

		JButton saveButton = new JButton("Save");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveRequirementButtonClick(this, container));
		add(saveButton);

		if(requirement.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 8;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteRequirementButtonClick(this, container));
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

	public RequirementEditFrame(JFrame owner, Requirement requirement, IocContainer container) {
		this(owner, requirement, String.format("Editing the requirement %s %s %s %s %s %s %s %s", requirement.getProjectId(), requirement.getRequirement(), requirement.getStartDate(), requirement.getPlannedTime(), requirement.getThePriorityOf(), requirement.getLevelOfCriticalityForTheClient(), requirement.getMarkOfCompletion(), requirement.getTheProbabilityOfChange()), container);
	}

	public RequirementEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Requirement(), "Adding an requirement", container);
	}

	public Requirement getRequirement() {
		String projectid = projectidTextField.getText();
		if(projectid.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Project id» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		requirement.setProjectId(Long.parseLong(projectid));
		String strrequirement = new String(requirementTextField.getText());
		if(strrequirement.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Requirement» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		requirement.setRequirement(strrequirement);
		String startdate = new String(startdateTextField.getText());
		requirement.setStartDate(startdate.trim());
		String plannedtime = new String(plannedtimeTextField.getText());
		if(plannedtime.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Planned time» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		requirement.setPlannedTime(Integer.parseInt(plannedtime));
		if(leftButtonThePriorityOf.isSelected()) {
			requirement.setThePriorityOf("высокий");
		}
		else if(centerButtonThePriorityOf.isSelected()) {
			requirement.setThePriorityOf("средний");
		}
		else if(rightButtonThePriorityOf.isSelected()) {
			requirement.setThePriorityOf("низкий");
		}
		else {
			JOptionPane.showMessageDialog(getOwner(), "The «The priority of» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(leftButtonLevelOfCriticalityForTheClient.isSelected()) {
			requirement.setLevelOfCriticalityForTheClient("высокий");
		}
		else if(centerButtonLevelOfCriticalityForTheClient.isSelected()) {
			requirement.setLevelOfCriticalityForTheClient("средний");
		}
		else if(rightButtonLevelOfCriticalityForTheClient.isSelected()) {
			requirement.setLevelOfCriticalityForTheClient("низкий");
		}
		else {
			JOptionPane.showMessageDialog(getOwner(), "The «Level of criticality for the client» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(ButtonMarkOfCompletion1.isSelected()) {
			requirement.setMarkOfCompletion(null);
		}
		else if(ButtonMarkOfCompletion2.isSelected()) {
			requirement.setMarkOfCompletion("реализация завершена");
		}
		else if(ButtonMarkOfCompletion3.isSelected()) {
			requirement.setMarkOfCompletion("протестирован и закрыт как успешный");
		}
		else if(ButtonMarkOfCompletion4.isSelected()) {
			requirement.setMarkOfCompletion("протестирован и закрыт как не успешный");
		}
		else if(ButtonMarkOfCompletion5.isSelected()) {
			requirement.setMarkOfCompletion("протестирован и возвращен на доработку");
		}
		else {
			JOptionPane.showMessageDialog(getOwner(), "The «A mark of completion» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(leftButtonTheProbabilityOfChange.isSelected()) {
			requirement.setTheProbabilityOfChange("высокий");
		}
		else if(centerButtonTheProbabilityOfChange.isSelected()) {
			requirement.setTheProbabilityOfChange("средний");
		}
		else if(rightButtonTheProbabilityOfChange.isSelected()) {
			requirement.setTheProbabilityOfChange("низкий");
		}
		else {
			JOptionPane.showMessageDialog(getOwner(), "The «The probability of a change» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return requirement;
	}

	public Long getRequirementId() {
		return requirement != null ? requirement.getId() : null;
	}

	public void update(List<Requirement> requirements) {
		((RequirementsListFrame)getOwner()).setRequirements(requirements);
	}
}
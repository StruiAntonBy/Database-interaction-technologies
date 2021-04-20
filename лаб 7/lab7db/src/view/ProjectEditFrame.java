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

import domain.Project;
import helper.DigitFilter;
import ioc.IocContainer;
import project_controller.DeleteProjectButtonClick;
import project_controller.SaveProjectButtonClick;

public class ProjectEditFrame extends JDialog{
	private Project project;
	private JTextField clientidTextField,startdateTextField,enddateTextField;

	private ProjectEditFrame(JFrame owner, Project project, String title, IocContainer container) {
		super(owner, title);
		this.project = project;
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

		JLabel clientidLabel = new JLabel("Client id:");
		clientidLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(clientidLabel, constraints);
		add(clientidLabel);

		if(project.getClientId()==null) {
			clientidTextField = new JTextField();
		}
		else {
			clientidTextField = new JTextField(String.valueOf(project.getClientId()));
		}
		PlainDocument doc = (PlainDocument) clientidTextField.getDocument();
		doc.setDocumentFilter(new DigitFilter());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(clientidTextField, constraints);
		add(clientidTextField);

		JLabel startdateLabel = new JLabel("Start date:");
		startdateLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(startdateLabel, constraints);
		add(startdateLabel);

		startdateTextField = new JTextField(project.getStartDate());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(startdateTextField, constraints);
		add(startdateTextField);
		
		JLabel enddateLabel = new JLabel("End date:");
		enddateLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(enddateLabel, constraints);
		add(enddateLabel);
		
		enddateTextField = new JTextField(project.getEndDate());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(enddateTextField, constraints);
		add(enddateTextField);

		JButton saveButton = new JButton("Save");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveProjectButtonClick(this, container));
		add(saveButton);

		if(project.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 3;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteProjectButtonClick(this, container));
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

	public ProjectEditFrame(JFrame owner, Project project, IocContainer container) {
		this(owner, project, String.format("Editing the project %s %s %s", project.getClientId(),project.getStartDate(),project.getEndDate()), container);
	}

	public ProjectEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Project(), "Adding an project", container);
	}

	public Project getProject() {
		String clientid = clientidTextField.getText();
		if(clientid.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Client id» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		project.setClientId(Long.parseLong(clientid));
		String startdate = new String(startdateTextField.getText());
		if(startdate.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Start date» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		project.setStartDate(startdate);
		String enddate=new String(enddateTextField.getText());
		project.setEndDate(enddate.trim());
		return project;
	}

	public Long getProjectId() {
		return project != null ? project.getId() : null;
	}

	public void update(List<Project> projects) {
		((ProjectsListFrame)getOwner()).setProjects(projects);
	}
}
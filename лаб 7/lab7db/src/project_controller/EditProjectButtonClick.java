package project_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Project;
import ioc.IocContainer;
import view.ProjectEditFrame;
import view.ProjectsListFrame;

public class EditProjectButtonClick implements ActionListener {
	private ProjectsListFrame projectsListFrame;
	private IocContainer container;

	public EditProjectButtonClick(ProjectsListFrame projectsListFrame, IocContainer container) {
		this.projectsListFrame = projectsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Project project = projectsListFrame.getSelectedProject();
		if(project != null) {
			new ProjectEditFrame(projectsListFrame, project, container);
		} else {
			JOptionPane.showMessageDialog(projectsListFrame, "No project is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
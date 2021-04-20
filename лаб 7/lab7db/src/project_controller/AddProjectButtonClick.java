package project_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.ProjectEditFrame;
import view.ProjectsListFrame;

public class AddProjectButtonClick implements ActionListener {
	private ProjectsListFrame projectsListFrame;
	private IocContainer container;

	public AddProjectButtonClick(ProjectsListFrame projectsListFrame, IocContainer container) {
		this.projectsListFrame = projectsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ProjectEditFrame(projectsListFrame, container);
	}
}
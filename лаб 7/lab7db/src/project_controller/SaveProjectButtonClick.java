package project_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Project;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ProjectService;
import service.ServiceException;
import view.ProjectEditFrame;

public class SaveProjectButtonClick implements ActionListener {
	private ProjectEditFrame projectEditFrame;
	private IocContainer container;

	public SaveProjectButtonClick(ProjectEditFrame projectEditFrame, IocContainer container) {
		this.projectEditFrame = projectEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Project project = projectEditFrame.getProject();
		if(project != null) {
			try {
				ProjectService service = container.getProjectService();
				service.save(project);
				JOptionPane.showMessageDialog(projectEditFrame, "Data was saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				projectEditFrame.update(service.getAll());
				projectEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(projectEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
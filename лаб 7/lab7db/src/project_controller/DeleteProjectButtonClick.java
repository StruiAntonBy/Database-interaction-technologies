package project_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.ProjectService;
import service.ServiceException;
import view.ProjectEditFrame;

public class DeleteProjectButtonClick implements ActionListener {
	private ProjectEditFrame projectEditFrame;
	private IocContainer container;

	public DeleteProjectButtonClick(ProjectEditFrame projectEditFrame, IocContainer container) {
		this.projectEditFrame = projectEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = projectEditFrame.getProjectId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(projectEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					ProjectService service = container.getProjectService();
					service.delete(id);
					JOptionPane.showMessageDialog(projectEditFrame, "Data was successfully deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
					projectEditFrame.update(service.getAll());
					projectEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(projectEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
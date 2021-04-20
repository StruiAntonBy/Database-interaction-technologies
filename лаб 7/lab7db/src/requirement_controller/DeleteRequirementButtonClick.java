package requirement_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.RequirementService;
import service.ServiceException;
import view.RequirementEditFrame;

public class DeleteRequirementButtonClick implements ActionListener {
	private RequirementEditFrame requirementEditFrame;
	private IocContainer container;

	public DeleteRequirementButtonClick(RequirementEditFrame requirementEditFrame, IocContainer container) {
		this.requirementEditFrame = requirementEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = requirementEditFrame.getRequirementId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(requirementEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					RequirementService service = container.getRequirementService();
					service.delete(id);
					JOptionPane.showMessageDialog(requirementEditFrame, "Data was successfully deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
					requirementEditFrame.update(service.getAll());
					requirementEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(requirementEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
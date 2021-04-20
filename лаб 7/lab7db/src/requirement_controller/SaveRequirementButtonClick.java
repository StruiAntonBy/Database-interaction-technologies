package requirement_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Requirement;
import ioc.ContainerException;
import ioc.IocContainer;
import service.RequirementService;
import service.ServiceException;
import view.RequirementEditFrame;

public class SaveRequirementButtonClick implements ActionListener {
	private RequirementEditFrame requirementEditFrame;
	private IocContainer container;

	public SaveRequirementButtonClick(RequirementEditFrame requirementEditFrame, IocContainer container) {
		this.requirementEditFrame = requirementEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Requirement requirement = requirementEditFrame.getRequirement();
		if(requirement != null) {
			try {
				RequirementService service = container.getRequirementService();
				service.save(requirement);
				JOptionPane.showMessageDialog(requirementEditFrame, "Data was saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				requirementEditFrame.update(service.getAll());
				requirementEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(requirementEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
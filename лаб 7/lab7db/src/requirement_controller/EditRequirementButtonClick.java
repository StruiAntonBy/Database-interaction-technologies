package requirement_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Requirement;
import ioc.IocContainer;
import view.RequirementEditFrame;
import view.RequirementsListFrame;

public class EditRequirementButtonClick implements ActionListener {
	private RequirementsListFrame requirementsListFrame;
	private IocContainer container;

	public EditRequirementButtonClick(RequirementsListFrame requirementsListFrame, IocContainer container) {
		this.requirementsListFrame = requirementsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Requirement requirement = requirementsListFrame.getSelectedRequirement();
		if(requirement != null) {
			new RequirementEditFrame(requirementsListFrame, requirement, container);
		} else {
			JOptionPane.showMessageDialog(requirementsListFrame, "No requirement is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}

package requirement_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.RequirementEditFrame;
import view.RequirementsListFrame;

public class AddRequirementButtonClick implements ActionListener {
	private RequirementsListFrame requirementsListFrame;
	private IocContainer container;

	public AddRequirementButtonClick(RequirementsListFrame requirementsListFrame, IocContainer container) {
		this.requirementsListFrame = requirementsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new RequirementEditFrame(requirementsListFrame, container);
	}
}

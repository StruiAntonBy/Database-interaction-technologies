package software_tester_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.SoftwareTesterEditFrame;
import view.SoftwareTestersListFrame;

public class AddSoftwareTesterButtonClick implements ActionListener {
	private SoftwareTestersListFrame testersListFrame;
	private IocContainer container;

	public AddSoftwareTesterButtonClick(SoftwareTestersListFrame testersListFrame, IocContainer container) {
		this.testersListFrame = testersListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new SoftwareTesterEditFrame(testersListFrame, container);
	}
}

package software_tester_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.SoftwareTester;
import ioc.IocContainer;
import view.SoftwareTesterEditFrame;
import view.SoftwareTestersListFrame;

public class EditSoftwareTesterButtonClick implements ActionListener {
	private SoftwareTestersListFrame testersListFrame;
	private IocContainer container;

	public EditSoftwareTesterButtonClick(SoftwareTestersListFrame testersListFrame, IocContainer container) {
		this.testersListFrame = testersListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SoftwareTester tester = testersListFrame.getSelectedTester();
		if(tester != null) {
			new SoftwareTesterEditFrame(testersListFrame, tester, container);
		} else {
			JOptionPane.showMessageDialog(testersListFrame, "No tester is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}

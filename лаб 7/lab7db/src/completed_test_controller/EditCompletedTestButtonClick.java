package completed_test_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.CompletedTest;
import ioc.IocContainer;
import view.CompletedTestEditFrame;
import view.CompletedTestsListFrame;

public class EditCompletedTestButtonClick implements ActionListener {
	private CompletedTestsListFrame testsListFrame;
	private IocContainer container;

	public EditCompletedTestButtonClick(CompletedTestsListFrame testsListFrame, IocContainer container) {
		this.testsListFrame = testsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CompletedTest test = testsListFrame.getSelectedTest();
		if(test != null) {
			new CompletedTestEditFrame(testsListFrame, test, container);
		} else {
			JOptionPane.showMessageDialog(testsListFrame, "No completed test is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
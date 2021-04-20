package completed_test_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.CompletedTestEditFrame;
import view.CompletedTestsListFrame;

public class AddCompletedTestButtonClick implements ActionListener {
	private CompletedTestsListFrame testsListFrame;
	private IocContainer container;

	public AddCompletedTestButtonClick(CompletedTestsListFrame testsListFrame, IocContainer container) {
		this.testsListFrame = testsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new CompletedTestEditFrame(testsListFrame, container);
	}
}
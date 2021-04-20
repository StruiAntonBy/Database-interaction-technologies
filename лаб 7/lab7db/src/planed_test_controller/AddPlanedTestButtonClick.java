package planed_test_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.PlanedTestEditFrame;
import view.PlanedTestsListFrame;

public class AddPlanedTestButtonClick implements ActionListener {
	private PlanedTestsListFrame testsListFrame;
	private IocContainer container;

	public AddPlanedTestButtonClick(PlanedTestsListFrame testsListFrame, IocContainer container) {
		this.testsListFrame = testsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new PlanedTestEditFrame(testsListFrame, container);
	}
}
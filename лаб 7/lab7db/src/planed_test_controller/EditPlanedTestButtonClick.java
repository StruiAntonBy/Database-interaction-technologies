package planed_test_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.PlanedTest;
import ioc.IocContainer;
import view.PlanedTestEditFrame;
import view.PlanedTestsListFrame;

public class EditPlanedTestButtonClick implements ActionListener {
	private PlanedTestsListFrame testsListFrame;
	private IocContainer container;

	public EditPlanedTestButtonClick(PlanedTestsListFrame testsListFrame, IocContainer container) {
		this.testsListFrame = testsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PlanedTest test = testsListFrame.getSelectedTest();
		if(test != null) {
			new PlanedTestEditFrame(testsListFrame, test, container);
		} else {
			JOptionPane.showMessageDialog(testsListFrame, "No planed test is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
package planed_test_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.PlanedTest;
import ioc.ContainerException;
import ioc.IocContainer;
import service.PlanedTestService;
import service.ServiceException;
import view.PlanedTestEditFrame;

public class SavePlanedTestButtonClick implements ActionListener {
	private PlanedTestEditFrame testEditFrame;
	private IocContainer container;

	public SavePlanedTestButtonClick(PlanedTestEditFrame testEditFrame, IocContainer container) {
		this.testEditFrame = testEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		PlanedTest test = testEditFrame.getTest();
		if(test != null) {
			try {
				PlanedTestService service = container.getPlanedTestService();
				service.save(test);
				JOptionPane.showMessageDialog(testEditFrame, "Data was saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				testEditFrame.update(service.getAll());
				testEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(testEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
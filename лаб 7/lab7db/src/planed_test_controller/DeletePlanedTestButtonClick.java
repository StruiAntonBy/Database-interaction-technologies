package planed_test_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.PlanedTestService;
import service.ServiceException;
import view.PlanedTestEditFrame;

public class DeletePlanedTestButtonClick implements ActionListener {
	private PlanedTestEditFrame testEditFrame;
	private IocContainer container;

	public DeletePlanedTestButtonClick(PlanedTestEditFrame testEditFrame, IocContainer container) {
		this.testEditFrame = testEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = testEditFrame.getTestId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(testEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					PlanedTestService service = container.getPlanedTestService();
					service.delete(id);
					JOptionPane.showMessageDialog(testEditFrame, "Data was successfully deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
					testEditFrame.update(service.getAll());
					testEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(testEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
package completed_test_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.CompletedTestService;
import service.ServiceException;
import view.CompletedTestEditFrame;

public class DeleteCompletedTestButtonClick implements ActionListener {
	private CompletedTestEditFrame testEditFrame;
	private IocContainer container;

	public DeleteCompletedTestButtonClick(CompletedTestEditFrame testEditFrame, IocContainer container) {
		this.testEditFrame = testEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = testEditFrame.getTestId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(testEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					CompletedTestService service = container.getCompletedTestService();
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
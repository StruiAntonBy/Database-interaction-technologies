package completed_test_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.CompletedTest;
import ioc.ContainerException;
import ioc.IocContainer;
import service.CompletedTestService;
import service.ServiceException;
import view.CompletedTestEditFrame;

public class SaveCompletedTestButtonClick implements ActionListener {
	private CompletedTestEditFrame testEditFrame;
	private IocContainer container;

	public SaveCompletedTestButtonClick(CompletedTestEditFrame testEditFrame, IocContainer container) {
		this.testEditFrame = testEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		CompletedTest test = testEditFrame.getTest();
		if(test != null) {
			try {
				CompletedTestService service = container.getCompletedTestService();
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
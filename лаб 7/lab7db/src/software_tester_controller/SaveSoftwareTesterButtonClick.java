package software_tester_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.SoftwareTester;
import ioc.ContainerException;
import ioc.IocContainer;
import service.SoftwareTesterService;
import service.ServiceException;
import view.SoftwareTesterEditFrame;

public class SaveSoftwareTesterButtonClick implements ActionListener {
	private SoftwareTesterEditFrame testerEditFrame;
	private IocContainer container;

	public SaveSoftwareTesterButtonClick(SoftwareTesterEditFrame testerEditFrame, IocContainer container) {
		this.testerEditFrame = testerEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		SoftwareTester tester = testerEditFrame.getTester();
		if(tester != null) {
			try {
				SoftwareTesterService service = container.getSoftwareTesterService();
				service.save(tester);
				JOptionPane.showMessageDialog(testerEditFrame, "Data was saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				testerEditFrame.update(service.getAll());
				testerEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(testerEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

package software_tester_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import service.SoftwareTesterService;
import view.SoftwareTesterEditFrame;

public class DeleteSoftwareTesterButtonClick implements ActionListener {
	private SoftwareTesterEditFrame testerEditFrame;
	private IocContainer container;

	public DeleteSoftwareTesterButtonClick(SoftwareTesterEditFrame testerEditFrame, IocContainer container) {
		this.testerEditFrame = testerEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = testerEditFrame.getTesterId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(testerEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					SoftwareTesterService service = container.getSoftwareTesterService();
					service.delete(id);
					JOptionPane.showMessageDialog(testerEditFrame, "Data was successfully deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
					testerEditFrame.update(service.getAll());
					testerEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(testerEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}

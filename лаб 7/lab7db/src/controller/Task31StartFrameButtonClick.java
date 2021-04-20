package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Lab4Task31;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ContactPersonService;
import service.ServiceException;
import view.Lab4Task31ListFrame;

public class Task31StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	
	public Task31StartFrameButtonClick(IocContainer container) {
		this.container=container;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ContactPersonService service = container.getContactPersonService();
			List<Lab4Task31> list = service.getContactPersonNumbers();
			Lab4Task31ListFrame lab4task31ListFrame = new Lab4Task31ListFrame(container);
			lab4task31ListFrame.setPersons(list);
		} catch (ContainerException | ServiceException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

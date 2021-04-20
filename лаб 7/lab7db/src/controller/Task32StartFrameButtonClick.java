package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Lab4Task32;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ContactPersonService;
import service.ServiceException;
import view.Lab4Task32ListFrame;

public class Task32StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	
	public Task32StartFrameButtonClick(IocContainer container) {
		this.container=container;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ContactPersonService service = container.getContactPersonService();
			List<Lab4Task32> list = service.getContactPersonLogins();
			Lab4Task32ListFrame lab4task32ListFrame = new Lab4Task32ListFrame(container);
			lab4task32ListFrame.setPersons(list);
		} catch (ContainerException | ServiceException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

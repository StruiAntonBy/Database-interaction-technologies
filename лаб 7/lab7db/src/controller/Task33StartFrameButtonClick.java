package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Lab4Task33;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ContactPersonService;
import service.ServiceException;
import view.Lab4Task33ListFrame;

public class Task33StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	
	public Task33StartFrameButtonClick(IocContainer container) {
		this.container=container;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ContactPersonService service = container.getContactPersonService();
			List<Lab4Task33> list = service.getContactPersonWorkExperiences();
			Lab4Task33ListFrame lab4task33ListFrame = new Lab4Task33ListFrame(container);
			lab4task33ListFrame.setPersons(list);
		} catch (ContainerException | ServiceException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
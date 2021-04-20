package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Lab4Task42;
import ioc.ContainerException;
import ioc.IocContainer;
import service.CompletedTestService;
import service.ServiceException;
import view.Lab4Task42ListFrame;

public class Task42StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	
	public Task42StartFrameButtonClick(IocContainer container) {
		this.container=container;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			CompletedTestService service = container.getCompletedTestService();
			List<Lab4Task42> list = service.getSumLengthTesterId();
			Lab4Task42ListFrame lab4task42ListFrame = new Lab4Task42ListFrame(container);
			lab4task42ListFrame.setTests(list);
		} catch (ContainerException | ServiceException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
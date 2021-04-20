package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Lab4Task41;
import ioc.ContainerException;
import ioc.IocContainer;
import service.CompletedTestService;
import service.ServiceException;
import view.Lab4Task41ListFrame;

public class Task41StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	
	public Task41StartFrameButtonClick(IocContainer container) {
		this.container=container;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			CompletedTestService service = container.getCompletedTestService();
			List<Lab4Task41> list = service.getPlanedTestIdCount();
			Lab4Task41ListFrame lab4task41ListFrame = new Lab4Task41ListFrame(container);
			lab4task41ListFrame.setTests(list);
		} catch (ContainerException | ServiceException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
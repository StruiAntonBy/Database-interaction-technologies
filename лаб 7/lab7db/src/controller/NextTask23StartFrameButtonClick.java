package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Requirement;
import ioc.ContainerException;
import ioc.IocContainer;
import service.RequirementService;
import service.ServiceException;
import view.Lab4Task23ListFrame;
import view.StartFrame;

public class NextTask23StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	private StartFrame startFrame;
	
	public NextTask23StartFrameButtonClick(StartFrame startFrame,IocContainer container) {
		this.container=container;
		this.startFrame = startFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(startFrame.getPrimarykeyTask23()!=null) {
				RequirementService service = container.getRequirementService();
				List<Requirement> list = service.getSuccessfullyImplementedRequirements(startFrame.getPrimarykeyTask23());
				Lab4Task23ListFrame lab4task23ListFrame = new Lab4Task23ListFrame(container);
				lab4task23ListFrame.setRequirements(list);
			}
			else {
				JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		} catch (ContainerException | ServiceException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
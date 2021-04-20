package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Lab4Task22;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ProjectService;
import service.ServiceException;
import view.Lab4Task22ListFrame;
import view.StartFrame;

public class NextTask22StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	private StartFrame startFrame;
	
	public NextTask22StartFrameButtonClick(StartFrame startFrame,IocContainer container) {
		this.container=container;
		this.startFrame = startFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(startFrame.getPrimarykeyTask22()!=null) {
				ProjectService service = container.getProjectService();
				List<Lab4Task22> list = service.getUnfinishedProjectsClient(startFrame.getPrimarykeyTask22());
				Lab4Task22ListFrame lab4task22ListFrame = new Lab4Task22ListFrame(container);
				lab4task22ListFrame.setList(list);
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

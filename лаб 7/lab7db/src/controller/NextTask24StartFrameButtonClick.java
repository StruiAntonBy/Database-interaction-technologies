package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.PlanedTest;
import ioc.ContainerException;
import ioc.IocContainer;
import service.PlanedTestService;
import service.ServiceException;
import view.PlanedTestsListFrame;
import view.StartFrame;

public class NextTask24StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	private StartFrame startFrame;
	
	public NextTask24StartFrameButtonClick(StartFrame startFrame,IocContainer container) {
		this.container=container;
		this.startFrame = startFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(startFrame.getPrimarykeyTask24()!=null) {
				PlanedTestService service = container.getPlanedTestService();
				List<PlanedTest> list = service.getScheduledDeepTests(startFrame.getPrimarykeyTask24());
				PlanedTestsListFrame testsListFrame = new PlanedTestsListFrame(container,1);
				testsListFrame.setTests(list);
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

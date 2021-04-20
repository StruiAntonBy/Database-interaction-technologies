package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.CompletedTest;
import ioc.ContainerException;
import ioc.IocContainer;
import service.CompletedTestService;
import service.ServiceException;
import view.CompletedTestsListFrame;
import view.StartFrame;

public class NextTask25StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	private StartFrame startFrame;
	
	public NextTask25StartFrameButtonClick(StartFrame startFrame,IocContainer container) {
		this.container=container;
		this.startFrame = startFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(startFrame.getPrimarykeyTask25()!=null) {
				CompletedTestService service = container.getCompletedTestService();
				List<CompletedTest> list = service.getAllCompletedTestsSpecifiedPlanedTest(startFrame.getPrimarykeyTask25());
				CompletedTestsListFrame testsListFrame = new CompletedTestsListFrame(container,1);
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
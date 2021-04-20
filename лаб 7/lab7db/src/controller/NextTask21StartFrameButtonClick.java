package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Email;
import ioc.ContainerException;
import ioc.IocContainer;
import service.EmailService;
import service.ServiceException;
import view.EmailsListFrame;
import view.StartFrame;

public class NextTask21StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	private StartFrame startFrame;
	
	public NextTask21StartFrameButtonClick(StartFrame startFrame,IocContainer container) {
		this.container=container;
		this.startFrame = startFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(startFrame.getPrimarykeyTask21()!=null) {
				EmailService service = container.getEmailService();
				List<Email> emails = service.getAllEmailAddressesClient(startFrame.getPrimarykeyTask21());
				EmailsListFrame emailsListFrame = new EmailsListFrame(container,1);
				emailsListFrame.setEmails(emails);
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

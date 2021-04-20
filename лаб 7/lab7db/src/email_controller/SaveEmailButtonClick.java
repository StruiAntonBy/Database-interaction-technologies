package email_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Email;
import ioc.ContainerException;
import ioc.IocContainer;
import service.EmailService;
import service.ServiceException;
import view.EmailEditFrame;

public class SaveEmailButtonClick implements ActionListener {
	private EmailEditFrame emailEditFrame;
	private IocContainer container;

	public SaveEmailButtonClick(EmailEditFrame emailEditFrame, IocContainer container) {
		this.emailEditFrame = emailEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Email email = emailEditFrame.getEmail();
		if(email != null) {
			try {
				EmailService service = container.getEmailService();
				service.save(email);
				JOptionPane.showMessageDialog(emailEditFrame, "Data was saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				emailEditFrame.update(service.getAll());
				emailEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(emailEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
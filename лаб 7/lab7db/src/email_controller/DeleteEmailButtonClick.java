package email_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.EmailService;
import service.ServiceException;
import view.EmailEditFrame;

public class DeleteEmailButtonClick implements ActionListener {
	private EmailEditFrame emailEditFrame;
	private IocContainer container;

	public DeleteEmailButtonClick(EmailEditFrame emailEditFrame, IocContainer container) {
		this.emailEditFrame = emailEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = emailEditFrame.getEmailId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(emailEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					EmailService service = container.getEmailService();
					service.delete(id);
					JOptionPane.showMessageDialog(emailEditFrame, "Data was successfully deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
					emailEditFrame.update(service.getAll());
					emailEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(emailEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
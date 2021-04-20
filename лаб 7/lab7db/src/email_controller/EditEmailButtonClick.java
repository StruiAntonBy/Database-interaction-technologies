package email_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Email;
import ioc.IocContainer;
import view.EmailEditFrame;
import view.EmailsListFrame;

public class EditEmailButtonClick implements ActionListener {
	private EmailsListFrame emailsListFrame;
	private IocContainer container;

	public EditEmailButtonClick(EmailsListFrame emailsListFrame, IocContainer container) {
		this.emailsListFrame = emailsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Email email = emailsListFrame.getSelectedEmail();
		if(email != null) {
			new EmailEditFrame(emailsListFrame, email, container);
		} else {
			JOptionPane.showMessageDialog(emailsListFrame, "No e-mail is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
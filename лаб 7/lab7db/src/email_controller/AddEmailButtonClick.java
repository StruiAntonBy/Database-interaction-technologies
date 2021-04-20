package email_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.EmailEditFrame;
import view.EmailsListFrame;

public class AddEmailButtonClick implements ActionListener {
	private EmailsListFrame emailsListFrame;
	private IocContainer container;

	public AddEmailButtonClick(EmailsListFrame emailsListFrame, IocContainer container) {
		this.emailsListFrame = emailsListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new EmailEditFrame(emailsListFrame, container);
	}
}
package users_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.UserEditFrame;
import view.UsersListFrame;

public class AddUserButtonClick implements ActionListener {
	private UsersListFrame usersListFrame;
	private IocContainer container;

	public AddUserButtonClick(UsersListFrame usersListFrame, IocContainer container) {
		this.usersListFrame = usersListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new UserEditFrame(usersListFrame, container);
	}
}
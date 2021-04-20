package users_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Users;
import ioc.IocContainer;
import view.UserEditFrame;
import view.UsersListFrame;

public class EditUserButtonClick implements ActionListener {
	private UsersListFrame usersListFrame;
	private IocContainer container;

	public EditUserButtonClick(UsersListFrame usersListFrame, IocContainer container) {
		this.usersListFrame = usersListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Users user = usersListFrame.getSelectedUser();
		if(user != null) {
			new UserEditFrame(usersListFrame, user, container);
		} else {
			JOptionPane.showMessageDialog(usersListFrame, "No user is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
package users_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Users;
import ioc.ContainerException;
import ioc.IocContainer;
import service.UsersService;
import service.ServiceException;
import view.UserEditFrame;

public class SaveUserButtonClick implements ActionListener {
	private UserEditFrame userEditFrame;
	private IocContainer container;

	public SaveUserButtonClick(UserEditFrame userEditFrame, IocContainer container) {
		this.userEditFrame = userEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Users user = userEditFrame.getUser();
		if(user != null) {
			try {
				UsersService service = container.getUsersService();
				service.save(user);
				JOptionPane.showMessageDialog(userEditFrame, "Data was saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				userEditFrame.update(service.getAll());
				userEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(userEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

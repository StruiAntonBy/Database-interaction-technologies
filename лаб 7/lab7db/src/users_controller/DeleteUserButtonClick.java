package users_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ioc.ContainerException;
import ioc.IocContainer;
import service.UsersService;
import service.ServiceException;
import view.UserEditFrame;

public class DeleteUserButtonClick implements ActionListener {
	private UserEditFrame userEditFrame;
	private IocContainer container;

	public DeleteUserButtonClick(UserEditFrame userEditFrame, IocContainer container) {
		this.userEditFrame = userEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Long id = userEditFrame.getUserId();
		if(id != null) {
			if(JOptionPane.showConfirmDialog(userEditFrame, "Do you really want to delete an entry?", "Action confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					UsersService service = container.getUsersService();
					service.delete(id);
					JOptionPane.showMessageDialog(userEditFrame, "Data was successfully deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
					userEditFrame.update(service.getAll());
					userEditFrame.dispose();
				} catch(ContainerException | ServiceException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(userEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}

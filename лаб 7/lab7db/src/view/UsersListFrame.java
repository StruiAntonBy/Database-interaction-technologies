package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.Users;
import ioc.IocContainer;
import table_model.UsersListTableModel;
import users_controller.AddUserButtonClick;
import users_controller.EditUserButtonClick;

public class UsersListFrame extends JFrame {
	private UsersListTableModel model;
	private JTable usersListTable;

	public UsersListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of users");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new UsersListTableModel();
		usersListTable = new JTable(model);
		usersListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(usersListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addUserButton = new JButton("Add");
			addUserButton.setPreferredSize(new Dimension(100, 30));
			addUserButton.addActionListener(new AddUserButtonClick(this, container));
			buttonsPanel.add(addUserButton);
			JButton editUserButton = new JButton("Edit");
			editUserButton.setPreferredSize(new Dimension(100, 30));
			editUserButton.addActionListener(new EditUserButtonClick(this, container));
			buttonsPanel.add(editUserButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setUsers(List<Users> users) {
		model.setUsers(users);
	}

	public Users getSelectedUser() {
		int index = usersListTable.getSelectedRow();
		if(index != -1) {
			return model.getUser(index);
		}
		return null;
	}
}

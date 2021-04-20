package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import domain.Users;
import ioc.IocContainer;
import users_controller.DeleteUserButtonClick;
import users_controller.SaveUserButtonClick;

public class UserEditFrame extends JDialog{
	private Users user;
	private JTextField loginTextField;
	private JPasswordField passwordPasswordField;
	private JRadioButton leftButton,centerButton,rightButton;

	private UserEditFrame(JFrame owner, Users user, String title, IocContainer container) {
		super(owner, title);
		this.user = user;
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 325);
		setResizable(false);
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		JLabel loginLabel = new JLabel("Login:");
		loginLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(loginLabel, constraints);
		add(loginLabel);

		loginTextField = new JTextField(user.getLogin());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(loginTextField, constraints);
		add(loginTextField);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(passwordLabel, constraints);
		add(passwordLabel);

		passwordPasswordField = new JPasswordField(user.getPassword());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(passwordPasswordField, constraints);
		add(passwordPasswordField);
		
		JLabel roleLabel = new JLabel("Role:");
		roleLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(roleLabel, constraints);
		add(roleLabel);
		
		ButtonGroup group = new ButtonGroup();
		switch(user.getRole()) {
			case 1:
				leftButton = new JRadioButton("1", true);
				centerButton = new JRadioButton("2", false);
				rightButton = new JRadioButton("3", false);
				break;
			case 2:
				leftButton = new JRadioButton("1", false);
				centerButton = new JRadioButton("2", true);
				rightButton = new JRadioButton("3", false);
				break;
			case 3:
				leftButton = new JRadioButton("1", false);
				centerButton = new JRadioButton("2", false);
				rightButton = new JRadioButton("3", true);
				break;
			default:
				leftButton = new JRadioButton("1", false);
				centerButton = new JRadioButton("2", false);
				rightButton = new JRadioButton("3", false);
				break;
		}
		group.add(leftButton);
		group.add(centerButton);
		group.add(rightButton);
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(leftButton, constraints);
		add(leftButton);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(centerButton, constraints);
		add(centerButton);
		constraints.gridwidth = 1;
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(rightButton, constraints);
		add(rightButton);

		JButton saveButton = new JButton("Save");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveUserButtonClick(this, container));
		add(saveButton);

		if(user.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 3;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteUserButtonClick(this, container));
			add(deleteButton);
		} else {
			JLabel emptyLabel = new JLabel();
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(emptyLabel, constraints);
			add(emptyLabel);
		}

		setVisible(true);
	}

	public UserEditFrame(JFrame owner, Users user, IocContainer container) {
		this(owner, user, String.format("Editing the user %s %s %s", user.getLogin(), user.getPassword(), user.getRole()), container);
	}

	public UserEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Users(), "Adding an user", container);
	}

	public Users getUser() {
		String login = loginTextField.getText();
		if(login.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Login» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		user.setLogin(login);
		String password = new String(passwordPasswordField.getPassword());
		if(password.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Password» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		user.setPassword(password);
		if(leftButton.isSelected()) {
			user.setRole(1);
		}
		else if(centerButton.isSelected()) {
			user.setRole(2);
		}
		else if(rightButton.isSelected()) {
			user.setRole(3);
		}
		else {
			JOptionPane.showMessageDialog(getOwner(), "The «Role» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return user;
	}

	public Long getUserId() {
		return user != null ? user.getId() : null;
	}

	public void update(List<Users> users) {
		((UsersListFrame)getOwner()).setUsers(users);
	}
}

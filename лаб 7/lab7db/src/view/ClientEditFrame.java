package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.Client;
import ioc.IocContainer;
import client_controller.DeleteClientButtonClick;
import client_controller.SaveClientButtonClick;

public class ClientEditFrame extends JDialog{
	private Client client;
	private JTextField nameTextField,registeredaddressTextField,bankdetailsTextField;

	private ClientEditFrame(JFrame owner, Client client, String title, IocContainer container) {
		super(owner, title);
		this.client = client;
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

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 20, 10, 10);
		layout.setConstraints(nameLabel, constraints);
		add(nameLabel);

		nameTextField = new JTextField(client.getName());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(40, 10, 10, 20);
		layout.setConstraints(nameTextField, constraints);
		add(nameTextField);

		JLabel registeredaddressLabel = new JLabel("Registered address:");
		registeredaddressLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(registeredaddressLabel, constraints);
		add(registeredaddressLabel);

		registeredaddressTextField = new JTextField(client.getRegisteredAddress());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(registeredaddressTextField, constraints);
		add(registeredaddressTextField);
		
		JLabel bankdetailsLabel = new JLabel("Bank details:");
		bankdetailsLabel.setHorizontalAlignment(JLabel.RIGHT);
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 20, 10, 10);
		layout.setConstraints(bankdetailsLabel, constraints);
		add(bankdetailsLabel);
		
		bankdetailsTextField = new JTextField(client.getBankDetails());
		constraints.gridwidth = 2;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(10, 10, 10, 20);
		layout.setConstraints(bankdetailsTextField, constraints);
		add(bankdetailsTextField);

		JButton saveButton = new JButton("Save");
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.insets = new Insets(20, 10, 40, 10);
		layout.setConstraints(saveButton, constraints);
		saveButton.addActionListener(new SaveClientButtonClick(this, container));
		add(saveButton);

		if(client.getId() != null) {
			JButton deleteButton = new JButton("Delete");
			constraints.gridwidth = 1;
			constraints.gridx = 2;
			constraints.gridy = 3;
			constraints.insets = new Insets(20, 10, 40, 20);
			layout.setConstraints(deleteButton, constraints);
			deleteButton.addActionListener(new DeleteClientButtonClick(this, container));
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

	public ClientEditFrame(JFrame owner, Client client, IocContainer container) {
		this(owner, client, String.format("Editing the client %s %s %s", client.getName(),client.getRegisteredAddress(),client.getBankDetails()), container);
	}

	public ClientEditFrame(JFrame owner, IocContainer container) {
		this(owner, new Client(), "Adding an client", container);
	}

	public Client getClient() {
		String name = nameTextField.getText();
		if(name.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Name» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		client.setName(name);
		String registeredaddress = new String(registeredaddressTextField.getText());
		if(registeredaddress.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Registered address» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		client.setRegisteredAddress(registeredaddress);
		String bankdetails = new String(bankdetailsTextField.getText());
		if(bankdetails.trim().length() == 0) {
			JOptionPane.showMessageDialog(getOwner(), "The «Bank details» field is empty", "Input error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		client.setBankDetails(bankdetails);
		return client;
	}

	public Long getClientId() {
		return client != null ? client.getId() : null;
	}

	public void update(List<Client> clients) {
		((ClientsListFrame)getOwner()).setClients(clients);
	}
}
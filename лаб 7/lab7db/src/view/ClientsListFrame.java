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

import domain.Client;
import ioc.IocContainer;
import table_model.ClientListTableModel;
import client_controller.AddClientButtonClick;
import client_controller.EditClientButtonClick;

public class ClientsListFrame extends JFrame {
	private ClientListTableModel model;
	private JTable clientsListTable;

	public ClientsListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of clients");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new ClientListTableModel();
		clientsListTable = new JTable(model);
		clientsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(clientsListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addClientButton = new JButton("Add");
			addClientButton.setPreferredSize(new Dimension(100, 30));
			addClientButton.addActionListener(new AddClientButtonClick(this, container));
			buttonsPanel.add(addClientButton);
			JButton editClientButton = new JButton("Edit");
			editClientButton.setPreferredSize(new Dimension(100, 30));
			editClientButton.addActionListener(new EditClientButtonClick(this, container));
			buttonsPanel.add(editClientButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setClients(List<Client> clients) {
		model.setClients(clients);
	}

	public Client getSelectedClient() {
		int index = clientsListTable.getSelectedRow();
		if(index != -1) {
			return model.getClient(index);
		}
		return null;
	}
}
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

import domain.Email;
import ioc.IocContainer;
import table_model.EmailListTableModel;
import email_controller.AddEmailButtonClick;
import email_controller.EditEmailButtonClick;

public class EmailsListFrame extends JFrame {
	private EmailListTableModel model;
	private JTable emailsListTable;

	public EmailsListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of e-mails");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new EmailListTableModel();
		emailsListTable = new JTable(model);
		emailsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(emailsListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addEmailButton = new JButton("Add");
			addEmailButton.setPreferredSize(new Dimension(100, 30));
			addEmailButton.addActionListener(new AddEmailButtonClick(this, container));
			buttonsPanel.add(addEmailButton);
			JButton editEmailButton = new JButton("Edit");
			editEmailButton.setPreferredSize(new Dimension(100, 30));
			editEmailButton.addActionListener(new EditEmailButtonClick(this, container));
			buttonsPanel.add(editEmailButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setEmails(List<Email> emails) {
		model.setEmails(emails);
	}

	public Email getSelectedEmail() {
		int index = emailsListTable.getSelectedRow();
		if(index != -1) {
			return model.getEmail(index);
		}
		return null;
	}
}
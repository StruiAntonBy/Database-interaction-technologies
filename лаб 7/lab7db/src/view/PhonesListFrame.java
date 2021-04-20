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

import domain.Phone;
import ioc.IocContainer;
import table_model.PhoneListTableModel;
import phone_controller.AddPhoneButtonClick;
import phone_controller.EditPhoneButtonClick;

public class PhonesListFrame extends JFrame {
	private PhoneListTableModel model;
	private JTable phonesListTable;

	public PhonesListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of phones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new PhoneListTableModel();
		phonesListTable = new JTable(model);
		phonesListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(phonesListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addPhoneButton = new JButton("Add");
			addPhoneButton.setPreferredSize(new Dimension(100, 30));
			addPhoneButton.addActionListener(new AddPhoneButtonClick(this, container));
			buttonsPanel.add(addPhoneButton);
			JButton editPhoneButton = new JButton("Edit");
			editPhoneButton.setPreferredSize(new Dimension(100, 30));
			editPhoneButton.addActionListener(new EditPhoneButtonClick(this, container));
			buttonsPanel.add(editPhoneButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setPhones(List<Phone> phones) {
		model.setPhones(phones);
	}

	public Phone getSelectedPhone() {
		int index = phonesListTable.getSelectedRow();
		if(index != -1) {
			return model.getPhone(index);
		}
		return null;
	}
}
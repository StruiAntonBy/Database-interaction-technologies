package view;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.Lab4Task33;
import ioc.IocContainer;
import table_model.Lab4Task33ListTableModel;

public class Lab4Task33ListFrame extends JFrame {
	private Lab4Task33ListTableModel model;
	private JTable lab4task33ListTable;

	public Lab4Task33ListFrame(IocContainer container) throws HeadlessException {
		super("List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new Lab4Task33ListTableModel();
		lab4task33ListTable = new JTable(model);
		lab4task33ListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(lab4task33ListTable);
		add(jScrollPane);
		setVisible(true);
	}

	public void setPersons(List<Lab4Task33> list) {
		model.setPersons(list);
	}

	public Lab4Task33 getSelectedContactPerson() {
		int index = lab4task33ListTable.getSelectedRow();
		if(index != -1) {
			return model.getPerson(index);
		}
		return null;
	}
}
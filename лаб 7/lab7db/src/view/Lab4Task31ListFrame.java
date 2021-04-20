package view;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.Lab4Task31;
import ioc.IocContainer;
import table_model.Lab4Task31ListTableModel;

public class Lab4Task31ListFrame extends JFrame {
	private Lab4Task31ListTableModel model;
	private JTable lab4task31ListTable;

	public Lab4Task31ListFrame(IocContainer container) throws HeadlessException {
		super("List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new Lab4Task31ListTableModel();
		lab4task31ListTable = new JTable(model);
		lab4task31ListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(lab4task31ListTable);
		add(jScrollPane);
		setVisible(true);
	}

	public void setPersons(List<Lab4Task31> list) {
		model.setPersons(list);
	}

	public Lab4Task31 getSelectedContactPerson() {
		int index = lab4task31ListTable.getSelectedRow();
		if(index != -1) {
			return model.getPerson(index);
		}
		return null;
	}
}
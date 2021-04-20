package view;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.Lab4Task32;
import ioc.IocContainer;
import table_model.Lab4Task32ListTableModel;

public class Lab4Task32ListFrame extends JFrame {
	private Lab4Task32ListTableModel model;
	private JTable lab4task32ListTable;

	public Lab4Task32ListFrame(IocContainer container) throws HeadlessException {
		super("List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new Lab4Task32ListTableModel();
		lab4task32ListTable = new JTable(model);
		lab4task32ListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(lab4task32ListTable);
		add(jScrollPane);
		setVisible(true);
	}

	public void setPersons(List<Lab4Task32> list) {
		model.setPersons(list);
	}

	public Lab4Task32 getSelectedContactPerson() {
		int index = lab4task32ListTable.getSelectedRow();
		if(index != -1) {
			return model.getPerson(index);
		}
		return null;
	}
}
package view;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.Lab4Task42;
import ioc.IocContainer;
import table_model.Lab4Task42ListTableModel;

public class Lab4Task42ListFrame extends JFrame {
	private Lab4Task42ListTableModel model;
	private JTable lab4task42ListTable;

	public Lab4Task42ListFrame(IocContainer container) throws HeadlessException {
		super("List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new Lab4Task42ListTableModel();
		lab4task42ListTable = new JTable(model);
		lab4task42ListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(lab4task42ListTable);
		add(jScrollPane);
		setVisible(true);
	}

	public void setTests(List<Lab4Task42> list) {
		model.setTests(list);
	}

	public Lab4Task42 getSelectedContactTest() {
		int index = lab4task42ListTable.getSelectedRow();
		if(index != -1) {
			return model.getTest(index);
		}
		return null;
	}
}
package view;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.Lab4Task41;
import ioc.IocContainer;
import table_model.Lab4Task41ListTableModel;

public class Lab4Task41ListFrame extends JFrame {
	private Lab4Task41ListTableModel model;
	private JTable lab4task41ListTable;

	public Lab4Task41ListFrame(IocContainer container) throws HeadlessException {
		super("List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new Lab4Task41ListTableModel();
		lab4task41ListTable = new JTable(model);
		lab4task41ListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(lab4task41ListTable);
		add(jScrollPane);
		setVisible(true);
	}

	public void setTests(List<Lab4Task41> list) {
		model.setTests(list);
	}

	public Lab4Task41 getSelectedContactTest() {
		int index = lab4task41ListTable.getSelectedRow();
		if(index != -1) {
			return model.getTest(index);
		}
		return null;
	}
}
package view;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.Lab4Task22;
import ioc.IocContainer;
import table_model.Lab4Task22ListTableModel;

public class Lab4Task22ListFrame extends JFrame {
	private Lab4Task22ListTableModel model;
	private JTable lab4task22ListTable;

	public Lab4Task22ListFrame(IocContainer container) throws HeadlessException {
		super("List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new Lab4Task22ListTableModel();
		lab4task22ListTable = new JTable(model);
		lab4task22ListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(lab4task22ListTable);
		add(jScrollPane);
		setVisible(true);
	}

	public void setList(List<Lab4Task22> list) {
		model.setList(list);
	}

	public Lab4Task22 getSelectedLab4Task22() {
		int index = lab4task22ListTable.getSelectedRow();
		if(index != -1) {
			return model.getLab4Task22(index);
		}
		return null;
	}
}
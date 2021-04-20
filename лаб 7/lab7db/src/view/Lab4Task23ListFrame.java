package view;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import domain.Requirement;
import ioc.IocContainer;
import table_model.Lab4Task23ListTableModel;

public class Lab4Task23ListFrame extends JFrame {
	private Lab4Task23ListTableModel model;
	private JTable lab4task23ListTable;

	public Lab4Task23ListFrame(IocContainer container) throws HeadlessException {
		super("List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new Lab4Task23ListTableModel();
		lab4task23ListTable = new JTable(model);
		lab4task23ListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(lab4task23ListTable);
		add(jScrollPane);
		setVisible(true);
	}

	public void setRequirements(List<Requirement> list) {
		model.setRequirements(list);
	}

	public Requirement getSelectedRequirement() {
		int index = lab4task23ListTable.getSelectedRow();
		if(index != -1) {
			return model.getRequirement(index);
		}
		return null;
	}
}
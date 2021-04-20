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

import domain.SoftwareTester;
import ioc.IocContainer;
import table_model.SoftwareTesterListTableModel;
import software_tester_controller.AddSoftwareTesterButtonClick;
import software_tester_controller.EditSoftwareTesterButtonClick;

public class SoftwareTestersListFrame extends JFrame {
	private SoftwareTesterListTableModel model;
	private JTable testersListTable;

	public SoftwareTestersListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of software testers");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new SoftwareTesterListTableModel();
		testersListTable = new JTable(model);
		testersListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(testersListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addSoftwareTesterButton = new JButton("Add");
			addSoftwareTesterButton.setPreferredSize(new Dimension(100, 30));
			addSoftwareTesterButton.addActionListener(new AddSoftwareTesterButtonClick(this, container));
			buttonsPanel.add(addSoftwareTesterButton);
			JButton editSoftwareTesterButton = new JButton("Edit");
			editSoftwareTesterButton.setPreferredSize(new Dimension(100, 30));
			editSoftwareTesterButton.addActionListener(new EditSoftwareTesterButtonClick(this, container));
			buttonsPanel.add(editSoftwareTesterButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setTesters(List<SoftwareTester> testers) {
		model.setTesters(testers);
	}

	public SoftwareTester getSelectedTester() {
		int index = testersListTable.getSelectedRow();
		if(index != -1) {
			return model.getTester(index);
		}
		return null;
	}
}

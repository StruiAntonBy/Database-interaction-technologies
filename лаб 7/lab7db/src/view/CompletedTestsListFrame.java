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

import domain.CompletedTest;
import ioc.IocContainer;
import table_model.CompletedTestListTableModel;
import completed_test_controller.AddCompletedTestButtonClick;
import completed_test_controller.EditCompletedTestButtonClick;

public class CompletedTestsListFrame extends JFrame {
	private CompletedTestListTableModel model;
	private JTable testsListTable;

	public CompletedTestsListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of completed tests");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new CompletedTestListTableModel();
		testsListTable = new JTable(model);
		testsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(testsListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addCompletedTestButton = new JButton("Add");
			addCompletedTestButton.setPreferredSize(new Dimension(100, 30));
			addCompletedTestButton.addActionListener(new AddCompletedTestButtonClick(this, container));
			buttonsPanel.add(addCompletedTestButton);
			JButton editCompletedTestButton = new JButton("Edit");
			editCompletedTestButton.setPreferredSize(new Dimension(100, 30));
			editCompletedTestButton.addActionListener(new EditCompletedTestButtonClick(this, container));
			buttonsPanel.add(editCompletedTestButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setTests(List<CompletedTest> tests) {
		model.setTests(tests);
	}

	public CompletedTest getSelectedTest() {
		int index = testsListTable.getSelectedRow();
		if(index != -1) {
			return model.getTest(index);
		}
		return null;
	}
}
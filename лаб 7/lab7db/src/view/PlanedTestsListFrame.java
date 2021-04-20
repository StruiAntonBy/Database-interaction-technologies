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

import domain.PlanedTest;
import ioc.IocContainer;
import table_model.PlanedTestListTableModel;
import planed_test_controller.AddPlanedTestButtonClick;
import planed_test_controller.EditPlanedTestButtonClick;

public class PlanedTestsListFrame extends JFrame {
	private PlanedTestListTableModel model;
	private JTable testsListTable;

	public PlanedTestsListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of planed tests");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new PlanedTestListTableModel();
		testsListTable = new JTable(model);
		testsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(testsListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addPlanedTestButton = new JButton("Add");
			addPlanedTestButton.setPreferredSize(new Dimension(100, 30));
			addPlanedTestButton.addActionListener(new AddPlanedTestButtonClick(this, container));
			buttonsPanel.add(addPlanedTestButton);
			JButton editPlanedTestButton = new JButton("Edit");
			editPlanedTestButton.setPreferredSize(new Dimension(100, 30));
			editPlanedTestButton.addActionListener(new EditPlanedTestButtonClick(this, container));
			buttonsPanel.add(editPlanedTestButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setTests(List<PlanedTest> tests) {
		model.setTests(tests);
	}

	public PlanedTest getSelectedTest() {
		int index = testsListTable.getSelectedRow();
		if(index != -1) {
			return model.getTest(index);
		}
		return null;
	}
}
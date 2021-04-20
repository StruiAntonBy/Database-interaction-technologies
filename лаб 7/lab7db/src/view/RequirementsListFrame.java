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

import domain.Requirement;
import ioc.IocContainer;
import table_model.RequirementListTableModel;
import requirement_controller.AddRequirementButtonClick;
import requirement_controller.EditRequirementButtonClick;

public class RequirementsListFrame extends JFrame {
	private RequirementListTableModel model;
	private JTable requirementsListTable;

	public RequirementsListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of requirements");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new RequirementListTableModel();
		requirementsListTable = new JTable(model);
		requirementsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(requirementsListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addRequirementButton = new JButton("Add");
			addRequirementButton.setPreferredSize(new Dimension(100, 30));
			addRequirementButton.addActionListener(new AddRequirementButtonClick(this, container));
			buttonsPanel.add(addRequirementButton);
			JButton editRequirementButton = new JButton("Edit");
			editRequirementButton.setPreferredSize(new Dimension(100, 30));
			editRequirementButton.addActionListener(new EditRequirementButtonClick(this, container));
			buttonsPanel.add(editRequirementButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setRequirements(List<Requirement> requirements) {
		model.setRequirements(requirements);
	}

	public Requirement getSelectedRequirement() {
		int index = requirementsListTable.getSelectedRow();
		if(index != -1) {
			return model.getRequirement(index);
		}
		return null;
	}
}
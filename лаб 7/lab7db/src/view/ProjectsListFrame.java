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

import domain.Project;
import ioc.IocContainer;
import table_model.ProjectListTableModel;
import project_controller.AddProjectButtonClick;
import project_controller.EditProjectButtonClick;

public class ProjectsListFrame extends JFrame {
	private ProjectListTableModel model;
	private JTable projectsListTable;

	public ProjectsListFrame(IocContainer container,int option) throws HeadlessException {
		super("List of projects");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		model = new ProjectListTableModel();
		projectsListTable = new JTable(model);
		projectsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(projectsListTable);
		add(jScrollPane);
		if(option==0) {
			FlowLayout buttonsPanelLayout = new FlowLayout(FlowLayout.LEFT);
			JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
			JButton addProjectButton = new JButton("Add");
			addProjectButton.setPreferredSize(new Dimension(100, 30));
			addProjectButton.addActionListener(new AddProjectButtonClick(this, container));
			buttonsPanel.add(addProjectButton);
			JButton editProjectButton = new JButton("Edit");
			editProjectButton.setPreferredSize(new Dimension(100, 30));
			editProjectButton.addActionListener(new EditProjectButtonClick(this, container));
			buttonsPanel.add(editProjectButton);
			add(buttonsPanel, BorderLayout.SOUTH);
		}
		setVisible(true);
	}

	public void setProjects(List<Project> projects) {
		model.setProjects(projects);
	}

	public Project getSelectedProject() {
		int index = projectsListTable.getSelectedRow();
		if(index != -1) {
			return model.getProject(index);
		}
		return null;
	}
}
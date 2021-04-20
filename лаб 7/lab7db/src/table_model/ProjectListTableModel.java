package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Project;

public class ProjectListTableModel implements TableModel {
	private List<Project> projects = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum ProjectFiled {
		ID("ID") {
			@Override
			public String getValue(Project project) {
				return String.format("%03d", project.getId());
			}
		},
		CLIENT_ID("Client id") {
			@Override
			public String getValue(Project project) {
				return String.format("%03d",project.getClientId());
			}
		},
		START_DATE("Start date") {
			@Override
			public String getValue(Project project) {
				return project.getStartDate();
			}
		},
		END_DATE("End date") {
			@Override
			public String getValue(Project project) {
				return project.getEndDate();
			}
		};

		private final String columnName;

		private ProjectFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Project project);
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Project getProject(int index) {
		return projects.get(index);
	}

	@Override
	public int getRowCount() {
		return projects.size();
	}

	@Override
	public int getColumnCount() {
		return ProjectFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return ProjectFiled.values()[columnIndex].getColumnName();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Project project = projects.get(rowIndex);
		return ProjectFiled.values()[columnIndex].getValue(project);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}
}
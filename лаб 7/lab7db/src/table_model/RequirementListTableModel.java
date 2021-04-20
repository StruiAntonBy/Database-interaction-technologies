package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Requirement;

public class RequirementListTableModel implements TableModel {
	private List<Requirement> requirements = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum RequirementFiled {
		ID("ID") {
			@Override
			public String getValue(Requirement requirement) {
				return String.format("%03d", requirement.getId());
			}
		},
		PROJECT_ID("Project id") {
			@Override
			public String getValue(Requirement requirement) {
				return String.format("%03d",requirement.getProjectId());
			}
		},
		REQUIREMENT("Requirement") {
			@Override
			public String getValue(Requirement requirement) {
				return requirement.getRequirement();
			}
		},
		START_DATE("Start date") {
			@Override
			public String getValue(Requirement requirement) {
				return requirement.getStartDate();
			}
		},
		PLANNED_TIME("Planned time") {
			@Override
			public String getValue(Requirement requirement) {
				return String.valueOf(requirement.getPlannedTime());
			}
		},
		THE_PRIORITY_OF("The priority of") {
			@Override
			public String getValue(Requirement requirement) {
				return requirement.getThePriorityOf();
			}
		},
		A_MARK_OF_COMPLETION("A mark of completion") {
			@Override
			public String getValue(Requirement requirement) {
				return requirement.getMarkOfCompletion();
			}
		},
		THE_PROBABILITY_OF_A_CHANGE("The probability of a change") {
			@Override
			public String getValue(Requirement requirement) {
				return requirement.getTheProbabilityOfChange();
			}
		};

		private final String columnName;

		private RequirementFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Requirement requirement);
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Requirement getRequirement(int index) {
		return requirements.get(index);
	}

	@Override
	public int getRowCount() {
		return requirements.size();
	}

	@Override
	public int getColumnCount() {
		return RequirementFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return RequirementFiled.values()[columnIndex].getColumnName();
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
		Requirement requirement = requirements.get(rowIndex);
		return RequirementFiled.values()[columnIndex].getValue(requirement);
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
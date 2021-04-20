package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Lab4Task22;

public class Lab4Task22ListTableModel implements TableModel {
	private List<Lab4Task22> list = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum Lab4Task22Filed {
		ID("ID") {
			@Override
			public String getValue(Lab4Task22 element) {
				return String.format("%03d", element.getId());
			}
		},
		CLIENT_ID("Client id") {
			@Override
			public String getValue(Lab4Task22 element) {
				return String.format("%03d",element.getClientId());
			}
		},
		START_DATE("Start date") {
			@Override
			public String getValue(Lab4Task22 element) {
				return element.getStartDate();
			}
		},
		END_DATE("End date") {
			@Override
			public String getValue(Lab4Task22 element) {
				return element.getEndDate();
			}
		},
		ALL_REQUIREMENT("All requirement") {
			@Override
			public String getValue(Lab4Task22 element) {
				return String.valueOf(element.getAllRequirement());
			}
		},
		REQUIREMENT_LOW("Requirement low") {
			@Override
			public String getValue(Lab4Task22 element) {
				return String.valueOf(element.getRequirementLow());
			}
		},
		REQUIREMENT_MIDDLE("Requirement middle") {
			@Override
			public String getValue(Lab4Task22 element) {
				return String.valueOf(element.getRequirementMiddle());
			}
		},
		REQUIREMENT_HIGH("Requirement high") {
			@Override
			public String getValue(Lab4Task22 element) {
				return String.valueOf(element.getRequirementHigh());
			}
		},
		PERCENT_HIGH("Percent high") {
			@Override
			public String getValue(Lab4Task22 element) {
				return String.valueOf(element.getPercentHigh());
			}
		},
		PERCENT_MIDDLE("Percent middle") {
			@Override
			public String getValue(Lab4Task22 element) {
				return String.valueOf(element.getPercentMiddle());
			}
		},
		PERCENT_LOW("Percent low") {
			@Override
			public String getValue(Lab4Task22 element) {
				return String.valueOf(element.getPercentLow());
			}
		};

		private final String columnName;

		private Lab4Task22Filed(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Lab4Task22 element);
	}

	public void setList(List<Lab4Task22> list) {
		this.list = list;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Lab4Task22 getLab4Task22(int index) {
		return list.get(index);
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return Lab4Task22Filed.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return Lab4Task22Filed.values()[columnIndex].getColumnName();
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
		Lab4Task22 element = list.get(rowIndex);
		return Lab4Task22Filed.values()[columnIndex].getValue(element);
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
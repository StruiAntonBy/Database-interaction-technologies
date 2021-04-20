package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Lab4Task41;

public class Lab4Task41ListTableModel implements TableModel {
	private List<Lab4Task41> tests = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum Lab4Task41Filed {
		PLANED_TEST_ID("Planed test id") {
			@Override
			public String getValue(Lab4Task41 test) {
				return String.format("%03d",test.getPlanedTestId());
			}
		},
		IDCOUNT("Idcount") {
			@Override
			public String getValue(Lab4Task41 test) {
				return String.valueOf(test.getIdCount());
			}
		};

		private final String columnName;

		private Lab4Task41Filed(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Lab4Task41 test);
	}

	public void setTests(List<Lab4Task41> tests) {
		this.tests = tests;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Lab4Task41 getTest(int index) {
		return tests.get(index);
	}

	@Override
	public int getRowCount() {
		return tests.size();
	}

	@Override
	public int getColumnCount() {
		return Lab4Task41Filed.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return Lab4Task41Filed.values()[columnIndex].getColumnName();
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
		Lab4Task41 test = tests.get(rowIndex);
		return Lab4Task41Filed.values()[columnIndex].getValue(test);
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
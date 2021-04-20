package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.CompletedTest;

public class CompletedTestListTableModel implements TableModel {
	private List<CompletedTest> tests = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum CompletedTestFiled {
		ID("ID") {
			@Override
			public String getValue(CompletedTest test) {
				return String.format("%03d", test.getId());
			}
		},
		TESTER_ID("Tester id") {
			@Override
			public String getValue(CompletedTest test) {
				return String.format("%03d",test.getTesterId());
			}
		},
		PLANED_TEST_ID("Planed test id") {
			@Override
			public String getValue(CompletedTest test) {
				return String.format("%03d",test.getPlanedTestId());
			}
		},
		START_DATE_AND_TIME("Start date") {
			@Override
			public String getValue(CompletedTest test) {
				return test.getStartDateAndTime();
			}
		},
		LENGTH("Length") {
			@Override
			public String getValue(CompletedTest test) {
				return String.valueOf(test.getLength());
			}
		},
		RESULT("Result") {
			@Override
			public String getValue(CompletedTest test) {
				return test.getResult();
			}
		};

		private final String columnName;

		private CompletedTestFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(CompletedTest test);
	}

	public void setTests(List<CompletedTest> tests) {
		this.tests = tests;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public CompletedTest getTest(int index) {
		return tests.get(index);
	}

	@Override
	public int getRowCount() {
		return tests.size();
	}

	@Override
	public int getColumnCount() {
		return CompletedTestFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return CompletedTestFiled.values()[columnIndex].getColumnName();
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
		CompletedTest test = tests.get(rowIndex);
		return CompletedTestFiled.values()[columnIndex].getValue(test);
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
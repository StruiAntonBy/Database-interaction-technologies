package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.PlanedTest;

public class PlanedTestListTableModel implements TableModel {
	private List<PlanedTest> tests = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum PlanedTestFiled {
		ID("ID") {
			@Override
			public String getValue(PlanedTest test) {
				return String.format("%03d", test.getId());
			}
		},
		REQUIREMENT_ID("Requirement id") {
			@Override
			public String getValue(PlanedTest test) {
				return String.format("%03d",test.getRequirementId());
			}
		},
		DESCRIPTION_OF_THE_PERFORMANCE("Description of the performance") {
			@Override
			public String getValue(PlanedTest test) {
				return test.getDescriptionOfThePerformance();
			}
		},
		EXPECTED_RESULT("Expected result") {
			@Override
			public String getValue(PlanedTest test) {
				return test.getExpectedResult();
			}
		},
		PLANNED_TIME("Planned time") {
			@Override
			public String getValue(PlanedTest test) {
				return String.valueOf(test.getPlannedTime());
			}
		},
		LEVEL_TEST("Level test") {
			@Override
			public String getValue(PlanedTest test) {
				return test.getLevelTest();
			}
		};

		private final String columnName;

		private PlanedTestFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(PlanedTest test);
	}

	public void setTests(List<PlanedTest> tests) {
		this.tests = tests;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public PlanedTest getTest(int index) {
		return tests.get(index);
	}

	@Override
	public int getRowCount() {
		return tests.size();
	}

	@Override
	public int getColumnCount() {
		return PlanedTestFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return PlanedTestFiled.values()[columnIndex].getColumnName();
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
		PlanedTest test = tests.get(rowIndex);
		return PlanedTestFiled.values()[columnIndex].getValue(test);
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
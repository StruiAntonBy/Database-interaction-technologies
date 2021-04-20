package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.SoftwareTester;

public class SoftwareTesterListTableModel implements TableModel{
	private List<SoftwareTester> testers = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum TesterFiled {
		ID("ID") {
			@Override
			public String getValue(SoftwareTester tester) {
				return String.format("%03d", tester.getId());
			}
		},
		SURNAME("Surname") {
			@Override
			public String getValue(SoftwareTester tester) {
				return tester.getSurname();
			}
		},
		NAME("Name") {
			@Override
			public String getValue(SoftwareTester tester) {
				return tester.getName();
			}
		},
		MIDDLE_NAME("Middle name") {
			@Override
			public String getValue(SoftwareTester tester) {
				return tester.getMiddleName();
			}
		},
		WORK_EXPERIENCE("Work experience") {
			@Override
			public String getValue(SoftwareTester tester) {
				return String.valueOf(tester.getWorkExperience());
			}
		};

		private final String columnName;

		private TesterFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(SoftwareTester tester);
	}

	public void setTesters(List<SoftwareTester> testers) {
		this.testers = testers;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public SoftwareTester getTester(int index) {
		return testers.get(index);
	}

	@Override
	public int getRowCount() {
		return testers.size();
	}

	@Override
	public int getColumnCount() {
		return TesterFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return TesterFiled.values()[columnIndex].getColumnName();
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
		SoftwareTester tester = testers.get(rowIndex);
		return TesterFiled.values()[columnIndex].getValue(tester);
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

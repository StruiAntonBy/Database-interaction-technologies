package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Lab4Task33;

public class Lab4Task33ListTableModel implements TableModel{
	private List<Lab4Task33> persons = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum Lab4Task33Filed {
		SURNAME("Surname") {
			@Override
			public String getValue(Lab4Task33 person) {
				return person.getSurname();
			}
		},
		NAME("Name") {
			@Override
			public String getValue(Lab4Task33 person) {
				return person.getName();
			}
		},
		MIDDLE_NAME("Middle name") {
			@Override
			public String getValue(Lab4Task33 person) {
				return person.getMiddleName();
			}
		},
		WORK_EXPERIENCE("Work experience") {
			@Override
			public String getValue(Lab4Task33 person) {
				return String.valueOf(person.getWorkExperience());
			}
		};

		private final String columnName;

		private Lab4Task33Filed(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Lab4Task33 person);
	}

	public void setPersons(List<Lab4Task33> persons) {
		this.persons = persons;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Lab4Task33 getPerson(int index) {
		return persons.get(index);
	}

	@Override
	public int getRowCount() {
		return persons.size();
	}

	@Override
	public int getColumnCount() {
		return Lab4Task33Filed.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return Lab4Task33Filed.values()[columnIndex].getColumnName();
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
		Lab4Task33 person = persons.get(rowIndex);
		return Lab4Task33Filed.values()[columnIndex].getValue(person);
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
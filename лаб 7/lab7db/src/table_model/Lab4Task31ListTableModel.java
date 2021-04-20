package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Lab4Task31;

public class Lab4Task31ListTableModel implements TableModel{
	private List<Lab4Task31> persons = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum Lab4Task31Filed {
		SURNAME("Surname") {
			@Override
			public String getValue(Lab4Task31 person) {
				return person.getSurname();
			}
		},
		NAME("Name") {
			@Override
			public String getValue(Lab4Task31 person) {
				return person.getName();
			}
		},
		MIDDLE_NAME("Middle name") {
			@Override
			public String getValue(Lab4Task31 person) {
				return person.getMiddleName();
			}
		},
		NUMBER("Number") {
			@Override
			public String getValue(Lab4Task31 person) {
				return person.getNumber();
			}
		};

		private final String columnName;

		private Lab4Task31Filed(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Lab4Task31 person);
	}

	public void setPersons(List<Lab4Task31> persons) {
		this.persons = persons;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Lab4Task31 getPerson(int index) {
		return persons.get(index);
	}

	@Override
	public int getRowCount() {
		return persons.size();
	}

	@Override
	public int getColumnCount() {
		return Lab4Task31Filed.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return Lab4Task31Filed.values()[columnIndex].getColumnName();
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
		Lab4Task31 person = persons.get(rowIndex);
		return Lab4Task31Filed.values()[columnIndex].getValue(person);
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
package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.ContactPerson;

public class ContactPersonListTableModel implements TableModel{
	private List<ContactPerson> persons = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum PersonFiled {
		ID("ID") {
			@Override
			public String getValue(ContactPerson person) {
				return String.format("%03d", person.getId());
			}
		},
		SURNAME("Surname") {
			@Override
			public String getValue(ContactPerson person) {
				return person.getSurname();
			}
		},
		NAME("Name") {
			@Override
			public String getValue(ContactPerson person) {
				return person.getName();
			}
		},
		MIDDLE_NAME("Middle name") {
			@Override
			public String getValue(ContactPerson person) {
				return person.getMiddleName();
			}
		};

		private final String columnName;

		private PersonFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(ContactPerson person);
	}

	public void setPersons(List<ContactPerson> persons) {
		this.persons = persons;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public ContactPerson getPerson(int index) {
		return persons.get(index);
	}

	@Override
	public int getRowCount() {
		return persons.size();
	}

	@Override
	public int getColumnCount() {
		return PersonFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return PersonFiled.values()[columnIndex].getColumnName();
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
		ContactPerson person = persons.get(rowIndex);
		return PersonFiled.values()[columnIndex].getValue(person);
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
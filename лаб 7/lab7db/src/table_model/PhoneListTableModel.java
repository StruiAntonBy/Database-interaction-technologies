package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Phone;

public class PhoneListTableModel implements TableModel {
	private List<Phone> phones = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum PhoneFiled {
		ID("ID") {
			@Override
			public String getValue(Phone phone) {
				return String.format("%03d", phone.getId());
			}
		},
		NUMBER("Number") {
			@Override
			public String getValue(Phone phone) {
				return phone.getNumber();
			}
		},
		CONTACT_PERSON_ID("Contact person id") {
			@Override
			public String getValue(Phone phone) {
				return String.format("%03d", phone.getContactPersonId());
			}
		};

		private final String columnName;

		private PhoneFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Phone phone);
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Phone getPhone(int index) {
		return phones.get(index);
	}

	@Override
	public int getRowCount() {
		return phones.size();
	}

	@Override
	public int getColumnCount() {
		return PhoneFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return PhoneFiled.values()[columnIndex].getColumnName();
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
		Phone phone = phones.get(rowIndex);
		return PhoneFiled.values()[columnIndex].getValue(phone);
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
package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Email;

public class EmailListTableModel implements TableModel {
	private List<Email> emails = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum EmailFiled {
		ID("ID") {
			@Override
			public String getValue(Email email) {
				return String.format("%03d", email.getId());
			}
		},
		LOGIN("Login") {
			@Override
			public String getValue(Email email) {
				return email.getLogin();
			}
		},
		CONTACT_PERSON_ID("Contact person id") {
			@Override
			public String getValue(Email email) {
				return String.format("%03d", email.getContactPersonId());
			}
		};

		private final String columnName;

		private EmailFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Email email);
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Email getEmail(int index) {
		return emails.get(index);
	}

	@Override
	public int getRowCount() {
		return emails.size();
	}

	@Override
	public int getColumnCount() {
		return EmailFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return EmailFiled.values()[columnIndex].getColumnName();
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
		Email email = emails.get(rowIndex);
		return EmailFiled.values()[columnIndex].getValue(email);
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
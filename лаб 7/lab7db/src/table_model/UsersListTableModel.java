package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Users;

public class UsersListTableModel implements TableModel {
	private List<Users> users = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum UserFiled {
		ID("ID") {
			@Override
			public String getValue(Users user) {
				return String.format("%03d", user.getId());
			}
		},
		LOGIN("Login") {
			@Override
			public String getValue(Users user) {
				return user.getLogin();
			}
		},
		PASSWORD("Password") {
			@Override
			public String getValue(Users user) {
				return user.getPassword();
			}
		},
		ROLE("Role") {
			@Override
			public String getValue(Users user) {
				return Integer.toString(user.getRole());
			}
		};

		private final String columnName;

		private UserFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Users user);
	}

	public void setUsers(List<Users> users) {
		this.users = users;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Users getUser(int index) {
		return users.get(index);
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {
		return UserFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return UserFiled.values()[columnIndex].getColumnName();
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
		Users user = users.get(rowIndex);
		return UserFiled.values()[columnIndex].getValue(user);
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

package table_model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Client;

public class ClientListTableModel implements TableModel {
	private List<Client> clients = new ArrayList<>();
	private List<TableModelListener> listeners = new ArrayList<>();
	
	private static enum ClientFiled {
		ID("ID") {
			@Override
			public String getValue(Client client) {
				return String.format("%03d", client.getId());
			}
		},
		NAME("Name") {
			@Override
			public String getValue(Client client) {
				return client.getName();
			}
		},
		REGISTERED_ADDRESS("Registered address") {
			@Override
			public String getValue(Client client) {
				return client.getRegisteredAddress();
			}
		},
		BANK_DETAILS("Bank details") {
			@Override
			public String getValue(Client client) {
				return client.getBankDetails();
			}
		};

		private final String columnName;

		private ClientFiled(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public abstract String getValue(Client client);
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
		TableModelEvent event = new TableModelEvent(this);
		for(TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

	public Client getClient(int index) {
		return clients.get(index);
	}

	@Override
	public int getRowCount() {
		return clients.size();
	}

	@Override
	public int getColumnCount() {
		return ClientFiled.values().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return ClientFiled.values()[columnIndex].getColumnName();
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
		Client client = clients.get(rowIndex);
		return ClientFiled.values()[columnIndex].getValue(client);
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

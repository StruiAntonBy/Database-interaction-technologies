package pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.ClientDao;
import domain.Client;

public class ClientDaoSqlImpl implements ClientDao{
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Client client) throws DaoException {
		String sql = "INSERT INTO \"client\"(\"name\", \"registered_address\", \"bank_details\") VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, client.getName());
			statement.setString(2, client.getRegisteredAddress());
			statement.setString(3, client.getBankDetails());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getLong(1);
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public Client read(Long id) throws DaoException {
		String sql = "SELECT \"name\", \"registered_address\", \"bank_details\" FROM \"client\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Client client = null;
			if(resultSet.next()) {
				client = new Client();
				client.setId(id);
				client.setName(resultSet.getString("name"));
				client.setRegisteredAddress(resultSet.getString("registered_address"));
				client.setBankDetails(resultSet.getString("bank_details"));
			}
			return client;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Client client) throws DaoException {
		String sql = "UPDATE \"client\" SET \"name\" = ?, \"registered_address\" = ?, \"bank_details\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, client.getName());
			statement.setString(2, client.getRegisteredAddress());
			statement.setString(3, client.getBankDetails());
			statement.setLong(4, client.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"client\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}
	
	@Override
	public List<Client> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"name\", \"registered_address\", \"bank_details\" FROM \"client\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Client> clients = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Client client = new Client();
				client.setId(resultSet.getLong("id"));
				client.setName(resultSet.getString("name"));
				client.setRegisteredAddress(resultSet.getString("registered_address"));
				client.setBankDetails(resultSet.getString("bank_details"));
				clients.add(client);
			}
			return clients;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}

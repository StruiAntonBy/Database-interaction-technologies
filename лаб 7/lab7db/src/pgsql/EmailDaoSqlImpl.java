package pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.EmailDao;
import domain.Email;

public class EmailDaoSqlImpl implements EmailDao{
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Email email) throws DaoException {
		String sql = "INSERT INTO \"e-mail\"(\"login\", \"contact_person_id\") VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, email.getLogin());
			statement.setLong(2, email.getContactPersonId());
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
	public Email read(Long id) throws DaoException {
		String sql = "SELECT \"login\", \"contact_person_id\" FROM \"e-mail\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Email email = null;
			if(resultSet.next()) {
				email = new Email();
				email.setId(id);
				email.setLogin(resultSet.getString("login"));
				email.setContactPersonId(resultSet.getLong("contact_person_id"));
			}
			return email;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Email email) throws DaoException {
		String sql = "UPDATE \"e-mail\" SET \"login\" = ?, \"contact_person_id\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, email.getLogin());
			statement.setLong(2, email.getContactPersonId());
			statement.setLong(3, email.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"e-mail\" WHERE \"id\" = ?";
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
	public List<Email> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"login\", \"contact_person_id\" FROM \"e-mail\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Email> emails = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Email email = new Email();
				email.setId(resultSet.getLong("id"));
				email.setLogin(resultSet.getString("login"));
				email.setContactPersonId(resultSet.getLong("contact_person_id"));
				emails.add(email);
			}
			return emails;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Email> readAllEmailAddressesClient(Long clientId) throws DaoException {
		String sql = "SELECT \"id\", \"login\", \"contact_person_id\" FROM \"e-mail\" WHERE \"contact_person_id\" = " + clientId;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Email> emails = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Email email = new Email();
				email.setId(resultSet.getLong("id"));
				email.setLogin(resultSet.getString("login"));
				email.setContactPersonId(resultSet.getLong("contact_person_id"));
				emails.add(email);
			}
			return emails;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}

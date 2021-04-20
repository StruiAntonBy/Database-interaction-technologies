package pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.PhoneDao;
import domain.Phone;

public class PhoneDaoSqlImpl implements PhoneDao{
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Phone phone) throws DaoException {
		String sql = "INSERT INTO \"phone\"(\"number\", \"contact_person_id\") VALUES (?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, phone.getNumber());
			statement.setLong(2, phone.getContactPersonId());
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
	public Phone read(Long id) throws DaoException {
		String sql = "SELECT \"number\", \"contact_person_id\" FROM \"phone\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Phone phone = null;
			if(resultSet.next()) {
				phone = new Phone();
				phone.setId(id);
				phone.setNumber(resultSet.getString("number"));
				phone.setContactPersonId(resultSet.getLong("contact_person_id"));
			}
			return phone;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Phone phone) throws DaoException {
		String sql = "UPDATE \"phone\" SET \"number\" = ?, \"contact_person_id\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, phone.getNumber());
			statement.setLong(2, phone.getContactPersonId());
			statement.setLong(3, phone.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"phone\" WHERE \"id\" = ?";
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
	public List<Phone> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"number\", \"contact_person_id\" FROM \"phone\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Phone> phones = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Phone phone = new Phone();
				phone.setId(resultSet.getLong("id"));
				phone.setNumber(resultSet.getString("number"));
				phone.setContactPersonId(resultSet.getLong("contact_person_id"));
				phones.add(phone);
			}
			return phones;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}

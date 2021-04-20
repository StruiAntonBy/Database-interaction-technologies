package pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.ContactPersonDao;
import domain.ContactPerson;
import domain.Lab4Task31;
import domain.Lab4Task32;
import domain.Lab4Task33;

public class ContactPersonDaoSqlImpl implements ContactPersonDao{
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(ContactPerson person) throws DaoException {
		String sql = "INSERT INTO \"contact_person\"(\"surname\", \"name\", \"middle_name\") VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, person.getSurname());
			statement.setString(2, person.getName());
			statement.setString(3, person.getMiddleName());
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
	public ContactPerson read(Long id) throws DaoException {
		String sql = "SELECT \"surname\", \"name\", \"middle_name\" FROM \"contact_person\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			ContactPerson person = null;
			if(resultSet.next()) {
				person = new ContactPerson();
				person.setId(id);
				person.setSurname(resultSet.getString("surname"));
				person.setName(resultSet.getString("name"));
				person.setMiddleName(resultSet.getString("middle_name"));
			}
			return person;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(ContactPerson person) throws DaoException {
		String sql = "UPDATE \"contact_person\" SET \"surname\" = ?, \"name\" = ?, \"middle_name\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, person.getSurname());
			statement.setString(2, person.getName());
			statement.setString(3, person.getMiddleName());
			statement.setLong(4, person.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"contact_person\" WHERE \"id\" = ?";
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
	public List<ContactPerson> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"surname\", \"name\", \"middle_name\" FROM \"contact_person\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<ContactPerson> persons = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				ContactPerson person = new ContactPerson();
				person.setId(resultSet.getLong("id"));
				person.setSurname(resultSet.getString("surname"));
				person.setName(resultSet.getString("name"));
				person.setMiddleName(resultSet.getString("middle_name"));
				persons.add(person);
			}
			return persons;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Lab4Task31> readContactPersonNumbers() throws DaoException {
		String sql = "select contact_person.surname, contact_person.name, contact_person.middle_name, phone.number from contact_person INNER JOIN phone ON phone.contact_person_id=contact_person.id";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Lab4Task31> persons = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Lab4Task31 person = new Lab4Task31();
				person.setSurname(resultSet.getString("surname"));
				person.setName(resultSet.getString("name"));
				person.setMiddleName(resultSet.getString("middle_name"));
				person.setNumber(resultSet.getString("number"));
				persons.add(person);
			}
			return persons;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Lab4Task32> readContactPersonLogins() throws DaoException {
		String sql = "select contact_person.surname, contact_person.name, contact_person.middle_name, \"e-mail\".login from contact_person INNER JOIN \"e-mail\" ON \"e-mail\".contact_person_id=contact_person.id";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Lab4Task32> persons = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Lab4Task32 person = new Lab4Task32();
				person.setSurname(resultSet.getString("surname"));
				person.setName(resultSet.getString("name"));
				person.setMiddleName(resultSet.getString("middle_name"));
				person.setLogin(resultSet.getString("login"));
				persons.add(person);
			}
			return persons;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Lab4Task33> readContactPersonWorkExperiences() throws DaoException {
		String sql = "select software_tester.surname, software_tester.name, software_tester.middle_name, software_tester.work_experience from software_tester LEFT JOIN users ON users.id=software_tester.id";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Lab4Task33> persons = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Lab4Task33 person = new Lab4Task33();
				person.setSurname(resultSet.getString("surname"));
				person.setName(resultSet.getString("name"));
				person.setMiddleName(resultSet.getString("middle_name"));
				person.setWorkExperience(resultSet.getInt("work_experience"));
				persons.add(person);
			}
			return persons;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}

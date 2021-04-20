package pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.SoftwareTesterDao;
import domain.SoftwareTester;

public class SoftwareTesterDaoSqlImpl implements SoftwareTesterDao{
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(SoftwareTester tester) throws DaoException {
		String sql = "INSERT INTO \"software_tester\"(\"surname\", \"name\", \"middle_name\", \"work_experience\") VALUES (?, ?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, tester.getSurname());
			statement.setString(2, tester.getName());
			statement.setString(3, tester.getMiddleName());
			statement.setInt(4, tester.getWorkExperience());
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
	public SoftwareTester read(Long id) throws DaoException {
		String sql = "SELECT \"surname\", \"name\", \"middle_name\", \"work_experience\" FROM \"software_tester\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			SoftwareTester tester = null;
			if(resultSet.next()) {
				tester = new SoftwareTester();
				tester.setId(id);
				tester.setSurname(resultSet.getString("surname"));
				tester.setName(resultSet.getString("name"));
				tester.setMiddleName(resultSet.getString("middle_name"));
				tester.setWorkExperience(resultSet.getInt("work_experience"));
			}
			return tester;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(SoftwareTester tester) throws DaoException {
		String sql = "UPDATE \"software_tester\" SET \"surname\" = ?, \"name\" = ?, \"middle_name\" = ?, \"work_experience\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, tester.getSurname());
			statement.setString(2, tester.getName());
			statement.setString(3, tester.getMiddleName());
			statement.setInt(4, tester.getWorkExperience());
			statement.setLong(5, tester.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"software_tester\" WHERE \"id\" = ?";
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
	public List<SoftwareTester> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"surname\", \"name\", \"middle_name\", \"work_experience\" FROM \"software_tester\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<SoftwareTester> testers = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				SoftwareTester tester = new SoftwareTester();
				tester.setId(resultSet.getLong("id"));
				tester.setSurname(resultSet.getString("surname"));
				tester.setName(resultSet.getString("name"));
				tester.setMiddleName(resultSet.getString("middle_name"));
				tester.setWorkExperience(resultSet.getInt("work_experience"));
				testers.add(tester);
			}
			return testers;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}

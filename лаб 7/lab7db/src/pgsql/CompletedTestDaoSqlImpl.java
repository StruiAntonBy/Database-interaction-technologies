package pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.CompletedTestDao;
import domain.CompletedTest;
import domain.Lab4Task41;
import domain.Lab4Task42;

public class CompletedTestDaoSqlImpl implements CompletedTestDao{
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(CompletedTest test) throws DaoException {
		String sql = "INSERT INTO \"completed_test\"(\"tester_id\", \"planed_test_id\", \"start_date_and_time\", \"length\", \"result\") VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setLong(1, test.getTesterId());
			statement.setLong(2, test.getPlanedTestId());
			statement.setDate(3, java.sql.Date.valueOf(test.getStartDateAndTime()));
			statement.setInt(4, test.getLength());
			statement.setString(5, test.getResult());
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
	public CompletedTest read(Long id) throws DaoException {
		String sql = "SELECT \"tester_id\", \"planed_test_id\", \"start_date_and_time\", \"length\", \"result\" FROM \"completed_test\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			CompletedTest test = null;
			if(resultSet.next()) {
				test = new CompletedTest();
				test.setId(id);
				test.setTesterId(resultSet.getLong("tester_id"));
				test.setPlanedTestId(resultSet.getLong("planed_test_id"));
				test.setStartDateAndTime(resultSet.getString("start_date_and_time"));
				test.setLength(resultSet.getInt("length"));
				test.setResult(resultSet.getString("result"));
			}
			return test;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(CompletedTest test) throws DaoException {
		String sql = "UPDATE \"completed_test\" SET \"tester_id\" = ?, \"planed_test_id\" = ?, \"start_date_and_time\" = ?, \"length\" = ?, \"result\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, test.getTesterId());
			statement.setLong(2, test.getPlanedTestId());
			statement.setDate(3, java.sql.Date.valueOf(test.getStartDateAndTime()));
			statement.setInt(4, test.getLength());
			statement.setString(5, test.getResult());
			statement.setLong(6, test.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"completed_test\" WHERE \"id\" = ?";
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
	public List<CompletedTest> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"tester_id\", \"planed_test_id\", \"start_date_and_time\", \"length\", \"result\" FROM \"completed_test\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<CompletedTest> tests = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				CompletedTest test = new CompletedTest();
				test.setId(resultSet.getLong("id"));
				test.setTesterId(resultSet.getLong("tester_id"));
				test.setPlanedTestId(resultSet.getLong("planed_test_id"));
				test.setStartDateAndTime(resultSet.getString("start_date_and_time"));
				test.setLength(resultSet.getInt("length"));
				test.setResult(resultSet.getString("result"));
				tests.add(test);
			}
			return tests;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<CompletedTest> readAllCompletedTestsSpecifiedPlanedTest(Long planedTestId) throws DaoException {
		String sql = "SELECT \"id\", \"tester_id\", \"planed_test_id\", \"start_date_and_time\", \"length\", \"result\" FROM \"completed_test\" where planed_test_id= "+planedTestId+" ORDER BY result DESC,start_date_and_time";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<CompletedTest> tests = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				CompletedTest test = new CompletedTest();
				test.setId(resultSet.getLong("id"));
				test.setTesterId(resultSet.getLong("tester_id"));
				test.setPlanedTestId(resultSet.getLong("planed_test_id"));
				test.setStartDateAndTime(resultSet.getString("start_date_and_time"));
				test.setLength(resultSet.getInt("length"));
				test.setResult(resultSet.getString("result"));
				tests.add(test);
			}
			return tests;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Lab4Task41> readPlanedTestIdCount() throws DaoException {
		String sql = "select planed_test_id, count(*) as idcount from completed_test GROUP BY planed_test_id";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Lab4Task41> tests = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Lab4Task41 test = new Lab4Task41();
				test.setPlanedTestId(resultSet.getLong("planed_test_id"));
				test.setIdCount(resultSet.getInt("idcount"));
				tests.add(test);
			}
			return tests;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Lab4Task42> sumLengthTesterId() throws DaoException {
		String sql = "select tester_id, sum(length) as sumlength from completed_test GROUP BY tester_id";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Lab4Task42> tests = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Lab4Task42 test = new Lab4Task42();
				test.setTesterId(resultSet.getLong("tester_id"));
				test.setSumLength(resultSet.getInt("sumlength"));
				tests.add(test);
			}
			return tests;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}

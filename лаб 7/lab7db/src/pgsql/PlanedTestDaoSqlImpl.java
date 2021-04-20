package pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.PlanedTestDao;
import domain.PlanedTest;

public class PlanedTestDaoSqlImpl implements PlanedTestDao{
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(PlanedTest test) throws DaoException {
		String sql = "INSERT INTO \"planed_test\"(\"requirement_id\", \"description_of_the_performance\", \"expected_result\", \"planned_time\", \"level_test\") VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setLong(1, test.getRequirementId());
			statement.setString(2, test.getDescriptionOfThePerformance());
			statement.setString(3, test.getExpectedResult());
			statement.setInt(4, test.getPlannedTime());
			statement.setString(5, test.getLevelTest());
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
	public PlanedTest read(Long id) throws DaoException {
		String sql = "SELECT \"requirement_id\", \"description_of_the_performance\", \"expected_result\", \"planned_time\", \"level_test\" FROM \"planed_test\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			PlanedTest test = null;
			if(resultSet.next()) {
				test = new PlanedTest();
				test.setId(id);
				test.setRequirementId(resultSet.getLong("requirement_id"));
				test.setDescriptionOfThePerformance(resultSet.getString("description_of_the_performance"));
				test.setExpectedResult(resultSet.getString("expected_result"));
				test.setPlannedTime(resultSet.getInt("planned_time"));
				test.setLevelTest(resultSet.getString("level_test"));
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
	public void update(PlanedTest test) throws DaoException {
		String sql = "UPDATE \"planed_test\" SET \"requirement_id\" = ?, \"description_of_the_performance\" = ?, \"expected_result\" = ?, \"planned_time\" = ?, \"level_test\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, test.getRequirementId());
			statement.setString(2, test.getDescriptionOfThePerformance());
			statement.setString(3, test.getExpectedResult());
			statement.setInt(4, test.getPlannedTime());
			statement.setString(5, test.getLevelTest());
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
		String sql = "DELETE FROM \"planed_test\" WHERE \"id\" = ?";
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
	public List<PlanedTest> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"requirement_id\", \"description_of_the_performance\", \"expected_result\", \"planned_time\", \"level_test\" FROM \"planed_test\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<PlanedTest> tests = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				PlanedTest test = new PlanedTest();
				test.setId(resultSet.getLong("id"));
				test.setRequirementId(resultSet.getLong("requirement_id"));
				test.setDescriptionOfThePerformance(resultSet.getString("description_of_the_performance"));
				test.setExpectedResult(resultSet.getString("expected_result"));
				test.setPlannedTime(resultSet.getInt("planned_time"));
				test.setLevelTest(resultSet.getString("level_test"));
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
	public List<PlanedTest> readScheduledDeepTests(Long requirementId) throws DaoException {
		String sql = "SELECT \"id\", \"requirement_id\", \"description_of_the_performance\", \"expected_result\", \"planned_time\", \"level_test\" FROM \"planed_test\" where requirement_id= "+requirementId+" and level_test='глубокий' ORDER BY planned_time";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<PlanedTest> tests = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				PlanedTest test = new PlanedTest();
				test.setId(resultSet.getLong("id"));
				test.setRequirementId(resultSet.getLong("requirement_id"));
				test.setDescriptionOfThePerformance(resultSet.getString("description_of_the_performance"));
				test.setExpectedResult(resultSet.getString("expected_result"));
				test.setPlannedTime(resultSet.getInt("planned_time"));
				test.setLevelTest(resultSet.getString("level_test"));
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

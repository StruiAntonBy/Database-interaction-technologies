package pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.RequirementDao;
import domain.Requirement;

public class RequirementDaoSqlImpl implements RequirementDao{
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Requirement requirement) throws DaoException {
		String sql = "INSERT INTO \"requirement\"(\"project_id\", \"requirement\", \"start_date\", \"planned_time\", \"the_priority_of\", \"level_of_criticality_for_the_client\", \"a_mark_of_completion\", \"the_probability_of_a_change\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setLong(1, requirement.getProjectId());
			statement.setString(2, requirement.getRequirement());
			if(requirement.getStartDate().length()==0) {
				statement.setDate(3, null);
			}
			else {
				statement.setDate(3, java.sql.Date.valueOf(requirement.getStartDate()));
			}
			statement.setInt(4, requirement.getPlannedTime());
			statement.setString(5, requirement.getThePriorityOf());
			statement.setString(6, requirement.getLevelOfCriticalityForTheClient());
			statement.setString(7, requirement.getMarkOfCompletion());
			statement.setString(8, requirement.getTheProbabilityOfChange());
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
	public Requirement read(Long id) throws DaoException {
		String sql = "SELECT \"project_id\", \"requirement\", \"start_date\", \"planned_time\", \"the_priority_of\", \"level_of_criticality_for_the_client\", \"a_mark_of_completion\", \"the_probability_of_a_change\" FROM \"requirement\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Requirement requirement = null;
			if(resultSet.next()) {
				requirement = new Requirement();
				requirement.setId(id);
				requirement.setProjectId(resultSet.getLong("project_id"));
				requirement.setRequirement(resultSet.getString("requirement"));
				requirement.setStartDate(resultSet.getString("start_date"));
				requirement.setPlannedTime(resultSet.getInt("planned_time"));
				requirement.setThePriorityOf(resultSet.getString("the_priority_of"));
				requirement.setLevelOfCriticalityForTheClient(resultSet.getString("level_of_criticality_for_the_client"));
				requirement.setMarkOfCompletion(resultSet.getString("a_mark_of_completion"));
				requirement.setTheProbabilityOfChange(resultSet.getString("the_probability_of_a_change"));
			}
			return requirement;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Requirement requirement) throws DaoException {
		String sql = "UPDATE \"requirement\" SET \"project_id\" = ?, \"requirement\" = ?, \"start_date\" = ?, \"planned_time\" = ?, \"the_priority_of\" = ?, \"level_of_criticality_for_the_client\" = ?, \"a_mark_of_completion\" = ?, \"the_probability_of_a_change\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, requirement.getProjectId());
			statement.setString(2, requirement.getRequirement());
			if(requirement.getStartDate().length()==0) {
				statement.setDate(3, null);
			}
			else {
				statement.setDate(3, java.sql.Date.valueOf(requirement.getStartDate()));
			}
			statement.setInt(4, requirement.getPlannedTime());
			statement.setString(5, requirement.getThePriorityOf());
			statement.setString(6, requirement.getLevelOfCriticalityForTheClient());
			statement.setString(7, requirement.getMarkOfCompletion());
			statement.setString(8, requirement.getTheProbabilityOfChange());
			statement.setLong(9, requirement.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"requirement\" WHERE \"id\" = ?";
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
	public List<Requirement> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"project_id\", \"requirement\", \"start_date\", \"planned_time\", \"the_priority_of\", \"level_of_criticality_for_the_client\", \"a_mark_of_completion\", \"the_probability_of_a_change\" FROM \"requirement\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Requirement> requirements= new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Requirement requirement = new Requirement();
				requirement.setId(resultSet.getLong("id"));
				requirement.setProjectId(resultSet.getLong("project_id"));
				requirement.setRequirement(resultSet.getString("requirement"));
				requirement.setStartDate(resultSet.getString("start_date"));
				requirement.setPlannedTime(resultSet.getInt("planned_time"));
				requirement.setThePriorityOf(resultSet.getString("the_priority_of"));
				requirement.setLevelOfCriticalityForTheClient(resultSet.getString("level_of_criticality_for_the_client"));
				requirement.setMarkOfCompletion(resultSet.getString("a_mark_of_completion"));
				requirement.setTheProbabilityOfChange(resultSet.getString("the_probability_of_a_change"));
				requirements.add(requirement);
			}
			return requirements;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Requirement> readSuccessfullyImplementedRequirements(Long clientId) throws DaoException {
		String sql = "select requirement.id, requirement.project_id, requirement.requirement, requirement.start_date, requirement.a_mark_of_completion from requirement, client, project where client.id= "+clientId+" and project.client_id=client.id and project.id=requirement.project_id ORDER BY start_date";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Requirement> requirements= new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Requirement requirement = new Requirement();
				requirement.setId(resultSet.getLong("id"));
				requirement.setProjectId(resultSet.getLong("project_id"));
				requirement.setRequirement(resultSet.getString("requirement"));
				requirement.setStartDate(resultSet.getString("start_date"));
				requirement.setMarkOfCompletion(resultSet.getString("a_mark_of_completion"));
				requirements.add(requirement);
			}
			return requirements;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}

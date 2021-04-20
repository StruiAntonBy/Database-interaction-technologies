package pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.ProjectDao;
import domain.Lab4Task22;
import domain.Project;

public class ProjectDaoSqlImpl implements ProjectDao{
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Long create(Project project) throws DaoException {
		String sql = "INSERT INTO \"project\"(\"client_id\", \"start_date\", \"end_date\") VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setLong(1, project.getClientId());
			statement.setDate(2, java.sql.Date.valueOf(project.getStartDate()));
			if(project.getEndDate().length()==0) {
				statement.setDate(3, null);
			}
			else {
				statement.setDate(3, java.sql.Date.valueOf(project.getEndDate()));
			}
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
	public Project read(Long id) throws DaoException {
		String sql = "SELECT \"client_id\", \"start_date\", \"end_date\" FROM \"project\" WHERE \"id\" = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Project project = null;
			if(resultSet.next()) {
				project = new Project();
				project.setId(id);
				project.setClientId(resultSet.getLong("client_id"));
				project.setStartDate(resultSet.getString("start_date"));
				project.setEndDate(resultSet.getString("end_date"));
			}
			return project;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void update(Project project) throws DaoException {
		String sql = "UPDATE \"project\" SET \"client_id\" = ?, \"start_date\" = ?, \"end_date\" = ? WHERE \"id\" = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, project.getClientId());
			statement.setDate(2, java.sql.Date.valueOf(project.getStartDate()));
			if(project.getEndDate().length()==0) {
				statement.setDate(3, null);
			}
			else {
				statement.setDate(3, java.sql.Date.valueOf(project.getEndDate()));
			}
			statement.setLong(4, project.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		String sql = "DELETE FROM \"project\" WHERE \"id\" = ?";
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
	public List<Project> readAll() throws DaoException {
		String sql = "SELECT \"id\", \"client_id\", \"start_date\", \"end_date\" FROM \"project\"";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Project> projects = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getLong("id"));
				project.setClientId(resultSet.getLong("client_id"));
				project.setStartDate(resultSet.getString("start_date"));
				project.setEndDate(resultSet.getString("end_date"));
				projects.add(project);
			}
			return projects;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}

	@Override
	public List<Lab4Task22> readUnfinishedProjectsClient(Long clientId) throws DaoException {
		String sql = "select project.id, project.client_id, project.start_date, project.end_date,(select count(requirement) from requirement where project.id=requirement.project_id) as all_requirement, (select count(level_of_criticality_for_the_client) from requirement where project.id=requirement.project_id and requirement.level_of_criticality_for_the_client='низкий') as requirement_low, (select count(level_of_criticality_for_the_client) from requirement where project.id=requirement.project_id and requirement.level_of_criticality_for_the_client='средний') as requirement_middle, (select count(level_of_criticality_for_the_client) from requirement where project.id=requirement.project_id and requirement.level_of_criticality_for_the_client='высокий') as requirement_high, (select count(level_of_criticality_for_the_client) from requirement where project.id=requirement.project_id and requirement.level_of_criticality_for_the_client='высокий')::real / (select count(requirement) from requirement where project.id=requirement.project_id)::real as percent_high, (select count(level_of_criticality_for_the_client) from requirement where project.id=requirement.project_id and requirement.level_of_criticality_for_the_client='средний')::real / (select count(requirement) from requirement where project.id=requirement.project_id)::real as percent_middle, (select count(level_of_criticality_for_the_client) from requirement where project.id=requirement.project_id and requirement.level_of_criticality_for_the_client='низкий')::real / (select count(requirement) from requirement where project.id=requirement.project_id)::real as percent_low from project where project.client_id= "+clientId+" and project.end_date IS NULL ORDER BY percent_high DESC, percent_middle DESC, percent_low DESC";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			List<Lab4Task22> projects = new ArrayList<>();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				Lab4Task22 project = new Lab4Task22();
				project.setId(resultSet.getLong("id"));
				project.setClientId(resultSet.getLong("client_id"));
				project.setStartDate(resultSet.getString("start_date"));
				project.setEndDate(resultSet.getString("end_date"));
				project.setAllRequirement(resultSet.getInt("all_requirement"));
				project.setRequirementLow(resultSet.getInt("requirement_low"));
				project.setRequirementMiddle(resultSet.getInt("requirement_middle"));
				project.setRequirementHigh(resultSet.getInt("requirement_high"));
				project.setPercentHigh(resultSet.getDouble("percent_high"));
				project.setPercentMiddle(resultSet.getDouble("percent_middle"));
				project.setPercentLow(resultSet.getDouble("percent_low"));
				projects.add(project);
			}
			return projects;
		} catch(SQLException e) {
			throw new DaoException(e);
		} finally {
			try { resultSet.close(); } catch (Exception e) {}
			try { statement.close(); } catch (Exception e) {}
		}
	}
}

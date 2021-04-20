package dao;

import java.util.List;

import domain.Requirement;

public interface RequirementDao extends Dao<Requirement>{
	List<Requirement> readSuccessfullyImplementedRequirements(Long clientId) throws DaoException;
}

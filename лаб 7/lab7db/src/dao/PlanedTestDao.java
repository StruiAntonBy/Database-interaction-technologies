package dao;

import java.util.List;

import domain.PlanedTest;

public interface PlanedTestDao extends Dao<PlanedTest>{
	List<PlanedTest> readScheduledDeepTests(Long requirementId) throws DaoException;
}

package dao;

import java.util.List;

import domain.CompletedTest;
import domain.Lab4Task41;
import domain.Lab4Task42;

public interface CompletedTestDao extends Dao<CompletedTest>{
	List<CompletedTest> readAllCompletedTestsSpecifiedPlanedTest(Long planedTestId) throws DaoException;
	
	List<Lab4Task41> readPlanedTestIdCount() throws DaoException;
	
	List<Lab4Task42> sumLengthTesterId() throws DaoException;
}

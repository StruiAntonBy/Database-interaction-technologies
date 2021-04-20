package service;

import java.util.List;

import domain.CompletedTest;
import domain.Lab4Task41;
import domain.Lab4Task42;

public interface CompletedTestService extends Service<CompletedTest>{
	CompletedTest getCompletedTest(Long id) throws ServiceException;
	
	List<CompletedTest> getAllCompletedTestsSpecifiedPlanedTest(Long planedTestId) throws ServiceException;
	
	List<Lab4Task41> getPlanedTestIdCount() throws ServiceException;
	
	List<Lab4Task42> getSumLengthTesterId() throws ServiceException;
}

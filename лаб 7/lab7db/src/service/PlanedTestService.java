package service;

import java.util.List;

import domain.PlanedTest;

public interface PlanedTestService extends Service<PlanedTest>{
	PlanedTest getPlanedTest(Long id) throws ServiceException;
	
	List<PlanedTest> getScheduledDeepTests(Long requirementId) throws ServiceException;
}

package service;

import java.util.List;

import domain.Requirement;

public interface RequirementService extends Service<Requirement>{
	Requirement getRequirement(Long id) throws ServiceException;
	
	List<Requirement> getSuccessfullyImplementedRequirements(Long clientId) throws ServiceException;
}
package service;

import java.util.List;

import domain.Lab4Task22;
import domain.Project;

public interface ProjectService extends Service<Project>{
	Project getProject(Long id) throws ServiceException;
	
	List<Lab4Task22> getUnfinishedProjectsClient(Long clientId) throws ServiceException;
}

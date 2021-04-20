package serviceImpl;

import java.util.List;

import dao.DaoException;
import dao.ProjectDao;
import domain.Lab4Task22;
import domain.Project;
import service.ProjectService;
import service.ServiceException;

public class ProjectServiceImpl implements ProjectService{
	private ProjectDao projectDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public List<Project> getAll() throws ServiceException {
		try {
			return projectDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Project project) throws ServiceException {
		try {
			if(project.getId() != null) {
				projectDao.update(project);
			} else {
				Long id = projectDao.create(project);
				project.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			projectDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Project getProject(Long id) throws ServiceException {
		try {
			return projectDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Lab4Task22> getUnfinishedProjectsClient(Long clientId) throws ServiceException {
		try {
			return projectDao.readUnfinishedProjectsClient(clientId);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

package serviceImpl;

import java.util.List;

import dao.DaoException;
import dao.RequirementDao;
import domain.Requirement;
import service.RequirementService;
import service.ServiceException;

public class RequirementServiceImpl implements RequirementService{
	private RequirementDao requirementDao;

	public void setRequirementDao(RequirementDao requirementDao) {
		this.requirementDao = requirementDao;
	}

	@Override
	public List<Requirement> getAll() throws ServiceException {
		try {
			return requirementDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Requirement requirement) throws ServiceException {
		try {
			if(requirement.getId() != null) {
				requirementDao.update(requirement);
			} else {
				Long id = requirementDao.create(requirement);
				requirement.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			requirementDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Requirement getRequirement(Long id) throws ServiceException {
		try {
			return requirementDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Requirement> getSuccessfullyImplementedRequirements(Long clientId) throws ServiceException {
		try {
			return requirementDao.readSuccessfullyImplementedRequirements(clientId);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

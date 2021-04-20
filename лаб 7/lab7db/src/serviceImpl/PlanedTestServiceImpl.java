package serviceImpl;

import java.util.List;

import dao.DaoException;
import dao.PlanedTestDao;
import domain.PlanedTest;
import service.PlanedTestService;
import service.ServiceException;

public class PlanedTestServiceImpl implements PlanedTestService{
	private PlanedTestDao testDao;

	public void setPlanedTestDao(PlanedTestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public List<PlanedTest> getAll() throws ServiceException {
		try {
			return testDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(PlanedTest test) throws ServiceException {
		try {
			if(test.getId() != null) {
				testDao.update(test);
			} else {
				Long id = testDao.create(test);
				test.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			testDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public PlanedTest getPlanedTest(Long id) throws ServiceException {
		try {
			return testDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<PlanedTest> getScheduledDeepTests(Long requirementId) throws ServiceException {
		try {
			return testDao.readScheduledDeepTests(requirementId);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

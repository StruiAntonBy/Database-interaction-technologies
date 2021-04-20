package serviceImpl;

import java.util.List;

import dao.CompletedTestDao;
import dao.DaoException;
import domain.CompletedTest;
import domain.Lab4Task41;
import domain.Lab4Task42;
import service.CompletedTestService;
import service.ServiceException;

public class CompletedTestServiceImpl implements CompletedTestService{
	private CompletedTestDao testDao;

	public void setCompletedTestDao(CompletedTestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public List<CompletedTest> getAll() throws ServiceException {
		try {
			return testDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(CompletedTest test) throws ServiceException {
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
	public CompletedTest getCompletedTest(Long id) throws ServiceException {
		try {
			return testDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<CompletedTest> getAllCompletedTestsSpecifiedPlanedTest(Long planedTestId) throws ServiceException {
		try {
			return testDao.readAllCompletedTestsSpecifiedPlanedTest(planedTestId);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Lab4Task41> getPlanedTestIdCount() throws ServiceException {
		try {
			return testDao.readPlanedTestIdCount();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Lab4Task42> getSumLengthTesterId() throws ServiceException {
		try {
			return testDao.sumLengthTesterId();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

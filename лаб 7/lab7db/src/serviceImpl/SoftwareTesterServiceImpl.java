package serviceImpl;

import java.util.List;

import dao.DaoException;
import dao.SoftwareTesterDao;
import domain.SoftwareTester;
import service.ServiceException;
import service.SoftwareTesterService;

public class SoftwareTesterServiceImpl implements SoftwareTesterService{
	private SoftwareTesterDao softwareTesterDao;

	public void setSoftwareTesterDao(SoftwareTesterDao softwareTesterDao) {
		this.softwareTesterDao = softwareTesterDao;
	}

	@Override
	public List<SoftwareTester> getAll() throws ServiceException {
		try {
			return softwareTesterDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(SoftwareTester tester) throws ServiceException {
		try {
			if(tester.getId() != null) {
				softwareTesterDao.update(tester);
			} else {
				Long id = softwareTesterDao.create(tester);
				tester.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			softwareTesterDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SoftwareTester getSoftwareTester(Long id) throws ServiceException {
		try {
			return softwareTesterDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

package serviceImpl;

import java.util.List;

import dao.DaoException;
import dao.PhoneDao;
import domain.Phone;
import service.PhoneService;
import service.ServiceException;

public class PhoneServiceImpl implements PhoneService{
	private PhoneDao phoneDao;

	public void setPhoneDao(PhoneDao phoneDao) {
		this.phoneDao = phoneDao;
	}

	@Override
	public List<Phone> getAll() throws ServiceException {
		try {
			return phoneDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Phone phone) throws ServiceException {
		try {
			if(phone.getId() != null) {
				phoneDao.update(phone);
			} else {
				Long id = phoneDao.create(phone);
				phone.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			phoneDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Phone getPhone(Long id) throws ServiceException {
		try {
			return phoneDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

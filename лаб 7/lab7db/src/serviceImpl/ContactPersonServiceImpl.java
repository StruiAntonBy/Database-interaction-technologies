package serviceImpl;

import java.util.List;

import dao.ContactPersonDao;
import dao.DaoException;
import domain.ContactPerson;
import domain.Lab4Task31;
import domain.Lab4Task32;
import domain.Lab4Task33;
import service.ContactPersonService;
import service.ServiceException;

public class ContactPersonServiceImpl implements ContactPersonService{
	private ContactPersonDao personDao;

	public void setContactPersonDao(ContactPersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public List<ContactPerson> getAll() throws ServiceException {
		try {
			return personDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(ContactPerson person) throws ServiceException {
		try {
			if(person.getId() != null) {
				personDao.update(person);
			} else {
				Long id = personDao.create(person);
				person.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			personDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public ContactPerson getContactPerson(Long id) throws ServiceException {
		try {
			return personDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Lab4Task31> getContactPersonNumbers() throws ServiceException {
		try {
			return personDao.readContactPersonNumbers();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Lab4Task32> getContactPersonLogins() throws ServiceException {
		try {
			return personDao.readContactPersonLogins();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Lab4Task33> getContactPersonWorkExperiences() throws ServiceException {
		try {
			return personDao.readContactPersonWorkExperiences();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

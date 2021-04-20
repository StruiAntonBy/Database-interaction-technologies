package serviceImpl;

import java.util.List;

import dao.DaoException;
import dao.EmailDao;
import domain.Email;
import service.EmailService;
import service.ServiceException;

public class EmailServiceImpl implements EmailService{
	private EmailDao emailDao;

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	@Override
	public List<Email> getAll() throws ServiceException {
		try {
			return emailDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Email email) throws ServiceException {
		try {
			if(email.getId() != null) {
				emailDao.update(email);
			} else {
				Long id = emailDao.create(email);
				email.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			emailDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Email getEmail(Long id) throws ServiceException {
		try {
			return emailDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Email> getAllEmailAddressesClient(Long clientId) throws ServiceException {
		try {
			return emailDao.readAllEmailAddressesClient(clientId);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

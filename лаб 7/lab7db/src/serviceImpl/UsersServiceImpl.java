package serviceImpl;

import java.util.List;

import dao.DaoException;
import dao.UsersDao;
import domain.Users;
import service.ServiceException;
import service.UsersService;

public class UsersServiceImpl implements UsersService{
	private UsersDao usersDao;

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	@Override
	public List<Users> getAll() throws ServiceException {
		try {
			return usersDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Users user) throws ServiceException {
		try {
			if(user.getId() != null) {
				 usersDao.update(user);
			} else {
				Long id = usersDao.create(user);
				user.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			usersDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Users getUser(Long id) throws ServiceException {
		try {
			return usersDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

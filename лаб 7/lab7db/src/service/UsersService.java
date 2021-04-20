package service;

import domain.Users;

public interface UsersService extends Service<Users>{
	Users getUser(Long id) throws ServiceException;
}

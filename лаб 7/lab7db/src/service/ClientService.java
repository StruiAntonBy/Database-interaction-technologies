package service;

import domain.Client;

public interface ClientService extends Service<Client>{
	Client getClient(Long id) throws ServiceException;
}

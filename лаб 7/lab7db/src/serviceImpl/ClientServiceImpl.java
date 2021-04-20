package serviceImpl;

import java.util.List;

import dao.ClientDao;
import dao.DaoException;
import domain.Client;
import service.ClientService;
import service.ServiceException;

public class ClientServiceImpl implements ClientService{
	private ClientDao clientDao;

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public List<Client> getAll() throws ServiceException {
		try {
			return clientDao.readAll();
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(Client client) throws ServiceException {
		try {
			if(client.getId() != null) {
				clientDao.update(client);
			} else {
				Long id = clientDao.create(client);
				client.setId(id);
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		try {
			clientDao.delete(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Client getClient(Long id) throws ServiceException {
		try {
			return clientDao.read(id);
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}

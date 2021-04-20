package dao;

import java.util.List;

import domain.Email;

public interface EmailDao extends Dao<Email>{
	
	List<Email> readAllEmailAddressesClient(Long clientId) throws DaoException;
	
}

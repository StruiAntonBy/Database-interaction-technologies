package service;

import java.util.List;

import domain.Email;

public interface EmailService extends Service<Email>{
	Email getEmail(Long id) throws ServiceException;
	
	List<Email> getAllEmailAddressesClient(Long clientId) throws ServiceException;
}

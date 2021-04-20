package service;

import java.util.List;

import domain.ContactPerson;
import domain.Lab4Task31;
import domain.Lab4Task32;
import domain.Lab4Task33;

public interface ContactPersonService extends Service<ContactPerson>{
	ContactPerson getContactPerson(Long id) throws ServiceException;
	
	List<Lab4Task31> getContactPersonNumbers() throws ServiceException;
	
	List<Lab4Task32> getContactPersonLogins() throws ServiceException;
	
	List<Lab4Task33> getContactPersonWorkExperiences() throws ServiceException;
}

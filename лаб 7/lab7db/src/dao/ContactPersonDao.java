package dao;

import java.util.List;

import domain.ContactPerson;
import domain.Lab4Task31;
import domain.Lab4Task32;
import domain.Lab4Task33;

public interface ContactPersonDao extends Dao<ContactPerson>{
	List<Lab4Task31> readContactPersonNumbers() throws DaoException;
	
	List<Lab4Task32> readContactPersonLogins() throws DaoException;
	
	List<Lab4Task33> readContactPersonWorkExperiences() throws DaoException;
}

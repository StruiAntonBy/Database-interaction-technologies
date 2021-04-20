package service;

import domain.Entity;
import java.util.List;

public interface Service<T extends Entity> {
	List<T> getAll() throws ServiceException;

	void save(T entity) throws ServiceException;

	void delete(Long id) throws ServiceException;
	
}

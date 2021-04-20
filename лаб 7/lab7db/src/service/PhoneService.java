package service;

import domain.Phone;

public interface PhoneService extends Service<Phone>{
	Phone getPhone(Long id) throws ServiceException;
}

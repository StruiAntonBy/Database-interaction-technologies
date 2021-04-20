package service;

import domain.SoftwareTester;

public interface SoftwareTesterService extends Service<SoftwareTester>{
	SoftwareTester getSoftwareTester(Long id) throws ServiceException;
}

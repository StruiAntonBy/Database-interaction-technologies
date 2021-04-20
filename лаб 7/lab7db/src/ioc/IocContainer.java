package ioc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.ClientDao;
import dao.CompletedTestDao;
import dao.ContactPersonDao;
import dao.EmailDao;
import dao.PhoneDao;
import dao.PlanedTestDao;
import dao.ProjectDao;
import dao.RequirementDao;
import dao.SoftwareTesterDao;
import dao.UsersDao;
import pgsql.ClientDaoSqlImpl;
import pgsql.CompletedTestDaoSqlImpl;
import pgsql.ContactPersonDaoSqlImpl;
import pgsql.EmailDaoSqlImpl;
import pgsql.PhoneDaoSqlImpl;
import pgsql.PlanedTestDaoSqlImpl;
import pgsql.ProjectDaoSqlImpl;
import pgsql.RequirementDaoSqlImpl;
import pgsql.SoftwareTesterDaoSqlImpl;
import pgsql.UsersDaoSqlImpl;
import service.ClientService;
import service.CompletedTestService;
import service.ContactPersonService;
import service.EmailService;
import service.PhoneService;
import service.PlanedTestService;
import service.ProjectService;
import service.RequirementService;
import service.SoftwareTesterService;
import service.UsersService;
import serviceImpl.ClientServiceImpl;
import serviceImpl.CompletedTestServiceImpl;
import serviceImpl.ContactPersonServiceImpl;
import serviceImpl.EmailServiceImpl;
import serviceImpl.PhoneServiceImpl;
import serviceImpl.PlanedTestServiceImpl;
import serviceImpl.ProjectServiceImpl;
import serviceImpl.RequirementServiceImpl;
import serviceImpl.SoftwareTesterServiceImpl;
import serviceImpl.UsersServiceImpl;

public class IocContainer implements AutoCloseable {
	private Connection connection;

	public Connection getConnection() throws ContainerException {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost/testing_projects_db", "root", "1111");
			} catch(SQLException e) {
				throw new ContainerException(e);
			}
		}
		return connection;
	}

	@Override
	public void close() {
		try { connection.close(); } catch (Exception e) {}
	}
	
	public UsersService getUsersService() throws ContainerException {
		UsersServiceImpl service = new UsersServiceImpl();
		service.setUsersDao(getUsersDao());
		return service;
	}

	public UsersDao getUsersDao() throws ContainerException {
		UsersDaoSqlImpl dao = new UsersDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	public SoftwareTesterService getSoftwareTesterService() throws ContainerException {
		SoftwareTesterServiceImpl service = new SoftwareTesterServiceImpl();
		service.setSoftwareTesterDao(getSoftwareTesterDao());
		return service;
	}

	public SoftwareTesterDao getSoftwareTesterDao() throws ContainerException {
		SoftwareTesterDaoSqlImpl dao = new SoftwareTesterDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	public ClientService getClientService() throws ContainerException {
		ClientServiceImpl service = new ClientServiceImpl();
		service.setClientDao(getClientDao());
		return service;
	}

	public ClientDao getClientDao() throws ContainerException {
		ClientDaoSqlImpl dao = new ClientDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	public ContactPersonService getContactPersonService() throws ContainerException {
		ContactPersonServiceImpl service = new ContactPersonServiceImpl();
		service.setContactPersonDao(getContactPersonDao());
		return service;
	}

	public ContactPersonDao getContactPersonDao() throws ContainerException {
		ContactPersonDaoSqlImpl dao = new ContactPersonDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	public PhoneService getPhoneService() throws ContainerException {
		PhoneServiceImpl service = new PhoneServiceImpl();
		service.setPhoneDao(getPhoneDao());
		return service;
	}

	public PhoneDao getPhoneDao() throws ContainerException {
		PhoneDaoSqlImpl dao = new PhoneDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	public EmailService getEmailService() throws ContainerException {
		EmailServiceImpl service = new EmailServiceImpl();
		service.setEmailDao(getEmailDao());
		return service;
	}

	public EmailDao getEmailDao() throws ContainerException {
		EmailDaoSqlImpl dao = new EmailDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	public ProjectService getProjectService() throws ContainerException {
		ProjectServiceImpl service = new ProjectServiceImpl();
		service.setProjectDao(getProjectDao());
		return service;
	}

	public ProjectDao getProjectDao() throws ContainerException {
		ProjectDaoSqlImpl dao = new ProjectDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	public RequirementService getRequirementService() throws ContainerException {
		RequirementServiceImpl service = new RequirementServiceImpl();
		service.setRequirementDao(getRequirementDao());
		return service;
	}

	public RequirementDao getRequirementDao() throws ContainerException {
		RequirementDaoSqlImpl dao = new RequirementDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	public PlanedTestService getPlanedTestService() throws ContainerException {
		PlanedTestServiceImpl service = new PlanedTestServiceImpl();
		service.setPlanedTestDao(getPlanedTestDao());
		return service;
	}

	public PlanedTestDao getPlanedTestDao() throws ContainerException {
		PlanedTestDaoSqlImpl dao = new PlanedTestDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
	
	public CompletedTestService getCompletedTestService() throws ContainerException {
		CompletedTestServiceImpl service = new CompletedTestServiceImpl();
		service.setCompletedTestDao(getCompletedTestDao());
		return service;
	}

	public CompletedTestDao getCompletedTestDao() throws ContainerException {
		CompletedTestDaoSqlImpl dao = new CompletedTestDaoSqlImpl();
		dao.setConnection(getConnection());
		return dao;
	}
}

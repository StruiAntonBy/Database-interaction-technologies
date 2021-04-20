package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Client;
import domain.CompletedTest;
import domain.ContactPerson;
import domain.Email;
import domain.Phone;
import domain.PlanedTest;
import domain.Project;
import domain.Requirement;
import domain.SoftwareTester;
import domain.Users;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ClientService;
import service.CompletedTestService;
import service.ContactPersonService;
import service.EmailService;
import service.PhoneService;
import service.PlanedTestService;
import service.ProjectService;
import service.RequirementService;
import service.ServiceException;
import service.SoftwareTesterService;
import service.UsersService;
import view.ClientsListFrame;
import view.CompletedTestsListFrame;
import view.ContactPersonsListFrame;
import view.EmailsListFrame;
import view.PhonesListFrame;
import view.PlanedTestsListFrame;
import view.ProjectsListFrame;
import view.RequirementsListFrame;
import view.SoftwareTestersListFrame;
import view.StartFrame;
import view.UsersListFrame;

public class NextStartFrameButtonClick implements ActionListener{
	private IocContainer container;
	private StartFrame startFrame;

	public NextStartFrameButtonClick(StartFrame startFrame,IocContainer container) {
		this.container=container;
		this.startFrame = startFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(startFrame.getNameTable().trim()) {
			case "users":
				try {
					UsersService service = container.getUsersService();
					List<Users> users = service.getAll();
					UsersListFrame usersListFrame = new UsersListFrame(container,0);
					usersListFrame.setUsers(users);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "software_tester":
				try {
					SoftwareTesterService service = container.getSoftwareTesterService();
					List<SoftwareTester> testers = service.getAll();
					SoftwareTestersListFrame testersListFrame = new SoftwareTestersListFrame(container,0);
					testersListFrame.setTesters(testers);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "client":
				try {
					ClientService service = container.getClientService();
					List<Client> clients = service.getAll();
					ClientsListFrame clientsListFrame = new ClientsListFrame(container,0);
					clientsListFrame.setClients(clients);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "contact_person":
				try {
					ContactPersonService service = container.getContactPersonService();
					List<ContactPerson> persons = service.getAll();
					ContactPersonsListFrame personsListFrame = new ContactPersonsListFrame(container,0);
					personsListFrame.setPersons(persons);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "phone":
				try {
					PhoneService service = container.getPhoneService();
					List<Phone> phones = service.getAll();
					PhonesListFrame phonesListFrame = new PhonesListFrame(container,0);
					phonesListFrame.setPhones(phones);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "e-mail":
				try {
					EmailService service = container.getEmailService();
					List<Email> emails = service.getAll();
					EmailsListFrame emailsListFrame = new EmailsListFrame(container,0);
					emailsListFrame.setEmails(emails);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "project":
				try {
					ProjectService service = container.getProjectService();
					List<Project> projects = service.getAll();
					ProjectsListFrame projectsListFrame = new ProjectsListFrame(container,0);
					projectsListFrame.setProjects(projects);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "planed_test":
				try {
					PlanedTestService service = container.getPlanedTestService();
					List<PlanedTest> tests = service.getAll();
					PlanedTestsListFrame testsListFrame = new PlanedTestsListFrame(container,0);
					testsListFrame.setTests(tests);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "completed_test":
				try {
					CompletedTestService service = container.getCompletedTestService();
					List<CompletedTest> tests = service.getAll();
					CompletedTestsListFrame testsListFrame = new CompletedTestsListFrame(container,0);
					testsListFrame.setTests(tests);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "requirement":
				try {
					RequirementService service = container.getRequirementService();
					List<Requirement> requirements = service.getAll();
					RequirementsListFrame requirementsListFrame = new RequirementsListFrame(container,0);
					requirementsListFrame.setRequirements(requirements);
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "Invalid table name", "Warning", JOptionPane.WARNING_MESSAGE);
				break;
		}
	}

}

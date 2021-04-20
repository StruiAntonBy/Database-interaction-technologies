package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class NextTask1StartFrameButtonClick implements ActionListener{
	private IocContainer container;
	private StartFrame startFrame;
	
	public NextTask1StartFrameButtonClick(StartFrame startFrame,IocContainer container) {
		this.container=container;
		this.startFrame = startFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(startFrame.getNameTableTask1().trim()) {
			case "users":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						UsersService service = container.getUsersService();
						List<Users> users = new ArrayList<>();
						Users user=service.getUser(startFrame.getPrimarykeyTask1());
						if(user!=null) {
							users.add(user);
							UsersListFrame usersListFrame = new UsersListFrame(container,1);
							usersListFrame.setUsers(users);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "software_tester":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						SoftwareTesterService service = container.getSoftwareTesterService();
						List<SoftwareTester> testers = new ArrayList<>();
						SoftwareTester tester=service.getSoftwareTester(startFrame.getPrimarykeyTask1());
						if(tester!=null) {
							testers.add(tester);
							SoftwareTestersListFrame testersListFrame = new SoftwareTestersListFrame(container,1);
							testersListFrame.setTesters(testers);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "client":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						ClientService service = container.getClientService();
						List<Client> clients = new ArrayList<>();
						Client client=service.getClient(startFrame.getPrimarykeyTask1());
						if(client!=null) {
							clients.add(client);
							ClientsListFrame clientsListFrame = new ClientsListFrame(container,1);
							clientsListFrame.setClients(clients);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "contact_person":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						ContactPersonService service = container.getContactPersonService();
						List<ContactPerson> persons = new ArrayList<>();
						ContactPerson person=service.getContactPerson(startFrame.getPrimarykeyTask1());
						if(person!=null) {
							persons.add(person);
							ContactPersonsListFrame personsListFrame = new ContactPersonsListFrame(container,1);
							personsListFrame.setPersons(persons);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "phone":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						PhoneService service = container.getPhoneService();
						List<Phone> phones = new ArrayList<>();
						Phone phone=service.getPhone(startFrame.getPrimarykeyTask1());
						if(phone!=null) {
							phones.add(phone);
							PhonesListFrame phonesListFrame = new PhonesListFrame(container,1);
							phonesListFrame.setPhones(phones);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "e-mail":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						EmailService service = container.getEmailService();
						List<Email> emails = new ArrayList<>();
						Email email=service.getEmail(startFrame.getPrimarykeyTask1());
						if(email!=null) {
							emails.add(email);
							EmailsListFrame emailsListFrame = new EmailsListFrame(container,1);
							emailsListFrame.setEmails(emails);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "project":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						ProjectService service = container.getProjectService();
						List<Project> projects = new ArrayList<>();
						Project project=service.getProject(startFrame.getPrimarykeyTask1());
						if(project!=null) {
							projects.add(project);
							ProjectsListFrame projectsListFrame = new ProjectsListFrame(container,1);
							projectsListFrame.setProjects(projects);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "planed_test":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						PlanedTestService service = container.getPlanedTestService();
						List<PlanedTest> tests = new ArrayList<>();
						PlanedTest test=service.getPlanedTest(startFrame.getPrimarykeyTask1());
						if(test!=null) {
							tests.add(test);
							PlanedTestsListFrame testsListFrame = new PlanedTestsListFrame(container,1);
							testsListFrame.setTests(tests);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "completed_test":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						CompletedTestService service = container.getCompletedTestService();
						List<CompletedTest> tests = new ArrayList<>();
						CompletedTest test=service.getCompletedTest(startFrame.getPrimarykeyTask1());
						if(test!=null) {
							tests.add(test);
							CompletedTestsListFrame testsListFrame = new CompletedTestsListFrame(container,1);
							testsListFrame.setTests(tests);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ContainerException | ServiceException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Application error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "requirement":
				try {
					if(startFrame.getPrimarykeyTask1()!=null) {
						RequirementService service = container.getRequirementService();
						List<Requirement> requirements = new ArrayList<>();
						Requirement requirement=service.getRequirement(startFrame.getPrimarykeyTask1());
						if(requirement!=null) {
							requirements.add(requirement);
							RequirementsListFrame requirementsListFrame = new RequirementsListFrame(container,1);
							requirementsListFrame.setRequirements(requirements);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect data entry", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Primary key is empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
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

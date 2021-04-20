package phone_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Phone;
import ioc.ContainerException;
import ioc.IocContainer;
import service.PhoneService;
import service.ServiceException;
import view.PhoneEditFrame;

public class SavePhoneButtonClick implements ActionListener {
	private PhoneEditFrame phoneEditFrame;
	private IocContainer container;

	public SavePhoneButtonClick(PhoneEditFrame phoneEditFrame, IocContainer container) {
		this.phoneEditFrame = phoneEditFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Phone phone = phoneEditFrame.getPhone();
		if(phone != null) {
			try {
				PhoneService service = container.getPhoneService();
				service.save(phone);
				JOptionPane.showMessageDialog(phoneEditFrame, "Data was saved successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
				phoneEditFrame.update(service.getAll());
				phoneEditFrame.dispose();
			} catch(ContainerException | ServiceException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(phoneEditFrame, "Error communicating with the database", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
package phone_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import domain.Phone;
import ioc.IocContainer;
import view.PhoneEditFrame;
import view.PhonesListFrame;

public class EditPhoneButtonClick implements ActionListener {
	private PhonesListFrame phonesListFrame;
	private IocContainer container;

	public EditPhoneButtonClick(PhonesListFrame phonesListFrame, IocContainer container) {
		this.phonesListFrame = phonesListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Phone phone = phonesListFrame.getSelectedPhone();
		if(phone != null) {
			new PhoneEditFrame(phonesListFrame, phone, container);
		} else {
			JOptionPane.showMessageDialog(phonesListFrame, "No phone is selected in the table", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
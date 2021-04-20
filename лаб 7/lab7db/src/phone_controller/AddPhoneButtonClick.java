package phone_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ioc.IocContainer;
import view.PhoneEditFrame;
import view.PhonesListFrame;

public class AddPhoneButtonClick implements ActionListener {
	private PhonesListFrame phonesListFrame;
	private IocContainer container;

	public AddPhoneButtonClick(PhonesListFrame phonesListFrame, IocContainer container) {
		this.phonesListFrame = phonesListFrame;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new PhoneEditFrame(phonesListFrame, container);
	}
}
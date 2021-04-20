import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.ApplicationFinisher;
import controller.ApplicationStarter;
import ioc.IocContainer;

public class Runner {
	public static void main(String[] args) {
		try {
			IocContainer container = new IocContainer();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			ApplicationStarter applicationStarter = new ApplicationStarter(container);
			Runtime.getRuntime().addShutdownHook(new Thread(new ApplicationFinisher(container)));
			applicationStarter.start();
		} catch(UnsupportedLookAndFeelException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
			JOptionPane.showMessageDialog(null, "It is not possible to draw a window in the system style", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}

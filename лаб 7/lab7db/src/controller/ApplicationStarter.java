package controller;

import ioc.IocContainer;
import view.StartFrame;

public class ApplicationStarter {
	private IocContainer container;

	public ApplicationStarter(IocContainer container) {
		this.container = container;
	}

	public void start() {
		new StartFrame(container);
	}
}

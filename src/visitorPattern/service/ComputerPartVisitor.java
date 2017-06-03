package visitorPattern.service;

import visitorPattern.service.impl.Computer;
import visitorPattern.service.impl.Keyboard;
import visitorPattern.service.impl.Monitor;
import visitorPattern.service.impl.Mouse;

public interface ComputerPartVisitor {

	void visitor(Computer computer);
	void visitor(Keyboard keyboard);
	void visitor(Mouse mouse);
	void visitor(Monitor monitor);
}

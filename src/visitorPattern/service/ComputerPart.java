package visitorPattern.service;

public interface ComputerPart {

	void accept(ComputerPartVisitor visitor);
}

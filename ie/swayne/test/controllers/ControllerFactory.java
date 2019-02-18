package ie.swayne.test.controllers;

public final class ControllerFactory {
	
	public static Controller get(String name) {
		name = name.toLowerCase();
		switch(name) {
		case "login": return new LoginController();
		case "Test": return new TestController();
		default:
			throw new IllegalArgumentException("Invalid controller (" + name + ") selected.");
		}
	}
}
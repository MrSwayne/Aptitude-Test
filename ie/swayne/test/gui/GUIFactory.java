package ie.swayne.test.gui;

public final class GUIFactory {

	public static GUI get(String name) {
		name = name.toLowerCase();
		switch(name) {
		case "login": return new LoginGUI();
		case "Test": return new TestGUI();
		default:
			throw new IllegalArgumentException("Could not find GUI (" + name + ")");
		}
	}
}
package nl.scouting.hit.sitecreator.input;

import nl.scouting.hit.sitecreator.input.module.InputModule;

public interface InputModuleUI {

	String getName();

	InputModule getProcessor();
}

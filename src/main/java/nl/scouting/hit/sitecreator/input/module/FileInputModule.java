package nl.scouting.hit.sitecreator.input.module;

import javax.swing.filechooser.FileNameExtensionFilter;

public interface FileInputModule extends InputModule {

	FileNameExtensionFilter getFilter();
}

package Engine.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    List<File> levels;
    File levelsFolder;

    /**
     * Creates a level manager
     * @param levelsFolder the folder with levels
     */
    public LevelManager(File levelsFolder) {
        if (!levelsFolder.isDirectory()) {
            throw new IllegalArgumentException("File: " + levelsFolder.getName() + " - is not a folder");
        }

        this.levelsFolder = levelsFolder;
    }

    /**
     * @return levels
     */
    public List<File> getLevels() {
        if (levels == null)
            parseLevels(levelsFolder);

        return levels;
    }

    private void parseLevels(File folder) {
        File[] files = folder.listFiles();
        levels = new ArrayList<>();
        for (File file : files) {
            if (file.isFile())
                levels.add(file);
        }
    }
}

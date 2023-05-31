package Logs;

import java.io.File;

public enum LogTo {
    FILE("./src/main/logs/log.txt"),
    CONSOLE(null);

    File fileLocation;

    LogTo(String fileLocation) {
        if (fileLocation != null)
            this.fileLocation = new File(fileLocation);
    }

    public File fileLocation() {
        return fileLocation;
    }
}

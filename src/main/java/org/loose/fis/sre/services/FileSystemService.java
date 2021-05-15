package org.loose.fis.sre.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class FileSystemService {
    public static String APPLICATION_FOLDER = ".dc-makeup-shop";
    private static final String USER_FOLDER = System.getProperty("user.home");

    public static Path getPathToFile(String... path) {
        return getApplicationHomeFolder().resolve(Paths.get(".", path));
    }

    public static Path getApplicationHomeFolder() {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    public static void initDirectory() {
        Path applicationHomePath = getApplicationHomeFolder();
        if (!Files.exists(applicationHomePath)) {
            boolean wasSuccessful;
            wasSuccessful = applicationHomePath.toFile().mkdirs();
            if (!wasSuccessful) {
                System.out.println("was not successful.");
            }
        }
    }

}

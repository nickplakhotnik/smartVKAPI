package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static aquality.selenium.browser.AqualityServices.getLogger;

public class DownloadPhotoUtil {

    public static final String pathNameOfDownloadedPhoto = "src\\main\\resources\\actualImage.jpg";

    public static String downloadPhoto(String photoURL) throws IOException {
        InputStream in = new URL(photoURL).openStream();
        Files.copy(in, Paths.get(new File(pathNameOfDownloadedPhoto).getAbsolutePath()));
        getLogger().info("Image downloaded from " + photoURL);
        return pathNameOfDownloadedPhoto;
    }
}

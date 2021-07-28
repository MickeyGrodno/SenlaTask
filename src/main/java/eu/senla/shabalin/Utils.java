package eu.senla.shabalin;

import io.qameta.allure.Attachment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;

public class Utils {
    @Attachment
    public static byte[] takeScreenShot() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(
                    new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())),
                    "png",
                    baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (AWTException | IOException e) {
            System.err.println("Failed to create or save screenshot");
        }
        return null;
    }

    @Attachment(value = "Вложение")
    public static byte[] getExpectedParametersOnTxt(String pageUrl, String alertMessage) {
        List<String> list = Arrays.asList(pageUrl, alertMessage);
        String fileDirectory;
        if(System.getProperty("os.name").equals("Linux")) {
            fileDirectory = "src/main/resources/testreportdata/";
        } else {
            fileDirectory = "src\\main\\resources\\testreportdata\\";
        }
        File directory = new File(fileDirectory);
        if(!directory.exists()) {
            directory.mkdir();
        }

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd_HH-mm-ss")
                .toFormatter();
        Path file = Paths.get(fileDirectory + LocalDateTime.now().format(formatter));
        try {
            Files.write(file, list, StandardCharsets.UTF_8);
            return Files.readAllBytes(file);
        } catch (IOException e) {
            System.err.println("File not found!");
        }
        return null;
    }
}

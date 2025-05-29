package com.maxeriksson.mailinator.formatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MailFormatter {

    public List<String> readMailTemplate(String filePath) {
        List<String> mailTemplate = new ArrayList<>();

        File file = new File(filePath);
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                mailTemplate.add(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File path not found: " + filePath);
        }

        return mailTemplate;
    }
}

package com.maxeriksson.mailinator.formatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MailFormatter {

    private File mailDraft;

    public MailFormatter(String mailDraftFilePath) {
        mailDraft = new File(mailDraftFilePath);
    }

    public List<String> readMailTemplate() {
        List<String> mailTemplate = new ArrayList<>();

        try (Scanner fileReader = new Scanner(mailDraft)) {
            while (fileReader.hasNextLine()) {
                mailTemplate.add(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File path not found: " + mailDraft.getPath());
        }

        return mailTemplate;
    }
}

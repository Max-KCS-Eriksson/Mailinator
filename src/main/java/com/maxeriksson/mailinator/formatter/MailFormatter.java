package com.maxeriksson.mailinator.formatter;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MailFormatter {

    private File mailDraft;

    private final String SKIP_LINE_INDICATOR = "!{skip}";

    public MailFormatter(@Value("${email.draft}") String mailDraftFilePath) {
        mailDraft = new File(mailDraftFilePath);
    }

    public String formatMailDraft(CompanyMailDetails mailDetails) {
        String formattedMail = "";
        List<String> mailDraft = readMailTemplate();
        for (int i = 0; i < mailDraft.size(); i++) {
            String line = mailDraft.get(i);
            String formattedLine = replaceTemplateTag(line, mailDetails);

            if (formattedLine.equals(SKIP_LINE_INDICATOR)) continue;
            if (i != mailDraft.size() - 1) formattedLine += "\n";

            formattedMail += formattedLine;
        }

        return formattedMail;
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

    public String replaceTemplateTag(String text, CompanyMailDetails values) {
        return text.replace("${company}", values.getCompany())
                .replace("${contactPerson}", values.getContactPerson())
                .replace(
                        "${optionalParagraph}",
                        !values.getOptionalParagraph().isBlank()
                                ? values.getOptionalParagraph()
                                : SKIP_LINE_INDICATOR);
    }
}

package com.maxeriksson.mailinator.formatter;

import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MailFormatter {

    private File mailDraft;

    private final String SKIP_LINE_INDICATOR = "!{skip}";

    public MailFormatter(String mailDraftFilePath) {
        mailDraft = new File(mailDraftFilePath);
    }

    public List<String> formatMailDraft(CompanyMailDetails mailDetails) {
        List<String> formattedMail = new ArrayList<>();
        List<String> mailDraft = readMailTemplate();
        for (String line : mailDraft) {
            String formattedLine = replaceTemplateTag(line, mailDetails);
            if (formattedLine.equals(SKIP_LINE_INDICATOR)) continue;
            formattedMail.add(formattedLine);
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
        return text.replace("${name}", values.getName())
                .replace("${contactPerson}", values.getContactPerson())
                .replace(
                        "${optionalParagraph}",
                        values.getOptionalParagraph() != null ? values.getOptionalParagraph() : SKIP_LINE_INDICATOR);
    }
}
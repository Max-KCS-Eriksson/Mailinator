package com.maxeriksson.mailinator.formatter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MailFormatterTest {

    private static MailFormatter mailFormatter;

    private static List<String> mailTemplate;

    @BeforeAll
    public static void setUp() {
        mailFormatter = new MailFormatter("src/test/resources/demoMailTemplate.txt");

        mailTemplate =
                Arrays.asList(
                        "Hello ${contactPerson}",
                        "Bla bla bla ${name}",
                        "${optionalParagraph}",
                        "Best regards,",
                        "John Doe");
    }

    @Test
    public void readMailTemplateTest() {
        List<String> expected = mailTemplate;
        List<String> actual = mailFormatter.readMailTemplate();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void formatMailDraftTest() {
        CompanyMailDetails mailDetails =
                new CompanyMailDetails(
                        "Company AB", "John Doe", "john.doe@company.se", Optional.of("Foo bar"));
        List<String> expected =
                Arrays.asList(
                        "Hello " + mailDetails.getContactPerson(),
                        "Bla bla bla " + mailDetails.getName(),
                        mailDetails.getOptionalParagraph().get(),
                        "Best regards,",
                        "John Doe");
        List<String> actual = mailFormatter.formatMailDraft(mailDetails);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void formatMailDraftTestWithoutOptionalParagraph() {
        CompanyMailDetails mailDetails =
                new CompanyMailDetails("Company AB", "John Doe", "john.doe@company.se");
        List<String> expected =
                Arrays.asList(
                        "Hello " + mailDetails.getContactPerson(),
                        "Bla bla bla " + mailDetails.getName(),
                        "Best regards,",
                        "John Doe");
        List<String> actual = mailFormatter.formatMailDraft(mailDetails);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}

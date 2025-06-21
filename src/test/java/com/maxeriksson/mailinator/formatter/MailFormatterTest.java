package com.maxeriksson.mailinator.formatter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MailFormatterTest {

    private static MailFormatter mailFormatter;

    private static List<String> mailTemplate;

    @BeforeAll
    public static void setUp() {
        mailFormatter = new MailFormatter("src/test/resources/demoMailTemplate.txt");

        mailTemplate =
                Arrays.asList(
                        "Hello ${contactPerson}",
                        "Bla bla bla ${company}",
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
                new CompanyMailDetails("Company AB", "John Doe", "john.doe@company.se", "Foo bar");
        String expected =
                "Hello "
                        + mailDetails.getContactPerson()
                        + "\n"
                        + "Bla bla bla "
                        + mailDetails.getCompany()
                        + "\n"
                        + mailDetails.getOptionalParagraph()
                        + "\n"
                        + "Best regards,"
                        + "\n"
                        + "John Doe";
        String actual = mailFormatter.formatMailDraft(mailDetails);
        assertEquals(expected, actual);
    }

    @Test
    public void formatMailDraftTestWithoutOptionalParagraph() {
        CompanyMailDetails mailDetails =
                new CompanyMailDetails("Company AB", "John Doe", "john.doe@company.se");
        String expected =
                "Hello "
                        + mailDetails.getContactPerson()
                        + "\n"
                        + "Bla bla bla "
                        + mailDetails.getCompany()
                        + "\n"
                        + "Best regards,"
                        + "\n"
                        + "John Doe";
        String actual = mailFormatter.formatMailDraft(mailDetails);
        assertEquals(expected, actual);
    }
}

package com.maxeriksson.mailinator.formatter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MailFormatterTest {

    private static MailFormatter mailFormatter;

    private static String filePath;
    private static List<String> mailTemplate;

    @BeforeAll
    public static void setUp() {
        mailFormatter = new MailFormatter();

        filePath = "demoMailTemplate.txt";
        mailTemplate =
                Arrays.asList(
                        "Hello ${contactPerson}",
                        "Bla bla bla",
                        "${optionalParagraph}",
                        "Best regards,",
                        "John Doe");
    }

    @Test
    public void readMailTemplateTestLength() {
        List<String> expected = mailTemplate;
        List<String> actual = mailFormatter.readMailTemplate(filePath);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void readMailTemplateTestContent() {
        List<String> readContent = mailFormatter.readMailTemplate(filePath);
        for (int i = 0; i < mailTemplate.size(); i++) {
            String expected = mailTemplate.get(i);
            String actual = readContent.get(i);
            assertEquals(expected, actual);
        }
    }
}

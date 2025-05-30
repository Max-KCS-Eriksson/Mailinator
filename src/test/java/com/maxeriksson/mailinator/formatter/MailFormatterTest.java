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
}

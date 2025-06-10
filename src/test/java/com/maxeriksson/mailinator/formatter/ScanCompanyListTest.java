package com.maxeriksson.mailinator.formatter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ScanCompanyListTest {

    @Test
    public void getCompanyMailDetailListTest() {
        String pathToCsvFile = "src/test/resources/demoCompanyList.csv";
        ScanCompanyList companyListParser = new ScanCompanyList(pathToCsvFile);
        List<CompanyMailDetails> expected =
                Arrays.asList(
                        new CompanyMailDetails("Corpo AB", "John Doe", "john.doe@corpo.se"),
                        new CompanyMailDetails(
                                "Dreams Inc.",
                                "Jane Smith",
                                "jane.smith@dreams.se",
                                "Lorem ipsum dolor sit amet consectetur adipiscing elit."
                                        + " Quisque faucibus ex sapien vitae pellentesque sem"
                                        + " placerat. \n"
                                        + "In id cursus mi pretium tellus duis convallis."
                                        + " Tempus leo eu aenean sed diam urna tempor. Pulvinar"
                                        + " vivamus \n"
                                        + "fringilla lacus nec metus bibendum egestas. Iaculis"
                                        + " massa nisl malesuada lacinia integer nunc posuere."
                                        + " Ut hendrerit \n"
                                        + "semper vel class aptent taciti sociosqu. Ad litora"
                                        + " torquent per conubia nostra inceptos himenaeos.\n"
                                        + "\n"
                                        + "Lorem ipsum dolor sit amet consectetur adipiscing"
                                        + " elit. Quisque faucibus ex sapien vitae pellentesque"
                                        + " sem placerat. \n"
                                        + "In id cursus mi pretium tellus duis convallis."
                                        + " Tempus leo eu aenean sed diam urna tempor. Pulvinar"
                                        + " vivamus \n"
                                        + "fringilla lacus nec metus bibendum egestas. Iaculis"
                                        + " massa nisl malesuada lacinia integer nunc posuere."
                                        + " Ut hendrerit \n"
                                        + "semper vel class aptent taciti sociosqu. Ad litora"
                                        + " torquent per conubia nostra inceptos himenaeos."));

        List<CompanyMailDetails> actual = companyListParser.getCompanyMailDetailList();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}

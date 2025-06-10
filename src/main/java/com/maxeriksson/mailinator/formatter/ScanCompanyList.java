package com.maxeriksson.mailinator.formatter;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.List;

public class ScanCompanyList {

    private String csvFilePath;

    @Value("email.recipientListSeparator")
    private String csvSeparator;

    public ScanCompanyList(String csvFilePath) {
        this.csvFilePath = csvFilePath;
        this.csvSeparator = csvSeparator == null ? ";" : csvSeparator;
    }

    public List<CompanyMailDetails> getCompanyMailDetailList() {
        try (FileReader reader = new FileReader(csvFilePath)) {
            CsvToBean<CompanyMailDetails> csvToBean =
                    new CsvToBeanBuilder<CompanyMailDetails>(reader)
                            .withType(CompanyMailDetails.class)
                            .withSeparator(csvSeparator.toCharArray()[0])
                            .withIgnoreLeadingWhiteSpace(true)
                            .withThrowExceptions(false)
                            .build();
            return csvToBean.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

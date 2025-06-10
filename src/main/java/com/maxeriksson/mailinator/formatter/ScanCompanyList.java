package com.maxeriksson.mailinator.formatter;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class ScanCompanyList {

    @Value("${email.recipient.list}")
    private String csvFilePath;

    @Value("${email.recipient.list.separator}")
    private String csvSeparator;

    public ScanCompanyList() {}

    public ScanCompanyList(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public List<CompanyMailDetails> getCompanyMailDetailList() {
        if (csvSeparator == null || csvSeparator.isEmpty()) csvSeparator = ";";
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

package com.maxeriksson.mailinator.formatter;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.util.List;

public class ScanCompanyList {

    private String csvFilePath;

    public ScanCompanyList(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public List<CompanyMailDetails> getCompanyMailDetailList() {
        try (FileReader reader = new FileReader(csvFilePath)) {
            CsvToBean<CompanyMailDetails> csvToBean = new CsvToBeanBuilder<CompanyMailDetails>(reader)
                    .withType(CompanyMailDetails.class)
                    .withSeparator('$')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withThrowExceptions(false)
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
    }
}
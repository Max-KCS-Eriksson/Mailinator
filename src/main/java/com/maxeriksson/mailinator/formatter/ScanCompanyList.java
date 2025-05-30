package com.maxeriksson.mailinator.formatter;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScanCompanyList {

    public List<CompanyMailDetails> getCompanyMailDetailList() {
        return new ArrayList<>(); // TODO: IMPLEMENT
    }

    public static void csvReaderDemo() {
        System.out.println("Reading CSV using BufferedReader!");

        // Path to our CSV file
        File csvFile = new File("CompanyList.csv");

        // Lists to store our data
        List<List<String>> data = new ArrayList<>();

        // // Try-with-resources to ensure the reader gets closed automatically
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Split the line by comma and convert to a List
                String[] values = line.split("\\$");
                List<String> lineData = Arrays.asList(values);

                // Add the line data to our main list
                data.add(lineData);
            }

            // Print the data we read
            System.out.println("\nData read from CSV file:");
            for (int i = 0; i < data.size(); i++) {
                List<String> row = data.get(i);
                System.out.println("Row " + i + ": " + String.join(", ", row));
            }

        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Now the openCSV method: \n");
    }

    // Try using openCSV to map values to java objects
    public static void processCSV() {
//    public void processCSV(String csvFilePath) {
        try (FileReader reader = new FileReader("CompanyList.csv")) {
  //      try (FileReader reader = new FileReader(csvFilePath)) {
            CsvToBean<CompanyMailDetails> csvToBean = new CsvToBeanBuilder<CompanyMailDetails>(reader)
                    .withType(CompanyMailDetails.class)
                    .withSeparator('$')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<CompanyMailDetails> companyMailDetailsList = csvToBean.parse();

            for (CompanyMailDetails details : companyMailDetailsList) {
                System.out.println(details);
            }

        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }


}

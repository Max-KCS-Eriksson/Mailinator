package com.maxeriksson.mailinator.formatter;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.util.Optional;

public class CompanyMailDetails {
 //   @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "name")
    private String name;
 //   @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "contactPerson")
    private String contactPerson;
    //   @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "contactEmail")
    private String contactEmail;
//    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "optionalParagraph")
    private Optional<String> optionalParagraph;

    public CompanyMailDetails(String name, String contactPerson, String contactEmail) {
    }

   /* public CompanyMailDetails(String name, String contactPerson, String contactEmail) {
        this(name, contactPerson, contactEmail, Optional.empty());
    }*/

    public CompanyMailDetails(
            String name,
            String contactPerson,
            String contactEmail,
            Optional<String> optionalParagraph) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.optionalParagraph = optionalParagraph;
    }

    public String toString() {
        return "CompanyMailDetails{" +
                "name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", optionalParagraph=" + optionalParagraph +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Optional<String> getOptionalParagraph() {
        return optionalParagraph;
    }

    public void setOptionalParagraph(Optional<String> optionalParagraph) {
        this.optionalParagraph = optionalParagraph;
    }
}

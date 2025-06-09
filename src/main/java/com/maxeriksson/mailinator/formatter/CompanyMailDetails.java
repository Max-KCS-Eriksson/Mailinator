package com.maxeriksson.mailinator.formatter;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.util.Optional;

public class CompanyMailDetails {
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String contactPerson;
    @CsvBindByName
    private String contactEmail;
    @CsvBindByName
    private String optionalParagraph;

    public CompanyMailDetails() {
    }

    public CompanyMailDetails(String name, String contactPerson, String contactEmail) {
        this(name, contactPerson, contactEmail, "");
    }

    public CompanyMailDetails(
            String name,
            String contactPerson,
            String contactEmail,
            String optionalParagraph) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.optionalParagraph = optionalParagraph;
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

    public String getOptionalParagraph() {
        return optionalParagraph;
    }

    public void setOptionalParagraph(String optionalParagraph) {
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

}
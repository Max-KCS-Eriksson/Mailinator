package com.maxeriksson.mailinator.formatter;

import java.util.Optional;

public class CompanyMailDetails {

    private String name;
    private String contactPerson;
    private String contactEmail;
    private Optional<String> optionalParagraph;

    public CompanyMailDetails(String name, String contactPerson, String contactEmail) {
        this(name, contactPerson, contactEmail, Optional.empty());
    }

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

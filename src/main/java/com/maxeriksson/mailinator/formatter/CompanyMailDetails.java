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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CompanyMailDetails other = (CompanyMailDetails) obj;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (contactPerson == null) {
            if (other.contactPerson != null) return false;
        } else if (!contactPerson.equals(other.contactPerson)) return false;
        if (contactEmail == null) {
            if (other.contactEmail != null) return false;
        } else if (!contactEmail.equals(other.contactEmail)) return false;
        if (optionalParagraph == null) {
            if (other.optionalParagraph != null) return false;
        } else if (!optionalParagraph.equals(other.optionalParagraph)) return false;
        return true;
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

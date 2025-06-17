package com.maxeriksson.mailinator.formatter;

import com.opencsv.bean.CsvBindByName;

public class CompanyMailDetails {

    @CsvBindByName private String company;
    @CsvBindByName private String contactPerson;
    @CsvBindByName private String contactEmail;
    @CsvBindByName private String optionalParagraph;

    public CompanyMailDetails() {}

    public CompanyMailDetails(String company, String contactPerson, String contactEmail) {
        this(company, contactPerson, contactEmail, "");
    }

    public CompanyMailDetails(
            String company, String contactPerson, String contactEmail, String optionalParagraph) {
        this.company = company;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.optionalParagraph = optionalParagraph;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
        if (company == null) {
            if (other.company != null) return false;
        } else if (!company.equals(other.company)) return false;
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
        return "CompanyMailDetails{"
                + "company='"
                + company
                + '\''
                + ", contactPerson='"
                + contactPerson
                + '\''
                + ", contactEmail='"
                + contactEmail
                + '\''
                + ", optionalParagraph="
                + optionalParagraph
                + '}';
    }
}

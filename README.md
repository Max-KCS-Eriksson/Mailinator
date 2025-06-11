# Mailinator

## Email Settings

User setting are specified in `src/main/resources/email.properties`.
First create above file in the mentioned path, and fill in below properties.

### Login

```
EMAIL_SENDER=<your_email_address>
EMAIL_PASSWORD=<your_password>
```

### Email Draft

The specified email draft will be sent to all recipients listed in the `contactEmail`
column in the CSV file specified in the `EMAIL_RECIPIENT_LIST=<path_to_file>` setting.

```
EMAIL_DRAFT=<path_to_email_draft>
```

### File attachment

A File can be attached to sent email by filling in below property with a path relative
to this application root directory.
This property can be completely omitted if not attachment is to be sent.

```
EMAIL_ATTACHMENT=<path/to/file>
```

## Email Draft Placeholders

Below listed placeholders will be processed according to the column values listed in the
CSV file specified in the `EMAIL_RECIPIENT_LIST=<path_to_file>` setting.

Available placeholders for the draft are:
`${name}`, `${contactPerson}`, `${optionalParagraph}`.

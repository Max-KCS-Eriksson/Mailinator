# Mailinator

## Email login

Fill in below properties in `src/main/resources/email.properties`.

```
EMAIL_SENDER=<your_email_address>
EMAIL_PASSWORD=<your_password>
```

A File can be attached to sent email by filling in below property with a path relative
to this application root directory.
This property can be completely omitted if not attachment is to be sent.

```
EMAIL_ATTACHMENT=<path/to/file>
```

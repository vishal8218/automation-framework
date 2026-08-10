package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String subject, String messageText, String screenshotPath) throws Exception {
        ConfigReader configReader = new ConfigReader();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            configReader.getFromEmail(),
                            configReader.getEmailPass());
                }
            });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(configReader.getFromEmail()));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(configReader.getToEmail()));
        message.setSubject(subject);

        // HTML EMAIL CONTENT
        String htmlContent =
                "<html>"
              + "<body style='font-family:Arial;'>"
              + "<div style='max-width:600px;"
              + "margin:auto;"
              + "padding:20px;"
              + "border:1px solid #ddd;"
              + "border-radius:10px;'>"
              + "<h2 style='color:red;'>"
              + "❌ Test Case Failed"
              + "</h2>"
              + "<p><b>Test Case:</b> "
              + subject + "</p>"
              + "<p><b>Failure Reason:</b></p>"
              + "<div style='background:#f5f5f5;"
              + "padding:10px;"
              + "border-radius:5px;'>"
              + messageText
              + "</div>"
              + "<br>"
              + "<p>Please check attached screenshot"
              + (screenshotPath == null ? " (unavailable for this failure)." : ".")
              + "</p>"
              + "<hr>"
              + "<p style='font-size:12px;color:gray;'>"
              + "Automation Test Report"
              + "</p>"
              + "</div>"
              + "</body>"
              + "</html>";

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(htmlPart);

        // ATTACH SCREENSHOT — only if a real path was provided and the file exists.
        // Previously this was derived from subject.substring(13) + ".png", which
        // assumed "Test Failed: " was always exactly 13 characters and that the
        // file already existed by send time — neither was reliable.
        if (screenshotPath != null) {
            File screenshotFile = new File(screenshotPath);
            if (screenshotFile.exists()) {
                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.attachFile(screenshotFile);
                multipart.addBodyPart(imagePart);
            } else {
                System.out.println("Screenshot file not found, sending email without attachment: " + screenshotPath);
            }
        }

        message.setContent(multipart);
        Transport.send(message);
        System.out.println("Email Sent Successfully");
    }
}

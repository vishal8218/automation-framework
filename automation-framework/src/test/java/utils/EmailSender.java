package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String subject,
                                 String messageText) throws Exception {

        final String fromEmail = "vishalevoke27@gmail.com";
        final String password = "wvic hncq gbcj bxdx"; // App password
        final String toEmail = "vk368065@gmail.com";

        // SMTP configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail));

        message.setSubject(subject);

        // ✅ TEXT PART
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(messageText);

        // ✅ IMAGE ATTACHMENT PART
        MimeBodyPart imagePart = new MimeBodyPart();
        String imagePaths = System.getProperty("user.dir")
                + "/screenshots/signUp.png";

        imagePart.attachFile(new File(imagePaths));
        
//        imagePart.attachFile(new File("/automation-framework/screenshots/signUp.png"));

        // ✅ MULTIPART EMAIL
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(imagePart);

        message.setContent(multipart);

        // Send email
        Transport.send(message);

        System.out.println("✅ Email with image sent successfully");
    }
}
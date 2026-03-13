package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String subject,
                                 String messageText) throws Exception {

        final String fromEmail = "vishalevoke27@gmail.com";
        final String password = "wvic hncq gbcj bxdx"; 
        final String toEmail = "vk368065@gmail.com";

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

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(messageText);

        MimeBodyPart imagePart = new MimeBodyPart();
        String imagePaths = System.getProperty("user.dir")
                + "/screenshots/signUp.png";

        imagePart.attachFile(new File(imagePaths));
                
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(imagePart);

        message.setContent(multipart);

        Transport.send(message);

    }
}
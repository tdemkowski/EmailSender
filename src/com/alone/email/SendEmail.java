
package com.alone.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
    private String user = "your_email@gmail.com";
    private String pass = "your_password";
    
    SendEmail(String to, String sub, String msg) {
        Properties prop = new Properties();
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() 
        {
           protected javax.mail.PasswordAuthentication getPasswordAuthentication() 
           {
               return new javax.mail.PasswordAuthentication(user, pass);
           }
        });
        
        // now to send email:
        try {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress("no-reply@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, InternetAddress.parse(to)[0]);
            message.setSubject(sub);
            message.setText(msg);
            
            Transport.send(message);
            
            System.out.println("Mail Sent");
            
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}

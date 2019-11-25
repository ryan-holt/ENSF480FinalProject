package Domain.Controllers;

import Utils.Listing;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

 /** This class is responsible for sending emails to given email addresses
 * @author Harsohail Brar
 * @version 4.10.0
 * @since November 25, 2019
 */
public class EmailSender {

    private static int num = 0;

     /**
      * Sets up PRMS email account that will send emails
      * @param recepient email for recepient
      * @param clientMessage message from sender
      * @param clientEmail email of sender
      * @param listing  listing info contained in email
      * @throws MessagingException
      */
    public void sendMail(String recepient, String clientMessage, String clientEmail, Listing listing)throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "ENSF480PRMS@gmail.com";
        String password = "fall2019ensf480";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient, clientMessage, clientEmail, listing);

        Transport.send(message);
        System.out.println("Message sent successfully!");
    }

     /**
      * Sends email to the recepient email
      */
    public Message prepareMessage(Session session, String myAccountEmail, String recepient, String clientMessage, String clientEmail, Listing listing){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("DO NOT REPLY PRMS Property Email");
            message.setText("Email from: " + clientEmail + "\n" +
                            "---------------------" + "\n" +
                            "For Listing ID: " + listing.getListingID() + "\n" +
                            "Type: " + listing.getType() + "\n"+
                            "Bedrooms: " + listing.getNumOfBedrooms() + "\n"+
                            "Bathrooms: " + listing.getNumOfBathrooms() + "\n"+
                            "Furnished: " + listing.isFurnishedString() + "\n"+
                            "Quadrant: " + listing.getQuadrant() + "\n"+
                            "Address: " + listing.getAddress() + "\n" +
                            "---------------------" + "\n" +
                            clientMessage + "\n");
            num++;
            return message;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
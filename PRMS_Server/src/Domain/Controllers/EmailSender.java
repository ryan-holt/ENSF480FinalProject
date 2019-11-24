package Domain.Controllers;

import Utils.Listing;

import java.net.InetAddress;
import java.util.*;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailSender {

    private static int num = 0;

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
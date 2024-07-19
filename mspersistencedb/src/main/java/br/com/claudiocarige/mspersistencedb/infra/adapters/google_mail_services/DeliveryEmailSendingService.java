package br.com.claudiocarige.mspersistencedb.infra.adapters.google_mail_services;

import jakarta.mail.MessagingException;


public interface DeliveryEmailSendingService {

    void sendEmail( String to, String subject, String body ) throws MessagingException;

}

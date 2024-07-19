package br.com.claudiocarige.mspersistencedb.infra.adapters.google_mail_services.impl;

import br.com.claudiocarige.mspersistencedb.core.usecases.CustomerService;
import br.com.claudiocarige.mspersistencedb.infra.adapters.google_mail_services.DeliveryEmailSendingService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryEmailSendingServiceImpl implements DeliveryEmailSendingService {

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine templateEngine;

    private final CustomerService customerService;

    @Autowired
    public DeliveryEmailSendingServiceImpl( JavaMailSender javaMailSender,
                                            SpringTemplateEngine templateEngine,
                                            CustomerService customerService ) {

        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.customerService = customerService;
    }

    @Override
    public void sendEmail( String toEmail, String subject, String data ) throws MessagingException {

        log.info( "Sending email to: " + toEmail );
        try {
            var message = createEmail( toEmail, subject, data);
            javaMailSender.send( message );
        } catch( MessagingException e ) {
            log.error( "Error sending the email: " + e.getMessage() );
            throw new MessagingException( "Error sending the email: ", e );
        }
    }

    private MimeMessage createEmail( String toEmail, String subject, String data ) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper( message, true, "UTF-8" );
        helper.setTo( toEmail );
        helper.setSubject( subject.toUpperCase() );
        var emailContent = createEmailBody( toEmail, subject, data);
        helper.setText( emailContent, true );
        return helper.getMimeMessage();
    }

    private String createEmailBody( String email, String subject, String data  ) {

        Context context = new Context();
        context.setVariable("data", data);
        context.setVariable( "name", customerService.findByPrimaryEmail( email ).getResponsibleEmployee() );

        var emailContent = templateEngine.process( "emails/" + subject + ".html", context );
        return emailContent;
    }

}

package br.com.maquiagemimportada.portal.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.maquiagemimportada.portal.domain.Usuario;

@Component
public class MailUtil {
	
	/**
	 * gteesjremzqqixlx
	 * @throws MessagingException
	 * @throws IOException
	 */
	@Async
	public void enviar(TemplateEngine te) throws MessagingException, IOException{
		
		Context context = new Context();
		String corpoEmail = te.process("mail/teste", context);
		
		//final String username = "ssseara@gmail.com";
		final String username = "lobatocabral";
        //final String password = "gteesjremzqqixlx";
		//final String password = "gljkyatdgpvyumep";
		final String password = "Vicenzo010902";

        Properties prop = new Properties();
		//prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.host", "smtp.sendgrid.net");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("contato@maquiagemimportada.com.br"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("ssseara@gmail.com")
            );
            message.setSubject("Testing Gmail SSL");
            message.setText(corpoEmail);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	@Async
	public void enviarConfirmacaoEmail(Usuario usuario, TemplateEngine te) throws MessagingException, IOException{
		Context context = new Context();
		context.setVariable("logo", "logo");
		context.setVariable("hash", usuario.getHash());
		String corpoEmail = te.process("cadastro/emailConfirmacao", context);
		
		final String username = "lobatocabral";
        final String password = "Vicenzo010902";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.sendgrid.net");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            MimeMessage message = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(new InternetAddress("contato@maquiagemimportada.com.br"));
            helper.setTo(usuario.getEmail());
            helper.setSubject("Confirmação de Email");
            helper.setText(corpoEmail,true);
            
            helper.addInline("logo", new ClassPathResource("static/images/Logo_MaquiagemImportada.png"));

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
}

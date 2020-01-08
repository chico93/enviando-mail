package enviando.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class AppTest { 

	private String userName = "chico.rabelo93@aol.com";
	private String senha= "***"; /* Configurar senha do email para o envio */

	@org.junit.Test
	public void testeEmail(){
		try {
			
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");/*Autorização*/
		properties.put("mail.smtp.starttls", "true"); /*Autenticação*/
		properties.put("mail.smtp.host", "smtp.aol.com"); /*Sercidor de email da aol */
		properties.put("mail.smtp.port", "465");/*Porta do servidor*/
		properties.put("mail.smtp.socketFactory.port", "465");/*Especifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao SMTP*/
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});
		
		
		Address[] toUser = InternetAddress.parse("felipe.rabelo93@outlook.com, felipe.rabelo93@hotmail.com, chico.rabelo@aol.com, felipe.rabelo@gmail.com"); /* Criar script para lista de usuarios desejados para o envio de emails em massa */
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName)); /*Quem esta ednviando*/
		message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
		message.setSubject("Chegou e-mail enviado com java");/*Assunto do e-mail*/
		message.setText("Olá, vc acaba de receber um e-mail enviado com Java!");
		
		
		Transport.send(message);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}

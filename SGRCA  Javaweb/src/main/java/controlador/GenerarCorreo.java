package controlador;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Session;

public class GenerarCorreo {

	public void EnviarCorreo(String asunto, Object mensaje) throws MessagingException {

		// Configuración del servidor SMTP
		String host = "smtp.gmail.com";
		String port = "587";

		final String username = "davidtorrest8564@gmail.com"; // correo
		final String password = "ddkh ygix yyru natq"; // clave de aplicacion

		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Autenticación
		 Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			// Crear el mensaje
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("davidtorrest8564@gmail.com")); //Destinatario
			message.setSubject(asunto); // Asunto
			message.setText((String) mensaje); //Cuerpo del mesaje
			
			// Enviar
			Transport.send(message);
			System.out.println("Correo enviado correctamente");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("Error al enviar correo: " + e.getMessage());
		}
		
		

	}

}

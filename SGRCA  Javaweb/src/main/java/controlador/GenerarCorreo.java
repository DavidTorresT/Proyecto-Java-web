package controlador;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import modelo.DocumentoMaestro;
import jakarta.mail.Session;

/* En la clase GenerarCorreo se crea el metodo EnviarCorreo que recibe dos parametros (asunto y mensaje) dentro del metodo
 * se crean dos variables (host y port) para configurar el servidor smtp que es un servicio de correo, se crean otras dos
 * variables (username y password) para asignarle el correo de donde va a ser eviado y la clave de aplicacion con la cual
 * le permitira al programa solo enviar correo.
 * 
 * Se instancia un objeto de la clase Properties (props) donde se configura el servidor luego invocamos un objeto de la 
 * clase Session (session) donde por medio del metodo getPasswordAuthentication de la clase PasswordAuthentication
 * se hace una autenticacion de usuario y contraseña.
 * 
 * Luego se abre un try donde se invoca la clase Message y al metodo MimeMessage de la libreria jakarta.mail 
 * con el parametro (session), luego se le agrega el destinatario a quien va a llegar el correo, el asunto y el mensaje 
 * ya definidos como parametros del metodo EnviarCorreo.
 * 
 * Despues se envia el mensaje y se muestra un mensaje de exito en consola, en caso de haber un error se muestra en consola*/
public class GenerarCorreo {

	public void EnviarCorreo(String asunto, DocumentoMaestro mensaje) throws MessagingException {

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
			message.setText("Codigo: " +mensaje.getCodigo() + "\nNombre: " + mensaje.getNombre() + "\nTamaño: " + mensaje.getTamanio() + "\nRuta: " + mensaje.getRuta() + "\nExtension: " + mensaje.getExt()); //Cuerpo del mesaje
			
			// Enviar
			Transport.send(message);
			System.out.println("Correo enviado correctamente");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("Error al enviar correo: " + e.getMessage());
		}
		
		

	}

}

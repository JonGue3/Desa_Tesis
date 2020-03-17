package com.tesis.v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
@Service
public class SendMailService {
    @Autowired
    private  PropertyService propertyService;

    @Transactional
    public void enviarMailEdicion(@ModelAttribute(value = "userEmail") String email, String asuntoCorreo,
                                  String contenidoCorreo, String fecha,
                                  ByteArrayOutputStream byteArrayOutputStream) throws  MessagingException {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
        LocalTime time = LocalTime.now();
        String localTimeNowFormat = formatter.format(time);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String url = request.getScheme() + "://" + request.getServerName();
            sendSimpleMail( url,email, asuntoCorreo,   contenidoCorreo, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Use it to send Simple text emails
    @Transactional
    public void sendSimpleMail(String url,
                               String correoDestinatario, String asuntoCorreo,
                               String contenidoCorreo,
                               ByteArrayOutputStream byteArrayOutputStream) throws MessagingException {

        Properties props = null;
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
        LocalTime time = LocalTime.now();
        String localTimeNowFormat = formatter.format(time);

        try {
            props = propertyService.openProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Session session = Session.getDefaultInstance(props, null);
        BodyPart texto = new MimeBodyPart();
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(texto);//Se agrega el cuerpo del mensaje.
        MimeMessage mensaje = new MimeMessage(session);
        // Se rellena el From
        mensaje.setFrom(new InternetAddress("tcs.sistema.logistico@gmail.com"));
        // Se rellenan los destinatarios
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));

        try {
            String token = contenidoCorreo;
            contenidoCorreo = propertyService.getCuerpoCorreoRecuperarClave().concat("\n\n");
            contenidoCorreo = contenidoCorreo.concat(token).concat("\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        texto.setText(contenidoCorreo);


        mensaje.setSubject(asuntoCorreo);
        mensaje.setContent(multiParte);

        Transport t = session.getTransport("smtp");
        String user = null;
        String password = null;
        try
        {
            user = propertyService.getCorreo();
            password = propertyService.getCorreoPassword();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        t.connect(user,password);
        t.sendMessage(mensaje,mensaje.getAllRecipients());
        t.close();
    }




}

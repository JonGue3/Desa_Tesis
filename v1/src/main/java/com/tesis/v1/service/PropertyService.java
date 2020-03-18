package com.tesis.v1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Service
public class PropertyService {
    private InputStream inputStream;
    private String result = "";

    public Properties openProperties() throws IOException {
        Properties prop = null;

        try {
            prop = new Properties();
            String popFileName = "application.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(popFileName);


            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + popFileName + "' not found in the classpath");
            }


            // get the property value and print it out


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }

        return prop;
    }
    public Properties openMessageProperty() throws IOException {

        Properties prop = null;

        try {
            prop = new Properties();
            String messagePropFileName = "messages.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(messagePropFileName);


            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + messagePropFileName + "' not found in the classpath");
            }


            // get the property value and print it out


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }

        return prop;
    }
    @Transactional
    public String getIpUrl() throws IOException {

        Properties prop = openProperties();

        String ipUrl = prop.getProperty("spring.server.ip");
        String result = ipUrl;

        return result;
    }

    @Transactional
    public String getCorreo() throws IOException {

        Properties prop = openProperties();
        String contenidoCorreo = prop.getProperty("mail.smtp.user");
        result = contenidoCorreo;

        return result;
    }
    @Transactional
    public String getCorreoPassword() throws IOException {

        Properties prop = openProperties();
        String contenidoCorreo = prop.getProperty("mail.smtp.password");
        result = contenidoCorreo;

        return result;
    }

    @Transactional
    public String getCuerpoCorreoRecuperarClave() throws IOException {

        Properties prop = openMessageProperty();
        String contenidoCorreo = prop.getProperty("mensaje.correo.cuerpo.recuperar.clave");
        result = contenidoCorreo;

        return result;
    }


}

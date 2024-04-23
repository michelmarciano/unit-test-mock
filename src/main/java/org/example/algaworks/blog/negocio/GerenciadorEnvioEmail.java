package org.example.algaworks.blog.negocio;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.example.algaworks.blog.modelo.Notificacao;

public class GerenciadorEnvioEmail implements GerenciadorNotificacao{

    @Override
    public void enviar(Notificacao notificacao) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("algatestes.algaworks", ""));
            email.setSSLOnConnect(true);
            email.setFrom("algatestes@gmail.com");
            email.setSubject("");
            email.setMsg("");
            email.addTo("");
            email.send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
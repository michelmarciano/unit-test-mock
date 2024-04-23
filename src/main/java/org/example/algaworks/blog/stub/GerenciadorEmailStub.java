package org.example.algaworks.blog.stub;

import org.example.algaworks.blog.modelo.Notificacao;
import org.example.algaworks.blog.negocio.GerenciadorNotificacao;

public class GerenciadorEmailStub implements GerenciadorNotificacao {

    @Override
    public void enviar(Notificacao notificacao) {
        System.out.println("Enviando Email");
    }
}

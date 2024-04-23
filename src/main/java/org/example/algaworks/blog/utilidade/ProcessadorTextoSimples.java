package org.example.algaworks.blog.utilidade;


import org.example.algaworks.blog.negocio.ProcessadorTexto;

public class ProcessadorTextoSimples implements ProcessadorTexto {

    @Override
    public int quantidadePalavras(String texto) {
        if (texto == null || texto.isBlank()) {
            return 0;
        }
        return texto.split(" ").length;
    }
}

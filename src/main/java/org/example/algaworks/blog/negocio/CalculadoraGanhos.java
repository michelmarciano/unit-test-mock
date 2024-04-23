package org.example.algaworks.blog.negocio;


import org.example.algaworks.blog.modelo.Editor;
import org.example.algaworks.blog.modelo.Ganhos;
import org.example.algaworks.blog.modelo.Post;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculadoraGanhos {

    private final ProcessadorTexto processadorTexto;
    private final BigDecimal bonusPremium;

    public CalculadoraGanhos(ProcessadorTexto processadorTexto,
                             BigDecimal bonusPremium) {
        Objects.requireNonNull(processadorTexto);
        Objects.requireNonNull(bonusPremium);
        this.processadorTexto = processadorTexto;
        this.bonusPremium = bonusPremium;
    }

    public Ganhos calcular(Post post) {
        Objects.requireNonNull(post);
        Editor autor = post.getAutor();
        Objects.requireNonNull(autor);

        BigDecimal valorPagoPorPalavra = autor.getValorPagoPorPalavra();
        int quantidadePalavras = processadorTexto.quantidadePalavras(post.getConteudo());
        BigDecimal totalGanho = valorPagoPorPalavra.multiply(BigDecimal.valueOf(quantidadePalavras));

        if (post.getAutor().isPremium()) {
            totalGanho = totalGanho.add(bonusPremium);
        }

        return new Ganhos(valorPagoPorPalavra, quantidadePalavras, totalGanho);
    }
}


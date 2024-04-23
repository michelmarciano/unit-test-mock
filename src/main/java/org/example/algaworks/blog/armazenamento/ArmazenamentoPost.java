package org.example.algaworks.blog.armazenamento;


import org.example.algaworks.blog.modelo.Post;

import java.util.List;
import java.util.Optional;

public interface ArmazenamentoPost {
    Post salvar(Post post);
    Optional<Post> encontrarPorId(Long post);
    void remover(Long postId);
    List<Post> encontrarTodos();
}

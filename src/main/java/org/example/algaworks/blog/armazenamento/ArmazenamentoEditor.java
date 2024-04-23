package org.example.algaworks.blog.armazenamento;


import org.example.algaworks.blog.modelo.Editor;

import java.util.List;
import java.util.Optional;

public interface ArmazenamentoEditor {
    Editor salvar(Editor editor);
    Optional<Editor> encontrarPorId(Long editor);
    Optional<Editor> encontrarPorEmail(String email);
    Optional<Editor> encontrarPorEmailComIdDiferenteDe(String email, Long id);
    void remover(Long editorId);
    List<Editor> encontrarTodos();
}
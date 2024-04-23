package org.example.algaworks.blog.stub;

import org.example.algaworks.blog.armazenamento.ArmazenamentoEditor;
import org.example.algaworks.blog.modelo.Editor;

import java.util.List;
import java.util.Optional;

public class ArmazenamentoEditorStub implements ArmazenamentoEditor {
    @Override
    public Editor salvar(Editor editor) {
        if (editor.getId() == null){
            editor.setId(1L);
        }
        return editor;
    }

    @Override
    public Optional<Editor> encontrarPorId(Long editor) {
        return Optional.empty();
    }

    @Override
    public Optional<Editor> encontrarPorEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Editor> encontrarPorEmailComIdDiferenteDe(String email, Long id) {
        return Optional.empty();
    }

    @Override
    public void remover(Long editorId) {

    }

    @Override
    public List<Editor> encontrarTodos() {
        return null;
    }
}

package org.example.algaworks.blog.negocio;



import org.example.algaworks.blog.armazenamento.ArmazenamentoEditor;
import org.example.algaworks.blog.exception.RegraNegocioException;
import org.example.algaworks.blog.modelo.Editor;
import org.example.algaworks.blog.modelo.Notificacao;

import java.time.OffsetDateTime;
import java.util.Objects;

public class CadastroEditor {

    private final ArmazenamentoEditor armazenamentoEditor;
    private final GerenciadorNotificacao gerenciadorEnvioEmail;

    public CadastroEditor(ArmazenamentoEditor armazenamentoEditor, GerenciadorNotificacao gerenciadorEnvioEmail) {
        this.armazenamentoEditor = armazenamentoEditor;
        this.gerenciadorEnvioEmail = gerenciadorEnvioEmail;
    }

    public Editor criar(Editor editor) {
        Objects.requireNonNull(editor);

        verificarSeExisteEditorUsandoMesmoEmail(editor);
        editor = armazenamentoEditor.salvar(editor);
        enviarEmailDeCadastro(editor);

        return editor;
    }

    public Editor editar(Editor editorAtualizado) {
        Objects.requireNonNull(editorAtualizado);

        verificarSeExisteEditorUsandoMesmoEmailComIdDiferente(editorAtualizado);
        Editor editor = encontrarEditor(editorAtualizado);
        editor.atualizarComDados(editorAtualizado);

        return armazenamentoEditor.salvar(editor);
    }

    public void remover(Long editorId) {
        Objects.requireNonNull(editorId);
        armazenamentoEditor.encontrarPorId(editorId).orElseThrow();
        armazenamentoEditor.remover(editorId);
    }

    private void verificarSeExisteEditorUsandoMesmoEmail(Editor editor) {
        if(armazenamentoEditor.encontrarPorEmail(editor.getEmail()).isPresent()) {
            throw new RegraNegocioException("Já existe um editor com esse e-mail " + editor.getEmail());
        }
    }

    private Editor encontrarEditor(Editor editorAtualizado) {
        return armazenamentoEditor.encontrarPorId(editorAtualizado.getId())
                .orElseThrow();
    }

    private void verificarSeExisteEditorUsandoMesmoEmailComIdDiferente(Editor editorAtualizado) {
        if(armazenamentoEditor.encontrarPorEmailComIdDiferenteDe(
                editorAtualizado.getEmail(),
                editorAtualizado.getId()).isPresent()) {
            throw new RegraNegocioException("Já existe um editor com esse e-mail " + editorAtualizado.getEmail());
        }

    }

    private void enviarEmailDeCadastro(Editor editor) {
        Notificacao notificacao = new Notificacao(OffsetDateTime.now(), editor.getEmail());
        gerenciadorEnvioEmail.enviar(notificacao);
    }
}


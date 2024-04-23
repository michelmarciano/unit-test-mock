package algaworks;

import org.example.algaworks.blog.armazenamento.ArmazenamentoEditor;
import org.example.algaworks.blog.modelo.Editor;
import org.example.algaworks.blog.negocio.CadastroEditor;
import org.example.algaworks.blog.negocio.GerenciadorEnvioEmail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class MockWhenTest {

    ArmazenamentoEditor armazenamentoEditor;
    GerenciadorEnvioEmail gerenciadorEmail;
    CadastroEditor cadastroEditor;
    Editor editor;

    @BeforeEach
    void init(){
        editor = new Editor(null, "Mike", "micke@gmailcom", BigDecimal.TEN, true);

        armazenamentoEditor = Mockito.mock(ArmazenamentoEditor.class);
        gerenciadorEmail = Mockito.mock(GerenciadorEnvioEmail.class);

        cadastroEditor = new CadastroEditor(armazenamentoEditor, gerenciadorEmail);
    }

    @Test
    void Dado_um_Editor_Valido_Quando_criar_Entao_deve_retornar_um_id_de_cadastro(){
        Mockito.when(armazenamentoEditor.salvar(editor))
                .thenReturn(new Editor(1L, "Mike", "micke@gmailcom", BigDecimal.TEN, true));

        Editor editorSalvo = cadastroEditor.criar(editor);
        Assertions.assertEquals(1L, editorSalvo.getId());

    }
}

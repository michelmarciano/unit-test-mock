package algaworks;

import org.example.algaworks.blog.armazenamento.ArmazenamentoEditor;
import org.example.algaworks.blog.modelo.Editor;
import org.example.algaworks.blog.modelo.Notificacao;
import org.example.algaworks.blog.negocio.CadastroEditor;
import org.example.algaworks.blog.negocio.GerenciadorEnvioEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MockVerifyTest {

    Editor editor;

    @Mock
    ArmazenamentoEditor armazenamentoEditor;

    @Mock
    GerenciadorEnvioEmail gerenciadorEmail;

    @InjectMocks
    CadastroEditor cadastroEditor;


    @BeforeEach
    void init(){
        editor = new Editor(null, "Mike", "micke@gmailcom", BigDecimal.TEN, true);
        Mockito.when(armazenamentoEditor.salvar(Mockito.any(Editor.class)))
                .thenReturn(new Editor(1L, "Mike", "micke@gmailcom", BigDecimal.TEN, true));
    }

    @Test
    void Dado_um_Editor_Valido_Quando_criar_Entao_deve_retornar_um_id_de_cadastro(){
        Editor editorSalvo = cadastroEditor.criar(editor);
        assertEquals(1L, editorSalvo.getId());
//        Mockito.verify(armazenamentoEditor, Mockito.times(1))
//                .salvar(Mockito.eq(editorSalvo));
        Mockito.verify(gerenciadorEmail, Mockito.times(1))
                .enviar(Mockito.any(Notificacao.class));
    }
}

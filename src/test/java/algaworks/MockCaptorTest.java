package algaworks;

import org.example.algaworks.blog.armazenamento.ArmazenamentoEditor;
import org.example.algaworks.blog.modelo.Editor;
import org.example.algaworks.blog.modelo.Notificacao;
import org.example.algaworks.blog.negocio.CadastroEditor;
import org.example.algaworks.blog.negocio.GerenciadorNotificacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class MockCaptorTest {

    @Captor
    ArgumentCaptor<Notificacao> notificacaoArgumentCaptor;

    Editor editor;

    @Mock
    ArmazenamentoEditor armazenamentoEditor;
    @Mock
    GerenciadorNotificacao gerenciadorEnvioEmail;

    @InjectMocks
    CadastroEditor cadastroEditor;

    @BeforeEach
    void init(){
        editor = new Editor(null, "mike", "mike.marciano@gmail.com", new BigDecimal(10), true);
        Mockito.when(armazenamentoEditor.salvar(Mockito.any(Editor.class)))
                .thenReturn(new Editor(1l, "mike", "mike.marciano@gmail.com", new BigDecimal(10), true));
    }

    @Test
    void Dado_um_editor_Quando_salvar_Entao_Entao (){

        Editor editorSalvo = cadastroEditor.criar(editor);

        Mockito.verify(gerenciadorEnvioEmail).enviar(notificacaoArgumentCaptor.capture());

        Notificacao mensagem = notificacaoArgumentCaptor.getValue();
        Assertions.assertEquals(editorSalvo.getEmail(), mensagem.getConteudo());

    }
}

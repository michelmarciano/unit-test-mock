package algaworks;


import org.example.algaworks.blog.armazenamento.ArmazenamentoEditor;
import org.example.algaworks.blog.modelo.Editor;
import org.example.algaworks.blog.modelo.Notificacao;
import org.example.algaworks.blog.negocio.CadastroEditor;
import org.example.algaworks.blog.negocio.GerenciadorNotificacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class MockSpyTest {

    @Captor
    ArgumentCaptor<Notificacao> notificacaoArgumentCaptor;

    @Spy
    Editor editor = new Editor(null, "mike", "mike.marciano@gmail.com", new BigDecimal(10), true);

    @Mock
    ArmazenamentoEditor armazenamentoEditor;
    @Mock
    GerenciadorNotificacao gerenciadorEnvioEmail;

    @InjectMocks
    CadastroEditor cadastroEditor;

    @BeforeEach
    void init(){
        Mockito.when(armazenamentoEditor.salvar(Mockito.any(Editor.class)))
                .thenReturn(new Editor(1l, "mike", "mike.marciano@gmail.com", new BigDecimal(10), true));
    }

    @Test
    void Dado_um_editor_valido_Quando_cadastrar_Entao_deve_verificar_email(){

        cadastroEditor.criar(editor);

        Mockito.verify(editor, Mockito.times(1)).getEmail();
    }
}

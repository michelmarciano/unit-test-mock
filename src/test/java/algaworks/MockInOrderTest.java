package algaworks;


import org.example.algaworks.blog.armazenamento.ArmazenamentoEditor;
import org.example.algaworks.blog.modelo.Editor;
import org.example.algaworks.blog.modelo.Notificacao;
import org.example.algaworks.blog.negocio.CadastroEditor;
import org.example.algaworks.blog.negocio.GerenciadorNotificacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class MockInOrderTest {

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
    void Dado_um_editor_valido_Quando_cadastrar_Entao_deve_enviar_email_apos_salvar(){

        cadastroEditor.criar(editor);

        InOrder inOrder = Mockito.inOrder(armazenamentoEditor, gerenciadorEnvioEmail);
        inOrder.verify(armazenamentoEditor, Mockito.times(1)).salvar(editor);
        inOrder.verify(gerenciadorEnvioEmail, Mockito.times(1)).enviar(Mockito.any(Notificacao.class));
    }
}

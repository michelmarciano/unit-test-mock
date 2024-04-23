package algaworks;

import org.example.algaworks.blog.armazenamento.ArmazenamentoEditor;
import org.example.algaworks.blog.modelo.Editor;
import org.example.algaworks.blog.negocio.CadastroEditor;
import org.example.algaworks.blog.negocio.GerenciadorEnvioEmail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class MockExceptionTest {

    Editor editor;
    @Mock
    ArmazenamentoEditor armazenamentoEditor;
    @Mock
    GerenciadorEnvioEmail gerenciadorEnvioEmail;
    @InjectMocks
    CadastroEditor cadastroEditor;

    @BeforeEach
    void initial(){
        editor = new Editor(null, "mike", "mike1984@gmail.com", new BigDecimal(1), true);
        Mockito.when(armazenamentoEditor.salvar(editor))
                .thenReturn(new Editor(1L, "mike", "mike1984@gmail.com", new BigDecimal(1), true));
    }

    @Test
    void Dado_um_editor_Quando_criar_e_lancar_uma_exception_ao_salvar_Entao_nao_deve_enviar_email(){
        Mockito.when(armazenamentoEditor.salvar(editor)).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> cadastroEditor.criar(editor));
        Mockito.verify(gerenciadorEnvioEmail, Mockito.never()).enviar(Mockito.any());
    }
}

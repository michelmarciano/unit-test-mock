package algaworks;

import org.assertj.core.api.Assertions;
import org.example.algaworks.blog.armazenamento.ArmazenamentoEditor;
import org.example.algaworks.blog.modelo.Editor;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MockParametrosDinamicosTest {

    @Mock
    ArmazenamentoEditor armazenamentoEditor;

    @Mock
    GerenciadorEnvioEmail gerenciadorEmail;

    @InjectMocks
    CadastroEditor cadastroEditor;


    @BeforeEach
    void init(){
        Mockito.when(armazenamentoEditor.salvar(Mockito.any(Editor.class)))
                .thenReturn(new Editor(1L, "Mike", "micke@gmailcom", BigDecimal.TEN, true));
    }

    @Test
    void Dado_um_Editor_Valido_Quando_criar_Entao_deve_retornar_um_id_de_cadastro(){
        Editor editorSalvo = cadastroEditor.criar(new Editor());
        assertEquals(1L, editorSalvo.getId());
        Assertions.assertThat(editorSalvo.getId()).isEqualTo(1L);
    }
}

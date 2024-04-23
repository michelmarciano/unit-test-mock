package algaworks;


import org.example.algaworks.blog.modelo.Editor;
import org.example.algaworks.blog.negocio.CadastroEditor;
import org.example.algaworks.blog.stub.ArmazenamentoEditorStub;
import org.example.algaworks.blog.stub.GerenciadorEmailStub;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StubTest {

    static CadastroEditor cadastroEditor;
    static Editor editor;

    @BeforeAll
    static void setup() {
        cadastroEditor = new CadastroEditor(new ArmazenamentoEditorStub(), new GerenciadorEmailStub());
        editor = new Editor(1L, "Mike", "michel.marciano1984@gmail.com", BigDecimal.TEN, true);
    }

    @Test
    public void Dado_um_editor_valido_Quando_criar_Entao_deve_retornar_um_id_de_cadastro() {
        Editor editorSalvo = cadastroEditor.criar(editor);
        Assertions.assertEquals(1L, editorSalvo.getId());

    }
}

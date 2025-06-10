
import Controller.UsuarioDAO;
import Model.Usuario;
import java.io.InputStream;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.Assertion;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioDAOTest {

    private IDatabaseTester jdt;

    @Before
    public void setUp() throws Exception {
        jdt = new JdbcDatabaseTester(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/competente",
                "root",
                ""
        );

        InputStream is = getClass().getResourceAsStream("/Datasets/dataset_inicial.xml");
        if (is == null) {
            throw new IllegalStateException("Arquivo dataset_inicial.xml não encontrado!");
        }
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(is);

        jdt.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        jdt.setDataSet(dataSet);
        jdt.onSetup();
    }

    @Test
    public void inserir() throws Exception {
        Usuario u = new Usuario("gabriel", 123, 1, "email.com", "123");
        UsuarioDAO.inserir(u);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("usuario");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/UsuarioDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("usuario");

        Assertion.assertEquals(expectedTable, currentTable);
    }

    @Test
    public void verificarRG() throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/UsuarioDAOVerificarRG.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(jdt.getConnection(), dataSet);

        boolean existe = UsuarioDAO.verificarRG(121);
        assertTrue(existe);

        boolean naoExiste = UsuarioDAO.verificarRG(999);
        assertFalse(naoExiste);
    }

    @Test
    public void verificarLogin() throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/UsuarioDAOVerificarLogin.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(jdt.getConnection(), dataSet);

        boolean loginCorreto = UsuarioDAO.verificarLogin("123", "senha123");
        assertTrue(loginCorreto);

        boolean loginIncorreto = UsuarioDAO.verificarLogin("123", "senhaErrada");
        assertFalse(loginIncorreto);

        boolean usuarioInexistente = UsuarioDAO.verificarLogin("999", "qualquerSenha");
        assertFalse(usuarioInexistente);
    }

}

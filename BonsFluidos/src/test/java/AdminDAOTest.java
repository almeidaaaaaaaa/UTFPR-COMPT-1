
import Controller.AdminDAO;
import Controller.UsuarioDAO;
import Controller.VoluntarioDAO;
import Model.Admin;
import java.io.InputStream;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.Assertion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class AdminDAOTest {

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
        Admin a = new Admin(1, "joao", 123, 0, "joao@email.com", "123");
        UsuarioDAO.inserir(a);
        AdminDAO.inserir(a);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("administrador");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/AdminDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("administrador");

        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"Adm_cod"});
    }

    @Test
    public void excluir() throws Exception {
        Admin a = new Admin(1, "joao", 123, 0, "joao@email.com", "123");

        UsuarioDAO.inserir(a);
        AdminDAO.inserir(a);

        AdminDAO.excluir(a.getCod());

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("administrador");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/dataset_inicial.xml"));
        ITable expectedTable = expectedDataSet.getTable("administrador");

        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"Adm_cod"});
    }

    @Test
    public void atualizar() throws Exception {
        Admin a = new Admin(1, "joao", 123, 0, "joao@email.com", "123");

        UsuarioDAO.inserir(a);
        AdminDAO.inserir(a);

        Admin aAtualizado = new Admin(1, "joao", 123, 1, "joao@email.com", "123");

        AdminDAO.atualizar(aAtualizado);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("administrador");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/AdminDAOAtualizar.xml"));
        ITable expectedTable = expectedDataSet.getTable("administrador");

        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"Adm_cod"});
    }

    @Test
    public void buscarPorRG_deveRetornarAdmin_quandoExistir() throws Exception {
        Admin a = new Admin(1, "joao", 123, 0, "joao@email.com", "123");

        UsuarioDAO.inserir(a);
        AdminDAO.inserir(a);
        int rgBuscado = 123;

        Admin adminEncontrado = AdminDAO.buscarPorRG(rgBuscado);

        assertNotNull("Deve retornar um Admin para o RG informado", adminEncontrado);
        assertEquals(rgBuscado, adminEncontrado.getRG());

        // Teste alguns outros campos para garantir que veio certo
        assertEquals("joao", adminEncontrado.getNome());
        assertEquals(1, adminEncontrado.getIdMestre());
        assertEquals("joao@email.com", adminEncontrado.getEmail());
        // E assim por diante conforme o seu construtor e getters
    }

    @Test
    public void buscarPorRG_deveRetornarNull_quandoNaoExistir() throws Exception {
        int rgInexistente = 99999; // Um RG que não existe no seu dataset

        Admin adminEncontrado = AdminDAO.buscarPorRG(rgInexistente);

        assertNull("Deve retornar null quando não encontrar o Admin pelo RG", adminEncontrado);
    }
}

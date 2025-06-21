import Controller.UsuarioDAO;
import Controller.VoluntarioDAO;
import Model.Voluntario;
import java.io.InputStream;
import java.time.LocalDateTime;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.Assertion;
import org.junit.Before;
import org.junit.Test;

public class VoluntarioDAOTest {

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
        Voluntario v = new Voluntario(LocalDateTime.of(2025, 6, 4, 10, 0), "pacheco", 2, 0, "pachec@email.com", "123");
        UsuarioDAO.inserir(v);
        VoluntarioDAO.inserir(v);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("voluntario");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/VoluntarioDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("voluntario");

        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[] {"Vol_cod"});
    }
    
    @Test
    public void excluir() throws Exception {
        Voluntario v = new Voluntario(
            LocalDateTime.of(2024, 1, 1, 9, 0),
            "Gabriel",
            123456,
            1,
            "gabriel@email.com",
            "123"
        );

        Controller.UsuarioDAO.inserir(v);
        VoluntarioDAO.inserir(v);
        VoluntarioDAO.excluir(v.getCod());

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("voluntario");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/dataset_inicial.xml"));
        ITable expectedTable = expectedDataSet.getTable("voluntario");

        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[] {"Vol_cod"});
    }
    
    
    @Test
    public void atualizar() throws Exception {
        Voluntario v = new Voluntario(
            LocalDateTime.of(2024, 1, 1, 9, 0),
            "Gabriel",
            123456,
            1,
            "gabriel@email.com",
            "123"
        );

        Controller.UsuarioDAO.inserir(v);
        VoluntarioDAO.inserir(v);

        // Atualização: altera apenas a dataE neste caso
        Voluntario atualizado = new Voluntario(
            LocalDateTime.of(2025, 6, 16, 12, 0),   // nova dataE
            "Gabriel",
            123456,
            1,
            "gabriel@email.com",
            "123"
        );
        atualizado.setCod(v.getCod());
        
        VoluntarioDAO.atualizar(atualizado);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("voluntario");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/VoluntarioDAOAtualizar.xml"));
        ITable expectedTable = expectedDataSet.getTable("voluntario");

        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[] {"Vol_cod"});
    }
}

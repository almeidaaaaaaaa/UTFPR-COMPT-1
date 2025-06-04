import Controller.UsuarioDAO;
import Model.Usuario;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.Assertion;
import org.junit.Before;
import org.junit.Test;

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

        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("Datasets/dataset_inicial.xml"));
        
        jdt.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        jdt.setDataSet(dataSet);
        jdt.onSetup();
    }

    @Test
    public void inserir() throws Exception {
        Usuario u = new Usuario("gabriel", 123, 1, "email.com");
        UsuarioDAO.inserir(u);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("usuario");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/UsuarioDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("usuario");

        Assertion.assertEquals(expectedTable, currentTable);
    }
}

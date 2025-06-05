import Controller.UsuarioDAO;
import Controller.VoluntarioDAO;
import Model.Voluntario;
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
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("Datasets/dataset_inicial.xml"));
        
        jdt.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        jdt.setDataSet(dataSet);
        jdt.onSetup();
    }

    @Test
    public void inserir() throws Exception {
        Voluntario v = new Voluntario(1, LocalDateTime.of(2025, 6, 4, 10, 0), "pacheco", 2, 0, "pachec@email.com");
        UsuarioDAO.inserir(v);
        VoluntarioDAO.inserir(v);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("voluntario");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/VoluntarioDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("voluntario");

        Assertion.assertEquals(expectedTable, currentTable);
    }
}

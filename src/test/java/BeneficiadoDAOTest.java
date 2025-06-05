import Controller.BeneficiadoDAO;
import Controller.UsuarioDAO;
import Model.Beneficiado;
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

public class BeneficiadoDAOTest {

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
        Beneficiado b = new Beneficiado(1, LocalDateTime.of(2025, 6, 4, 10, 0), "rua tal", "almeida",123, 0, "almeida@email.com");
        UsuarioDAO.inserir(b);
        BeneficiadoDAO.inserir(b);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("beneficiado");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/BeneficiadoDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("beneficiado");

        Assertion.assertEquals(expectedTable, currentTable);
    }
}

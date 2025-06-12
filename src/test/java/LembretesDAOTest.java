
import Controller.LembretesDAO;
import Model.Lembretes;
import java.io.InputStream;
import java.time.LocalDateTime;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class LembretesDAOTest {
    
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
    public void testInserir() throws Exception {
        Lembretes lembrete = new Lembretes(
            "Recado", 
            LocalDateTime.of(2023, 12, 15, 16, 30), 
            1
        );
        LembretesDAO.inserir(lembrete);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("lembrete");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/LembretesDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("lembrete");

        Assertion.assertEquals(expectedTable, currentTable);
    }

     @Test
    public void testAtualizar() throws Exception {
        Lembretes lembrete = new Lembretes(
            "Recado", 
            LocalDateTime.of(2023, 12, 15, 16, 30), 
            1
        );
        LembretesDAO.inserir(lembrete);
        
        Lembretes lembreteA = new Lembretes(
            "Recado diferente", 
            LocalDateTime.of(2023, 12, 15, 16, 30), 
            1
        );

        // Chama o método para atualizar
        LembretesDAO.atualizar(lembreteA);

        // Verifica se a atualização foi bem sucedida
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("lembrete");

        // Carrega o dataset com o resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/LembretesDAOAtualizar.xml"));
        ITable expectedTable = expectedDataSet.getTable("lembrete");

        // Compara o resultado atual com o esperado
        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    
    @Test
    public void excluir() throws Exception {
        Lembretes lembrete = new Lembretes(
            "Recado", 
            LocalDateTime.of(2023, 12, 15, 16, 30), 
            1
        );
        LembretesDAO.inserir(lembrete);
        
        LembretesDAO.excluir(1);

        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("lembrete");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/dataset_inicial.xml"));
        ITable expectedTable = expectedDataSet.getTable("lembrete");

        Assertion.assertEquals(expectedTable, currentTable);
    }}

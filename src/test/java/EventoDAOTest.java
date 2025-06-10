import Controller.EventoDAO;
import Model.Evento;
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

public class EventoDAOTest {

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
        Evento evento = new Evento(
            1, 
            "Palestrante Exemplo", 
            LocalDateTime.of(2023, 12, 15, 14, 30), 
            LocalDateTime.of(2023, 12, 15, 16, 30), 
            "Auditório Principal", 
            50, 
            1
        );
        EventoDAO.inserir(evento);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("evento");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/EventoDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("evento");

        Assertion.assertEquals(expectedTable, currentTable);
    }
     @Test
    public void testAtualizar() throws Exception {
        Evento evento = new Evento(
            1, 
            "Palestrante Exemplo", 
            LocalDateTime.of(2023, 12, 15, 14, 30), 
            LocalDateTime.of(2023, 12, 15, 16, 30), 
            "Auditório Principal", 
            50, 
            1
        );
        EventoDAO.inserir(evento);
        
        Evento eventoA = new Evento(
            1, // ID do evento a ser atualizado
            "Novo Palestrante",
            LocalDateTime.of(2023, 12, 20, 15, 0), // nova data
            LocalDateTime.of(2023, 12, 20, 17, 0), // novo horário
            "Novo Local",
            75, // novas vagas
            2   // novo tipo
        );

        // Chama o método para atualizar
        EventoDAO.atualizar(eventoA);

        // Verifica se a atualização foi bem sucedida
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("evento");

        // Carrega o dataset com o resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/EventoDAOAtualizar.xml"));
        ITable expectedTable = expectedDataSet.getTable("evento");

        // Compara o resultado atual com o esperado
        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    
    @Test
    public void excluir() throws Exception {
              Evento evento = new Evento(
            1, 
            "Palestrante Exemplo", 
            LocalDateTime.of(2023, 12, 15, 14, 30), 
            LocalDateTime.of(2023, 12, 15, 16, 30), 
            "Auditório Principal", 
            50, 
            1
        );
        EventoDAO.inserir(evento);
        
        EventoDAO.excluir(1);

        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("evento");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/EventoDAOExcluir.xml"));
        ITable expectedTable = expectedDataSet.getTable("evento");

        Assertion.assertEquals(expectedTable, currentTable);
    }
}

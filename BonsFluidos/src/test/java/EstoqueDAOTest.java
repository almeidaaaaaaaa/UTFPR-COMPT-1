
import Controller.EstoqueDAO;
import Controller.UsuarioDAO;
import Controller.VoluntarioDAO;
import Model.Estoque;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.Assertion;
import org.junit.Before;
import org.junit.Test;

public class EstoqueDAOTest {
    
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
            throw new IllegalStateException("Arquivo EstoqueDatasetInicial.xml não encontrado!");
        }
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(is);
        
        jdt.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        jdt.setDataSet(dataSet);
        jdt.onSetup();
    }
    
    @Test
    public void inserir() throws Exception {
        Estoque estoque = new Estoque(
                LocalDateTime.of(2024, 1, 1, 10, 0), // Est_dataE
                "produto x",
                14,
                LocalDateTime.of(2024, 1, 1, 9, 0), // Voluntario_dataE
                "Gabriel", // Usuario_nome
                123456, // Usuario_RG
                1, // Usuario_cargo
                "gabriel@email.com", // Usuario_email
                "123"
        );
        
        UsuarioDAO.inserir(estoque);
        VoluntarioDAO.inserir(estoque);
        
        EstoqueDAO.inserir(estoque);
        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("estoque");
        
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/EstoqueDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("estoque");
        
        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"Voluntario_Vol_cod", "Est_cod"});
    }
    
    @Test
    public void excluir() throws Exception {
        
        Estoque estoque = new Estoque(
                LocalDateTime.of(2024, 1, 1, 10, 0), // Est_dataE
                "produto x",
                14,
                LocalDateTime.of(2024, 1, 1, 9, 0), // Voluntario_dataE
                "Gabriel", // Usuario_nome
                123456, // Usuario_RG
                1, // Usuario_cargo
                "gabriel@email.com", // Usuario_email
                "123"
        );
        
        UsuarioDAO.inserir(estoque);
        VoluntarioDAO.inserir(estoque);
        
        EstoqueDAO.inserir(estoque);
        
        EstoqueDAO.excluir(estoque.getCodigo());
        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("estoque");
        
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/dataset_inicial.xml"));
        ITable expectedTable = expectedDataSet.getTable("estoque");
        
        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"Voluntario_Vol_cod", "Est_cod"});
    }
    
    @Test
    public void atualizar() throws Exception {
        Estoque estoque = new Estoque(
                LocalDateTime.of(2024, 1, 1, 10, 0), // Est_dataE
                "produto x",
                14,
                LocalDateTime.of(2024, 1, 1, 9, 0), // Voluntario_dataE
                "Gabriel", // Usuario_nome
                123456, // Usuario_RG
                1, // Usuario_cargo
                "gabriel@email.com", // Usuario_email
                "123"
        );
        
        UsuarioDAO.inserir(estoque);
        VoluntarioDAO.inserir(estoque);
        
        EstoqueDAO.inserir(estoque);
        
        Estoque estoque_at = new Estoque(
                LocalDateTime.of(2024, 1, 1, 10, 0), // Est_dataE
                "produto y",
                14,
                LocalDateTime.of(2024, 1, 1, 9, 0), // Voluntario_dataE
                "Gabriel", // Usuario_nome
                123456, // Usuario_RG
                1, // Usuario_cargo
                "gabriel@email.com", // Usuario_email
                "123"
        );
        estoque_at.setCodigo(estoque.getCodigo());
        EstoqueDAO.atualizar(estoque_at);
        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("estoque");
        
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/EstoqueDAOAtualizar.xml"));
        ITable expectedTable = expectedDataSet.getTable("estoque");
        
        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"Voluntario_Vol_cod", "Est_cod"});
    }
}

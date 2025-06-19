
import Controller.ProdutoDAO;
import Controller.EstoqueDAO;
import Controller.UsuarioDAO;
import Controller.VoluntarioDAO;
import Model.Produto;
import java.io.InputStream;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class ProdutoDAOTest {

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
        List<Model.Produto> produtos = new ArrayList<>();

        Produto produto = new Produto(
                1,
                10,
                "Produto Teste",
                1, // Est_cod
                LocalDateTime.of(2024, 1, 1, 10, 0), // Est_dataE
                LocalDateTime.of(2024, 1, 5, 10, 0), // Est_dataS
                "Destino Final", // Est_destino
                produtos, // Produtos
                10, // Voluntario_Vol_cod
                LocalDateTime.of(2024, 1, 1, 9, 0), // Voluntario_dataE
                "Gabriel", // Usuario_nome
                123456, // Usuario_RG
                1, // Usuario_cargo
                "gabriel@email.com", // Usuario_email
                "123"
        );

        UsuarioDAO.inserir(produto);
        VoluntarioDAO.inserir(produto);
        EstoqueDAO.inserir(produto);
        ProdutoDAO.inserir(produto);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("produto");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/ProdutoDAOInserir.xml"));
        ITable expectedTable = expectedDataSet.getTable("produto");

        Assertion.assertEquals(expectedTable, currentTable);
    }

    @Test
    public void testAtualizar() throws Exception {
        List<Model.Produto> produtos = new ArrayList<>();

        Produto produto = new Produto(
                1,
                10,
                "Produto Teste",
                1, // Est_cod
                LocalDateTime.of(2024, 1, 1, 10, 0), // Est_dataE
                LocalDateTime.of(2024, 1, 5, 10, 0), // Est_dataS
                "Destino Final", // Est_destino
                produtos, // Produtos
                10, // Voluntario_Vol_cod
                LocalDateTime.of(2024, 1, 1, 9, 0), // Voluntario_dataE
                "Gabriel", // Usuario_nome
                123456, // Usuario_RG
                1, // Usuario_cargo
                "gabriel@email.com", // Usuario_email
                "123"
        );

        UsuarioDAO.inserir(produto);
        VoluntarioDAO.inserir(produto);
        EstoqueDAO.inserir(produto);
        ProdutoDAO.inserir(produto);

        Produto produtoA = new Produto(
                1,
                20,
                "Produto Teste2",
                1, // Est_cod
                LocalDateTime.of(2025, 1, 1, 10, 0), // Est_dataE
                LocalDateTime.of(2025, 1, 5, 10, 0), // Est_dataS
                "Destino Final2", // Est_destino
                produtos, // Produtos
                20, // Voluntario_Vol_cod
                LocalDateTime.of(2024, 1, 1, 9, 0), // Voluntario_dataE
                "Gabriel", // Usuario_nome
                123456, // Usuario_RG
                1, // Usuario_cargo
                "gabriel@email.com", // Usuario_email
                "123"
        );

        // Chama o método para atualizar
        ProdutoDAO.atualizar(produtoA);

        // Verifica se a atualização foi bem sucedida
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("produto");

        // Carrega o dataset com o resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/ProdutoDAOAtualizar.xml"));
        ITable expectedTable = expectedDataSet.getTable("produto");

        // Compara o resultado atual com o esperado
        Assertion.assertEquals(expectedTable, currentTable);
    }

    @Test
    public void excluir() throws Exception {
        List<Model.Produto> produtos = new ArrayList<>();

        Produto produto = new Produto(
                1,
                10,
                "Produto Teste",
                1, // Est_cod
                LocalDateTime.of(2024, 1, 1, 10, 0), // Est_dataE
                LocalDateTime.of(2024, 1, 5, 10, 0), // Est_dataS
                "Destino Final", // Est_destino
                produtos, // Produtos
                10, // Voluntario_Vol_cod
                LocalDateTime.of(2024, 1, 1, 9, 0), // Voluntario_dataE
                "Gabriel", // Usuario_nome
                123456, // Usuario_RG
                1, // Usuario_cargo
                "gabriel@email.com", // Usuario_email
                "123"
        );

        UsuarioDAO.inserir(produto);
        VoluntarioDAO.inserir(produto);
        EstoqueDAO.inserir(produto);
        ProdutoDAO.inserir(produto);

        ProdutoDAO.excluir(1);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("produto");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
                .build(getClass().getResourceAsStream("/Datasets/dataset_inicial.xml"));
        ITable expectedTable = expectedDataSet.getTable("produto");

        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    
    @Test
public void buscarP() throws Exception {

    // Carrega o dataset XML com produto, estoque, voluntario e usuario
    IDataSet dataSet = new FlatXmlDataSetBuilder()
            .build(getClass().getResourceAsStream("/Datasets/ProdutoDAOBuscarP.xml"));
    DatabaseOperation.CLEAN_INSERT.execute(jdt.getConnection(), dataSet);

    ProdutoDAO dao = new ProdutoDAO();
    List<Object[]> produtos = dao.buscarP();

    // Esperado conforme o dataset.xml que você fez acima
    Object[][] esperado = {
        {1, 10, "Produto Teste", 1},
        {2, 10, "Produto teste 2", 1}
    };

    assertEquals("Quantidade de produtos retornados incorreta", esperado.length, produtos.size());

    for (int i = 0; i < esperado.length; i++) {
        assertEquals("ID incorreto no índice " + i, esperado[i][0], produtos.get(i)[0]);
        assertEquals("Quantidade incorreta no índice " + i, esperado[i][1], produtos.get(i)[1]);
        assertEquals("Nome incorreto no índice " + i, esperado[i][2], produtos.get(i)[2]);
        assertEquals("Código do estoque incorreto no índice " + i, esperado[i][3], produtos.get(i)[3]);
    }
}



}

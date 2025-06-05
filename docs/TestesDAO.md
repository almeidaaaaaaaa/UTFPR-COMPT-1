
# 🧪 Testes com JUnit + DBUnit (MySQL + XML)

---

## ✅ Pré-requisitos

- MySQL rodando localmente
- Tabelas criadas com a estrutura correta
- Dependências no `pom.xml`: JUnit, DBUnit, MySQL Connector

---

## 📁 Organização do projeto

- Crie uma pasta `Datasets` dentro de `src/test/resources/`
- Coloque nela os arquivos `.xml` que representarão os dados do banco
- Crie uma classe de teste com o nome do DAO que deseja testar, por exemplo: `AdminDAOTest`

---

## 🔄 Funcionamento do teste

1. Limpa o banco e insere dados iniciais com o arquivo `dataset_inicial.xml`
2. Executa o método que você deseja testar, como `AdminDAO.inserir(...)`
3. Compara o conteúdo atual da tabela com um XML esperado, como `AdminDAOInserir.xml`
4. Se estiverem iguais, o teste passa ✅. Se houver diferença, o teste falha ❌

---

## ✍️ Como criar os arquivos XML

Sempre que for testar uma nova tabela, adicione uma nova linha no `dataset_inicial.xml` com o nome dessa tabela — mesmo que ela esteja vazia.

### Exemplo de `dataset_inicial.xml` (estado inicial do banco)

```xml
<dataset>
    <usuario />
    <voluntario />
    <administrador />
    <beneficiado />
</dataset>
```

> **Dica:** Use esse XML como ponto de partida para manter o banco limpo antes de cada teste.

### Exemplo de `AdminDAOInserir.xml` (estado esperado do banco)

```xml
<dataset>
    <administrador Adm_idMestre="1" Usuario_Usu_rg="123" Adm_cod="1" />
    <usuario Usu_rg="123" Usu_nome="joao" Usu_cargo="0" Usu_email="joao@email.com" />
</dataset>
```

---

## ⚙️ O que acontece nos testes

### 🔹 Método `@Before setUp()`

Executado antes de cada teste. Ele:

- Conecta ao banco
- Carrega o `dataset_inicial.xml`
- Limpa o banco e insere os dados definidos no XML

> Isso garante que cada teste comece com o banco em um estado previsível.

### 🔹 Método de teste (ex: `inserir()`)

Exemplo resumido de um teste:

1. Cria um objeto de teste:
```java
Admin a = new Admin(1, 1, "joao", 123, 0, "joao@email.com");
```

2. Executa os métodos DAO:
```java
UsuarioDAO.inserir(a);
AdminDAO.inserir(a);
```

3. Captura o estado atual do banco:
```java
ITable currentTable = jdt.getConnection().createDataSet().getTable("administrador");
```

4. Carrega o XML esperado:
```java
ITable expectedTable = new FlatXmlDataSetBuilder()
        .build(getClass().getResourceAsStream("/Datasets/AdminDAOInserir.xml"))
        .getTable("administrador");
```

5. Compara os dois:
```java
Assertion.assertEquals(expectedTable, currentTable);
```

---

## ▶️ Como rodar os testes

- No **NetBeans**: clique com o botão direito sobre a classe de teste → **Run Test**
- Ou use o terminal com `mvn test`

---

## 🧠 Boas práticas

- Sempre use `CLEAN_INSERT` para garantir um banco limpo
- Certifique-se de que os nomes das tabelas e colunas nos XMLs estão corretos
- Escreva um teste para **cada tipo de operação** (inserção, atualização, remoção...)

---
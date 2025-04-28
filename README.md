# :telephone: Agenda de Contatos

Aplicativo Android simples para gerenciamento de contatos, utilizando Room como banco de dados local, padrão MVC, conceitos de POO e Padrões de Projeto.

## :zap: Padrões de Projeto e Princípios
- Factory: Utilizado para gerar uma fábrica de contatos
- Singleton: Utilizado para criar uma única instância do Banco de Dados
- Adapter:  Está adaptando os dados para serem mostrados na interface do usuário no caso "ListView"
- Strategy: Utilizado como forma de ordenação (busca de contatos)
- Builder: Utilizado para construir cada `AlertDialog`
- S (Single Responsability Principle): Utilizado para garantir que cada classe tenha uma única responsabilidade
- O (Open/Closed Principle): Utilizado para garantir que uma classe esteja aberta para extensão mas fechada para modificação
- D (Dependecy Inversion Principle): Utilizado para garantir as dependências em abstrações não em implementações

  
## :desktop_computer: Estrutura do Projeto
```
com.example.agendapoo2
├── DAO
│   ├── ContatoDAO.java
│
├── database
│   ├── BancoDados.java
│   ├── Singleton.java
│
├── model
│   ├── BuscaPorEmail.java
│   ├── BuscarPorTelefone.java
│   ├── BuscarPorNome.java
│   ├── BuscaStrategy.java
│   ├── Contato.java
│   ├── ContatoFactory.java
│
├── view
│   ├── ActivityNovoContato.java
│   ├── ContatoAdapter.java
│   ├── ContatoDetalheActivity.java
│   ├── MainActivity.java
│   ├── MensagemBuilder.java

```

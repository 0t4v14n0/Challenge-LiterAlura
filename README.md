# LiteraLura - Projeto de Desafio

O **LiteraLura** é um projeto que utiliza a API Gutendex para consultar informações sobre livros e autores, armazenando esses dados em um banco de dados PostgreSQL. Este projeto foi desenvolvido utilizando Spring Framework e Java para implementação das funcionalidades descritas abaixo.

## Funcionalidades Implementadas

1. **Buscar Livro Pelo Título**
   - Permite buscar livros cadastrados no sistema pelo título.

2. **Listar Livros Registrados**
   - Apresenta uma lista de todos os livros registrados no banco de dados.

3. **Listar Autores Registrados**
   - Exibe uma lista de todos os autores registrados no sistema.

4. **Listar Autores Vivos em Determinado Ano**
   - Retorna uma lista de autores que estavam vivos em um ano específico.

5. **Listar Livros em Determinado Idioma**
   - Mostra uma lista de livros disponíveis em um idioma específico.

6. **Estatísticas**
   - Apresenta estatísticas diversas sobre os livros e autores cadastrados, como total de livros, total de autores, entre outras informações relevantes.

7. **TOP 10 Livros Mais Baixados**
   - Lista os 10 livros mais baixados pelos usuários.

8. **Buscar Autor Por Nome**
   - Permite buscar um autor pelo seu nome completo ou parcial.

## Tecnologias Utilizadas

- **Spring Framework**: Utilizado para a criação do backend do projeto, incluindo o uso de Spring Boot para facilitar a configuração e execução.
- **Java**: Linguagem principal de programação.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados dos livros e autores.
- **Gutendex API**: API utilizada para consultar informações sobre livros e autores disponíveis.
- **Hibernate**: Framework de mapeamento objeto-relacional para facilitar a integração com o PostgreSQL.
- **Maven**: Gerenciador de dependências utilizado para gerenciar as bibliotecas e plugins do projeto.

## Configuração do Ambiente de Desenvolvimento

Para executar o projeto em seu ambiente local, siga os passos abaixo:

1. **Clonar o Repositório:**

  ```shell
  git clone https://github.com/0t4v14n0/Challenge-LiterAlura.git
  ```

2. **Configurar o Banco de Dados:**
- Crie um banco de dados PostgreSQL chamado `literalura_db`.
- Configure as credenciais do banco no arquivo `application.properties`.

3. **Executar o Projeto:**
- Execute o projeto Spring Boot usando sua IDE preferida (como Eclipse ou IntelliJ IDEA) ou através do Maven:
  ```
  mvn spring-boot:run
  ```

4. **Acessar as Funcionalidades:**
- Após iniciar o servidor, acesse as funcionalidades através de um cliente HTTP ou através de uma interface de usuário (front-end) desenvolvida.

## Contribuição

Este projeto é um desafio e pode ser melhorado com diversas funcionalidades adicionais, como integração com mais APIs de livros, melhoria na interface de usuário, entre outros. Contribuições são bem-vindas!

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

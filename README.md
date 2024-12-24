# Sistema Ponto Eletrônico

![Visualizações do Repositório](https://komarev.com/ghpvc/?username=Eduardo-J-S&repo=sistema-ponto-eletronico&color=green)

Bem-vindo ao repositório do **Sistema Ponto Eletrônico**! Este projeto foi desenvolvido para gerenciar e rastrear a frequência dos funcionários de forma eficiente, utilizando **Spring Boot** para o back-end e **React** para o front-end.

## 🚀 Sobre o Projeto

O **Sistema Ponto Eletrônico** é um aplicativo full-stack criado para permitir que os funcionários registrem suas entradas e saídas e que os administradores possam visualizar e gerenciar esses registros de maneira eficiente.

### Tecnologias utilizadas:

- **Back-End**:  
  - **Spring Boot**: Para construir a API RESTful que gerencia os dados de entrada e saída dos funcionários.
  - **Spring Data JPA**: Simplifica a interação com o banco de dados e fornece um repositório baseado em JPA para operações CRUD, abstraindo a complexidade de consultas SQL.
  - **Spring Security**: Para segurança e autenticação.
  - **MySQL**: Banco de dados relacional utilizado para armazenar os dados de entrada e saída dos funcionários.
  - **Lombok**: Utilizado para reduzir código boilerplate, como getters, setters e construtores, tornando o código mais limpo e fácil de manter.
  - **Apache POI**: Biblioteca utilizada para manipulação de arquivos Excel, permitindo exportação de dados de frequência dos funcionários.
  - **ModelMapper**: Biblioteca utilizada para facilitar o mapeamento entre **DTOs** e **Entidades**, melhorando a separação de responsabilidades e a legibilidade do código.
  - **Spring Boot DevTools**: Ferramenta que facilita o desenvolvimento, oferecendo funcionalidades como hot reload e reinício automático durante as modificações no código.
  - **Java JWT**: Biblioteca usada para geração e validação de tokens JWT, essencial para o processo de autenticação e autorização em APIs seguras.

- **Front-End**:  
  - **React**: Para construir a interface do usuário interativa e responsiva.
  - **Axios**: Para fazer requisições HTTP para o back-end.
  - **React Router DOM**: Utilizado para gerenciar a navegação entre as páginas e componentes da aplicação React, facilitando o controle de rotas no sistema.
  - **JWT Decode**: Utilizado para decodificar tokens JWT no front-end, permitindo a verificação de informações de autenticação e autorização no lado do cliente.

- **Banco de Dados**:  
  - O banco de dados relacional utilizado para armazenar os dados foi o **MySQL**.

- **Ferramentas de Build**: 
  - Maven (para o back-end)
  - npm (para o front-end)

## Funcionalidades

### 1. **Autenticação e Registro:**
- **Login**: Permite que o usuário se autentique no sistema.
- **Registro**: Permite que novos usuários (funcionários) se cadastrem no sistema.

### 2. **Plataforma de Ponto Eletrônico:**
Após o login, o usuário terá acesso às funcionalidades abaixo, dependendo do seu papel (usuário comum ou administrador):
- **Registro de Ponto**: Funcionalidade para registrar as entradas e saídas do funcionário.
- **Espelho de Ponto**: Visualização dos registros de ponto do usuário.
- **Alterar Senha**: Opção para alterar a senha do usuário.
- **Ajuda**: Seção com FAQs ou assistência para o uso do sistema.

### 3. **Funcionalidades Exclusivas para Administradores:**
- **Adicionar Funcionário**: Permite ao administrador adicionar novos funcionários ao sistema.
- **Justificativa de Ponto**: Funcionalidade para gerenciar e aprovar justificativas de pontos.
- **Editar Horários**: Permite ao administrador editar os horários de entrada e saída dos funcionários.
- **Gerenciar Funcionários**: O administrador pode visualizar, editar ou excluir registros de funcionários no sistema.

## 📦 Primeiros Passos

### Pré-requisitos

- **Java Development Kit (JDK)** 8 ou superior.
- **Maven** 3.6 ou superior (para o back-end).
- **Node.js** 12 ou superior.
- **npm** ou **yarn** (para o front-end).

### Instalação

1. Clone o repositório
    ```bash
    git clone https://github.com/Eduardo-J-S/sistema-ponto-eletronico.git
    ```
2. Configuração do Back-End
    1. Navegue até o diretório do back-end:
    ```bash
    cd sistema-ponto-eletronico
    ```
    2. Construa o projeto com Maven:
    ```bash
    mvn clean install
    ```
    3. Configure o banco de dados (se necessário) e ajuste as credenciais no application.properties ou application.yml.

    4. Execute o aplicativo:
    ```bash
    mvn spring-boot:run
    ```
O back-end estará disponível por padrão em http://localhost:8083.

3. Configuração do Front-End
    1. Navegue até o diretório do front-end:
    ```bash
    cd sistema-ponto-eletronico/frontend
    ```
    2. Instale as dependências com npm ou yarn:
    ```bash
    npm install
    ```
    3. Execute o servidor de desenvolvimento:
    ```bash
    npm start
    ```

O front-end estará disponível em http://localhost:3000.

## Testando a Aplicação
Após configurar tanto o back-end quanto o front-end, você poderá acessar a aplicação no navegador. A interface do front-end permitirá que você registre as entradas e saídas, e o administrador poderá visualizar os registros.

## 🤝 Contribuindo
Contribuições são bem-vindas! Siga estes passos:

1. Faça um fork do repositório.
2. Crie uma nova branch (git checkout -b feature/YourFeature).
3. Faça commit das suas alterações (git commit -m 'Add some feature').
4. Envie para a branch (git push origin feature/YourFeature).
5. Abra um pull request.

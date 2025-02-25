<p align="center">
    <img src="https://imgur.com/7kYLhY6.png" width="200" title="Unistock Logo" alt="Unistock Logo" />
</p>

Aplicação de CRUD para gerenciamento de estoque de produtos. Desenvolvida em Java com **JSP/Servlets**, seguindo os padrões **DAO** e **MVC**, e integração com PostgreSQL.

## 🚀 Funcionalidades
- **Criação** de produtos com código, nome e preço
- **Listagem** de produtos com paginação e ordenação
- **Atualização** de informações de produtos existentes
- **Exclusão** de itens do estoque
- **Interface web**

## 🛠️ Tecnologias
- **Java 1.8** - Linguagem principal
- **JSP** + **Servlets** - Interface e controle
- **PostgreSQL** - Persistência de dados
- **Tomcat 9** - Servidor de aplicação
- **Docker** - Containerização
- **Maven** - Gerenciamento de dependências

## 🔍 Diagramas

<details>
<summary>Modelagem de Dados</summary>
  
![Diagrama do Banco](https://imgur.com/0LYvA1z.png)
</details>

<details>
<summary>Classes do Sistema</summary>

![Classe Produto](https://i.imgur.com/P5XnYyI.png)
![Classes Lógica DAO](https://i.imgur.com/IPePEM2.png)
![Classes de Fetchers (Buscadores)](https://i.imgur.com/p0y1DuS.png)
![Classes de Handlers (Manipuladores)](https://i.imgur.com/DciUXux.png)

</details>

## ⚙️ Configuração

### Pré-requisitos
- Docker e Docker Compose
- Java 1.8 (apenas para desenvolvimento)
- Maven (apenas para desenvolvimento)

### Passo a Passo
1. Clone o repositório:
   ```bash
   git clone https://github.com/yotozangue/unistock.git
   cd unistock
   ```
2. Configure as variáveis de ambiente:
    ```bash
    cp .env-example .env
    # Edite o .env com suas credenciais
    ```
3. Execute a aplicação:
    ```bash
    make all
    ```
    O comando vai:
    - Construir as imagens Docker
    - Iniciar os containers (Tomcat + PostgreSQL)
    - Popular o banco de dados
4. Acesse a aplicação:
    ```bash
    http://localhost:8080/unistock
    ```

## Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [License](https://github.com/yotozangue/unistock/blob/main/LICENSE) para detalhes.


## Contato

Se você tiver qualquer dúvida ou sugestão, entre em contato comigo através do email [allandelima@proton.me](mailto:allandelima@proton.me).

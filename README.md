 # Sistema de Controle de Estoque

**Teste Técnico - Digiboard Eletrônica da Amazônia**  
**Desenvolvedor Full Stack - Java**  
**Agosto/2025**

## 📋 Descrição do Projeto

Sistema web para controle de estoque desenvolvido com Java 8, JSF 2.3, PrimeFaces 8.0, Hibernate 5.4 e PostgreSQL. O sistema permite o cadastro de produtos, usuários e o processamento de pagamentos com controle de estoque e validação de produtos vencidos.

## 🚀 Funcionalidades

### 1. **Cadastro de Produtos**
- ✅ Código único do produto
- ✅ Descrição do produto
- ✅ Data de entrada no estoque
- ✅ Data de validade
- ✅ Quantidade em estoque
- ✅ Operações: Criar, Listar, Alterar, Excluir

### 2. **Cadastro de Usuários**
- ✅ Nome do usuário
- ✅ Email único
- ✅ Operações: Criar, Listar, Alterar, Excluir

### 3. **Processamento de Pagamentos**
- ✅ Seleção de usuário e produto
- ✅ Informar quantidade desejada
- ✅ Baixa automática no estoque
- ✅ Registro do pagamento com data
- ✅ Cálculo automático da data de entrega
- ✅ Validação de produtos vencidos
- ✅ Validação de quantidade disponível

### 4. **Listagem de Pagamentos**
- ✅ Histórico completo de pagamentos
- ✅ Informações do produto, quantidade, datas e usuário
- ✅ Paginação e ordenação

## 🛠️ Tecnologias Utilizadas

- **Java 8** - Linguagem de programação
- **JSF 2.3** - Framework web
- **PrimeFaces 8.0** - Biblioteca de componentes UI
- **Hibernate 5.4** - ORM para persistência
- **PostgreSQL** - Banco de dados
- **Maven** - Gerenciador de dependências
- **CDI** - Injeção de dependências

## 🗄️ Estrutura do Banco de Dados

### Tabelas:
1. **usuarios** - Cadastro de usuários do sistema
2. **produto** - Catálogo de produtos com controle de estoque
3. **pagamentos** - Registro de vendas/movimentações

### Scripts SQL:
- `create_database.sql` - Criação das tabelas e índices
- `insert_sample_data.sql` - Dados de exemplo para teste

## ⚙️ Configuração e Instalação

### Pré-requisitos:
- Java 8 ou superior
- PostgreSQL 12+
- Apache Tomcat 9+ ou servidor compatível
- Maven 3.6+

### 1. **Configuração do Banco de Dados**

```sql
 1 - Basicamente copiar e colar os scrips da pasta SQL na aba de query tool do postgres para criar o banco e tabelas 
 2 - Depois eexecutar o script com os inser de exemplo
```

### 2. **Configuração da Aplicação**

Editar o arquivo `src/main/resources/hibernate.cfg.xml`:

**Obs:** Verificar qual banco vai utilizar, nesse projeto utilizei o Postgres

```xml
<property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/testetecnico</property>
<property name="hibernate.connection.username">SEU_USUARIO</property>
<property name="hibernate.connection.password">SUA_SENHA</property>
```

### 3. **Compilação e Deploy**
**Obs:** Confesso que nessa parte de compilação e deploy tive um pouco de dor de cabeça, uns amigos me ajudaram e consegui rodar 'setando' meu servidor Tomcat direto pela IDE que utilizaei, no caso o Intelij, vou deixar o link de um tutorial que segui:  
[Como configurar o Tomcat no IntelliJ](https://cursos.alura.com.br/forum/topico-duvida-como-posso-configurar-o-tomcat-no-intelij-375178#:~:text=Abra%20o%20IntelliJ%20e%20v%C3%A1,onde%20o%20TomCat%20foi%20instalado.)
```bash
# Compilar o projeto - Essa forma é mais 'automatica'
1 - Primeiro, você precisa baixar e instalar o TomCat. Você pode fazer isso no site oficial do Apache TomCat.

2 - Abra o IntelliJ e vá até "File" > "Settings" (ou use o atalho CTRL+ALT+S).

3 - No menu de configurações, vá até "Build, Execution, Deployment" > "Application Servers".

4 - Clique no '+' e escolha 'TomCat Server'.

5 - Agora você precisa apontar para a pasta onde o TomCat foi instalado. Clique em 'Configure...' e navegue até a pasta de instalação do TomCat.

7 - Clique 'OK' para fechar as janelas e salvar as configurações.

Agora, quando você criar um novo projeto, poderá escolher o TomCat como seu servidor de aplicação.
```
```bash
# Compilar o projeto - Essa forma é mais manual
mvn clean package

# O arquivo WAR será gerado em target/meuapp.war
# Deploy no Tomcat copiando para a pasta webapps/
```

### 4. **Acessar a Aplicação**

Abrir o navegador em: `http://localhost:8080/meuapp/telainicial.xhtml`

## 🔧 Regras de Negócio Implementadas

### ✅ Validações de Pagamento:
- ❌ Não permitir pagamento de produtos vencidos
- ❌ Não permitir quantidade maior que o estoque disponível
- ✅ Campos obrigatórios validados
- ✅ Baixa automática do estoque após pagamento
- ✅ Cálculo automático da data de entrega (3 dias úteis)

### ✅ Validações de Produto:
- ✅ Código único obrigatório
- ✅ Data de validade posterior à data de entrada
- ✅ Quantidade deve ser zero ou positiva

### ✅ Validações de Usuário:
- ✅ Nome obrigatório
- ✅ Email obrigatório e formato válido
- ✅ Email único no sistema

## 🎯 Funcionalidades da Interface

- **Design Responsivo** - Interface adaptável a diferentes telas
- **Navegação Intuitiva** - Menu principal com acesso direto às funcionalidades
- **Mensagens de Feedback** - Notificações de sucesso, erro e alerta
- **Paginação** - Tabelas com paginação automática
- **Confirmação de Exclusão** - Diálogos de confirmação para operações críticas
- **Validação em Tempo Real** - Feedback imediato de erros de validação

## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/topics/git/add_files/#add-files-to-a-git-repository) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin https://gitlab.com/lucasfrranca/testetecnico.git
git branch -M main
git push -uf origin main
```

## Integrate with your tools

- [ ] [Set up project integrations](https://gitlab.com/lucasfrranca/testetecnico/-/settings/integrations)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Set auto-merge](https://docs.gitlab.com/user/project/merge_requests/auto_merge/)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing (SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

# Editing this README

When you're ready to make this README your own, just edit this file and use the handy template below (or feel free to structure it however you want - this is just a starting point!). Thanks to [makeareadme.com](https://www.makeareadme.com/) for this template.

## Suggestions for a good README

Every project is different, so consider which of these sections apply to yours. The sections used in the template are suggestions for most open source projects. Also keep in mind that while a README can be too long and detailed, too long is better than too short. If you think your README is too long, consider utilizing another form of documentation rather than cutting out information.

## Name
Choose a self-explaining name for your project.

## Description
Let people know what your project can do specifically. Provide context and add a link to any reference visitors might be unfamiliar with. A list of Features or a Background subsection can also be added here. If there are alternatives to your project, this is a good place to list differentiating factors.

## Badges
On some READMEs, you may see small images that convey metadata, such as whether or not all the tests are passing for the project. You can use Shields to add some to your README. Many services also have instructions for adding a badge.

## Visuals
Depending on what you are making, it can be a good idea to include screenshots or even a video (you'll frequently see GIFs rather than actual videos). Tools like ttygif can help, but check out Asciinema for a more sophisticated method.

## Installation
Within a particular ecosystem, there may be a common way of installing things, such as using Yarn, NuGet, or Homebrew. However, consider the possibility that whoever is reading your README is a novice and would like more guidance. Listing specific steps helps remove ambiguity and gets people to using your project as quickly as possible. If it only runs in a specific context like a particular programming language version or operating system or has dependencies that have to be installed manually, also add a Requirements subsection.

## Usage
Use examples liberally, and show the expected output if you can. It's helpful to have inline the smallest example of usage that you can demonstrate, while providing links to more sophisticated examples if they are too long to reasonably include in the README.

## Support
Tell people where they can go to for help. It can be any combination of an issue tracker, a chat room, an email address, etc.

## Roadmap
If you have ideas for releases in the future, it is a good idea to list them in the README.

## Contributing
State if you are open to contributions and what your requirements are for accepting them.

For people who want to make changes to your project, it's helpful to have some documentation on how to get started. Perhaps there is a script that they should run or some environment variables that they need to set. Make these steps explicit. These instructions could also be useful to your future self.

You can also document commands to lint the code or run tests. These steps help to ensure high code quality and reduce the likelihood that the changes inadvertently break something. Having instructions for running tests is especially helpful if it requires external setup, such as starting a Selenium server for testing in a browser.

## Authors and acknowledgment
Show your appreciation to those who have contributed to the project.

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.

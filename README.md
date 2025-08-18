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


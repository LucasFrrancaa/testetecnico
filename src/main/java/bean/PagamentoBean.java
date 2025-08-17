package bean;

import model.Pagamentos;
import model.Produto;
import model.Usuarios;
import repository.PagamentoDao;
import repository.ProdutoDao;
import repository.UsuarioDao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class PagamentoBean implements Serializable {
    
    private Pagamentos pagamento;
    private List<Pagamentos> pagamentos;
    private List<Usuarios> usuarios;
    private List<Produto> produtos;
    private List<Produto> produtosDisponiveis;
    
    private PagamentoDao pagamentoDao;
    private UsuarioDao usuarioDao;
    private ProdutoDao produtoDao;
    
    private Long usuarioSelecionadoId;
    private Long produtoSelecionadoId;
    private Integer quantidadeSolicitada;
    
    public PagamentoBean() {
        this.pagamento = new Pagamentos();
        this.pagamentoDao = new PagamentoDao();
        this.usuarioDao = new UsuarioDao();
        this.produtoDao = new ProdutoDao();
        
        carregarDados();
    }
    
    public void processarPagamento() {
        try {
            if (validarPagamento()) {
                // Buscar entidades pelos IDs
                Usuarios usuario = buscarUsuarioPorId(usuarioSelecionadoId);
                Produto produto = buscarProdutoPorId(produtoSelecionadoId);
                
                // Criar novo pagamento
                Pagamentos novoPagamento = new Pagamentos();
                novoPagamento.setUsuario(usuario);
                novoPagamento.setProduto(produto);
                novoPagamento.setQuantidade(quantidadeSolicitada);
                novoPagamento.setData_pagamento(new Date());
                
                // Calcular data de entrega (3 dias úteis)
                Date dataEntrega = calcularDataEntrega(new Date(), 3);
                novoPagamento.setData_entrega(dataEntrega);
                
                // Baixar estoque
                produto.setQuantidade(produto.getQuantidade() - quantidadeSolicitada);
                produtoDao.alterar(produto);
                
                // Registrar pagamento
                pagamentoDao.incluir(novoPagamento);
                
                addMessage("Pagamento processado com sucesso!", FacesMessage.SEVERITY_INFO);
                limpar();
                carregarDados();
            }
        } catch (Exception e) {
            addMessage("Erro ao processar pagamento: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void onProdutoChange() {
        // Atualizar informações quando produto for selecionado
        if (produtoSelecionadoId != null) {
            Produto produto = buscarProdutoPorId(produtoSelecionadoId);
            if (produto != null && isProdutoVencido(produto)) {
                addMessage("Produto selecionado está vencido!", FacesMessage.SEVERITY_WARN);
            }
        }
    }
    
    private boolean validarPagamento() {
        if (usuarioSelecionadoId == null) {
            addMessage("Selecione um usuário", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        if (produtoSelecionadoId == null) {
            addMessage("Selecione um produto", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        if (quantidadeSolicitada == null || quantidadeSolicitada <= 0) {
            addMessage("Quantidade deve ser maior que zero", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        Produto produto = buscarProdutoPorId(produtoSelecionadoId);
        if (produto == null) {
            addMessage("Produto não encontrado", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        // Verificar validade
        if (isProdutoVencido(produto)) {
            addMessage("Não é possível realizar pagamento com produto vencido", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        // Verificar estoque
        if (quantidadeSolicitada > produto.getQuantidade()) {
            addMessage("Quantidade solicitada (" + quantidadeSolicitada + 
                      ") é maior que o estoque disponível (" + produto.getQuantidade() + ")", 
                      FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        return true;
    }
    
    private boolean isProdutoVencido(Produto produto) {
        return produto.getData_validade().before(new Date());
    }
    
    private Date calcularDataEntrega(Date dataBase, int diasUteis) {
        Date dataEntrega = new Date(dataBase.getTime());
        int diasAdicionados = 0;
        
        while (diasAdicionados < diasUteis) {
            dataEntrega = new Date(dataEntrega.getTime() + 24 * 60 * 60 * 1000); // Adiciona 1 dia
            
            // Verificar se não é fim de semana (simplificado)
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(dataEntrega);
            int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
            
            if (dayOfWeek != java.util.Calendar.SATURDAY && dayOfWeek != java.util.Calendar.SUNDAY) {
                diasAdicionados++;
            }
        }
        
        return dataEntrega;
    }
    
    private Usuarios buscarUsuarioPorId(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    private Produto buscarProdutoPorId(Long id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    private void carregarDados() {
        try {
            usuarios = usuarioDao.listar();
            produtos = produtoDao.listar();
            pagamentos = pagamentoDao.listar();
            
            // Filtrar apenas produtos válidos (não vencidos e com estoque)
            produtosDisponiveis = produtos.stream()
                    .filter(p -> !isProdutoVencido(p) && p.getQuantidade() > 0)
                    .collect(Collectors.toList());
                    
        } catch (Exception e) {
            addMessage("Erro ao carregar dados: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void limpar() {
        this.usuarioSelecionadoId = null;
        this.produtoSelecionadoId = null;
        this.quantidadeSolicitada = null;
    }
    
    private void addMessage(String message, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
    }
    
    // Getters e Setters
    public Pagamentos getPagamento() {
        return pagamento;
    }
    
    public void setPagamento(Pagamentos pagamento) {
        this.pagamento = pagamento;
    }
    
    public List<Pagamentos> getPagamentos() {
        return pagamentos;
    }
    
    public void setPagamentos(List<Pagamentos> pagamentos) {
        this.pagamentos = pagamentos;
    }
    
    public List<Usuarios> getUsuarios() {
        return usuarios;
    }
    
    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public List<Produto> getProdutosDisponiveis() {
        return produtosDisponiveis;
    }
    
    public void setProdutosDisponiveis(List<Produto> produtosDisponiveis) {
        this.produtosDisponiveis = produtosDisponiveis;
    }
    
    public Long getUsuarioSelecionadoId() {
        return usuarioSelecionadoId;
    }
    
    public void setUsuarioSelecionadoId(Long usuarioSelecionadoId) {
        this.usuarioSelecionadoId = usuarioSelecionadoId;
    }
    
    public Long getProdutoSelecionadoId() {
        return produtoSelecionadoId;
    }
    
    public void setProdutoSelecionadoId(Long produtoSelecionadoId) {
        this.produtoSelecionadoId = produtoSelecionadoId;
    }
    
    public Integer getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }
    
    public void setQuantidadeSolicitada(Integer quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }
}

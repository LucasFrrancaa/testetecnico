package bean;

import model.Produto;
import repository.ProdutoDao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
    
    private Produto produto;
    private List<Produto> produtos;
    private ProdutoDao produtoDao;
    
    public ProdutoBean() {
        this.produto = new Produto();
        this.produtoDao = new ProdutoDao();
        carregarProdutos();
    }
    
    public void incluir() {
        try {
            if (validarCampos()) {
                produtoDao.incluir(produto);
                addMessage("Produto incluído com sucesso!", FacesMessage.SEVERITY_INFO);
                limpar();
                carregarProdutos();
            }
        } catch (Exception e) {
            addMessage("Erro ao incluir produto: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void alterar() {
        try {
            if (validarCampos()) {
                produtoDao.alterar(produto);
                addMessage("Produto alterado com sucesso!", FacesMessage.SEVERITY_INFO);
                limpar();
                carregarProdutos();
            }
        } catch (Exception e) {
            addMessage("Erro ao alterar produto: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void excluir(Produto produto) {
        try {
            produtoDao.exclui(produto);
            addMessage("Produto excluído com sucesso!", FacesMessage.SEVERITY_INFO);
            carregarProdutos();
        } catch (Exception e) {
            addMessage("Erro ao excluir produto: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(Produto produto) {
        this.produto = produto;
    }
    
    private void carregarProdutos() {
        try {
            produtos = produtoDao.listar();
        } catch (Exception e) {
            addMessage("Erro ao carregar produtos: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    private boolean validarCampos() {
        if (produto.getCodigo() == null || produto.getCodigo().trim().isEmpty()) {
            addMessage("Código é obrigatório", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()) {
            addMessage("Descrição é obrigatória", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        if (produto.getData_entrada() == null) {
            addMessage("Data de entrada é obrigatória", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        if (produto.getData_validade() == null) {
            addMessage("Data de validade é obrigatória", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        if (produto.getData_validade().before(produto.getData_entrada())) {
            addMessage("Data de validade deve ser posterior à data de entrada", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            addMessage("Quantidade deve ser zero ou positiva", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        return true;
    }
    
    public boolean isProdutoVencido(Produto produto) {
        return produto.getData_validade().before(new Date());
    }
    
    public boolean isProdutoValidoParaVenda(Produto produto) {
        return !isProdutoVencido(produto) && produto.getQuantidade() > 0;
    }
    
    public void limpar() {
        this.produto = new Produto();
    }
    
    private void addMessage(String message, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}

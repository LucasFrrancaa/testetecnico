package bean;

import model.Usuarios;
import repository.UsuarioDao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
    
    private Usuarios usuario;
    private List<Usuarios> usuarios;
    private UsuarioDao usuarioDao;
    
    public UsuarioBean() {
        this.usuario = new Usuarios();
        this.usuarioDao = new UsuarioDao();
        carregarUsuarios();
    }
    
    public void incluir() {
        try {
            if (validarCampos()) {
                usuarioDao.incluir(usuario);
                addMessage("Usuário incluído com sucesso!", FacesMessage.SEVERITY_INFO);
                limpar();
                carregarUsuarios();
            }
        } catch (Exception e) {
            addMessage("Erro ao incluir usuário: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void alterar() {
        try {
            if (validarCampos()) {
                usuarioDao.alterar(usuario);
                addMessage("Usuário alterado com sucesso!", FacesMessage.SEVERITY_INFO);
                limpar();
                carregarUsuarios();
            }
        } catch (Exception e) {
            addMessage("Erro ao alterar usuário: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void excluir(Usuarios usuario) {
        try {
            usuarioDao.exclui(usuario);
            addMessage("Usuário excluído com sucesso!", FacesMessage.SEVERITY_INFO);
            carregarUsuarios();
        } catch (Exception e) {
            addMessage("Erro ao excluir usuário: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    private void carregarUsuarios() {
        try {
            usuarios = usuarioDao.listar();
        } catch (Exception e) {
            addMessage("Erro ao carregar usuários: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    private boolean validarCampos() {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            addMessage("Nome é obrigatório", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            addMessage("Email é obrigatório", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        if (!isValidEmail(usuario.getEmail())) {
            addMessage("Email deve ter formato válido", FacesMessage.SEVERITY_ERROR);
            return false;
        }
        
        return true;
    }
    
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
    
    public void limpar() {
        this.usuario = new Usuarios();
    }
    
    public void testarConexao() {
        try {
            // Teste básico para verificar se o Hibernate está funcionando
            usuarios = usuarioDao.listar();
            addMessage("Conexão com banco OK! Total de usuários: " + usuarios.size(), FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage("Erro na conexão: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }
    
    private void addMessage(String message, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
    }
    
    // Getters e Setters
    public Usuarios getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    public List<Usuarios> getUsuarios() {
        return usuarios;
    }
    
    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
}

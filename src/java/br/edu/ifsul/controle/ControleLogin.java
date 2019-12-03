package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {
    
    private UsuarioDAO<Usuario> dao;
    private Usuario usuarioLogado;
    private String usuario;
    private String nome;
    private String senha;
    
    public ControleLogin(){
        dao = new UsuarioDAO<>();
    }
    
    public String irPaginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String login(){
        if(dao.login(usuario, senha)){
            usuarioLogado = dao.localizaUsuarioPorNomeUsuario(usuario);
            usuario = "";
            senha = "";
            Util.mensagemInformacao("Login efetuado com sucesso!");
            return "/index?faces-redirect=true";
        } else {
            Util.mensagemErro("Nome de usuário ou senha inválidos!");
            return "/login?faces-redirect=true";
        }
    }
    
    public String logout(){
        usuarioLogado = null;
        Util.mensagemInformacao("Logout efetuado com sucesso!");
        return "/index?faces-redirect=true";
    }
    
    

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    

}

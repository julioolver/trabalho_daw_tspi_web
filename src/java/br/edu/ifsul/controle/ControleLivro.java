/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;

import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Julio Cesar
 */
@ManagedBean(name = "controleLivro")
@SessionScoped
public class ControleLivro implements Serializable{
    private LivroDAO<Livro> dao;
    private Livro objeto;
    private IdiomaDAO<Idioma> daoIdioma;
    private FormatoDAO<Formato> daoFormato;
    
    public ControleLivro(){
        dao = new LivroDAO<>();
        daoIdioma = new IdiomaDAO<>();
        daoFormato = new FormatoDAO<>();
    }
    
    public String listar(){
        return "/privado/Livro/listar?faces-redirect=true";
    }
    public String novo(){
      //setObjeto(new Livro());
        objeto = new Livro();
        return "formulario?faces-redirect=true";
    }
public String salvar(){
        boolean persistiu;
        if (getObjeto().getISBN()== null){
            persistiu = getDao().persist(getObjeto());
        } else {
            persistiu = getDao().merge(getObjeto());
        }
        if (persistiu){
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(getDao().getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Object ISBN){
        setObjeto(getDao().localizar(ISBN));
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Object ISBN){
        setObjeto(getDao().localizar(ISBN));
        if (getDao().remove(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public LivroDAO<Livro> getDao() {
        return dao;
    }

    public void setDao(LivroDAO<Livro> dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoIdioma
     */
    public IdiomaDAO<Idioma> getDaoIdioma() {
        return daoIdioma;
    }

    /**
     * @param daoIdioma the daoIdioma to set
     */
    public void setDaoIdioma(IdiomaDAO<Idioma> daoIdioma) {
        this.daoIdioma = daoIdioma;
    }

    /**
     * @return the daoFormato
     */
    public FormatoDAO<Formato> getDaoFormato() {
        return daoFormato;
    }

    /**
     * @param daoFormato the daoFormato to set
     */
    public void setDaoFormato(FormatoDAO<Formato> daoFormato) {
        this.daoFormato = daoFormato;
    }

}

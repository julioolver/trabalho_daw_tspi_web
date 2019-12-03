/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Julio Cesar
 */
@ManagedBean(name = "controleIdioma")
@SessionScoped
public class ControleIdioma implements Serializable{
    private IdiomaDAO<Idioma> dao;
    private Idioma objeto;
    
    public ControleIdioma(){
        dao = new IdiomaDAO<>();
    }
    
    public String listar(){
        return "/privado/Idioma/listar?faces-redirect=true";
    }
    public String novo(){
      //setObjeto(new Idioma());
        objeto = new Idioma();
        return "formulario?faces-redirect=true";
    }
  public String salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Object id){
        setObjeto(getDao().localizar(id));
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Object id){
        setObjeto(getDao().localizar(id));
        if (getDao().remove(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public IdiomaDAO<Idioma> getDao() {
        return dao;
    }

    public void setDao(IdiomaDAO<Idioma> dao) {
        this.dao = dao;
    }

    public Idioma getObjeto() {
        return objeto;
    }

    public void setObjeto(Idioma objeto) {
        this.objeto = objeto;
    }

}

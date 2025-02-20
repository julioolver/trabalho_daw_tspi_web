/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Julio Cesar
 */
@ManagedBean(name = "controleFormato")
@ViewScoped
public class ControleFormato implements Serializable{
    private FormatoDAO<Formato> dao;
    private Formato objeto;
    
    public ControleFormato(){
        dao = new FormatoDAO<>();
    }
    
    public String listar(){
        return "/privado/Formato/listar?faces-redirect=true";
    }
    public String novo(){
      //setObjeto(new Formato());
        objeto = new Formato();
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
    
    public FormatoDAO<Formato> getDao() {
        return dao;
    }

    public void setDao(FormatoDAO<Formato> dao) {
        this.dao = dao;
    }

    public Formato getObjeto() {
        return objeto;
    }

    public void setObjeto(Formato objeto) {
        this.objeto = objeto;
    }

}
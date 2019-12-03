/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Julio Cesar
 */
@ManagedBean(name = "controleAutor")
@ViewScoped
public class ControleAutor implements Serializable{
    private AutorDAO<Autor> dao;
    private Autor objeto;
   
    
    public ControleAutor(){
        dao = new AutorDAO<>();
    }
    
    public String listar(){
        return "/privado/Autor/listar?faces-redirect=true";
    }
    public void novo(){
      //setObjeto(new Autor());
        objeto = new Autor();
      
    }
public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
          
        } else {
            Util.mensagemErro(dao.getMensagem());
           
        }
    }    
    public void editar(Object id){
        setObjeto(getDao().localizar(id));
        
    }
    
    public void remover(Object id){
        setObjeto(getDao().localizar(id));
        if (getDao().remove(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public AutorDAO<Autor> getDao() {
        return dao;
    }

    public void setDao(AutorDAO<Autor> dao) {
        this.dao = dao;
    }

    public Autor getObjeto() {
        return objeto;
    }

    public void setObjeto(Autor objeto) {
        this.objeto = objeto;
    }

}

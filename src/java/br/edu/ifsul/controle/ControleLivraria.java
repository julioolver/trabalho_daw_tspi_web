/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.LivrariaDAO;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Livraria;
import br.edu.ifsul.modelo.Livraria;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Julio Cesar
 */
@ManagedBean(name = "controleLivraria")
@ViewScoped
public class ControleLivraria implements Serializable{
    private LivrariaDAO<Livraria> dao;
    private Livraria objeto;
    private Boolean novoCatalogo;
    private Catalogo catalogo;
    
    public ControleLivraria(){
        dao = new LivrariaDAO<>();
        
    }
    
    public String listar(){
        return "/privado/Livraria/listar?faces-redirect=true";
    }
    public void novo(){
      //setObjeto(new Livraria());
        objeto = new Livraria();
       
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
    
    public LivrariaDAO<Livraria> getDao() {
        return dao;
    }

    public void setDao(LivrariaDAO<Livraria> dao) {
        this.dao = dao;
    }

    public Livraria getObjeto() {
        return objeto;
    }

    public void setObjeto(Livraria objeto) {
        this.objeto = objeto;
    }

     public void novoCatalogo(){
        catalogo = new Catalogo();
        novoCatalogo = true;
    }
    
    public void alterarCatalogo(int index){
        catalogo = objeto.getCatalogos().get(index);
        novoCatalogo = false;
    }
    
    public void salvarCatalogo(){
        if (novoCatalogo){
            objeto.adicionarCatalogo(catalogo);
        }
        Util.mensagemInformacao("Catalogo adicionado com sucesso!");
    }
    
    public void removerCatalogo(int index){
        objeto.removerCatalogo(index);
        Util.mensagemInformacao("Catalogo removido com sucesso!");
    }
    /**
     * @return the novoCatalogo
     */
    public Boolean getNovoCatalogo() {
        return novoCatalogo;
    }

    /**
     * @param novoCatalogo the novoCatalogo to set
     */
    public void setNovoCatalogo(Boolean novoCatalogo) {
        this.novoCatalogo = novoCatalogo;
    }

    /**
     * @return the catalogo
     */
    public Catalogo getCatalogo() {
        return catalogo;
    }

    /**
     * @param catalogo the catalogo to set
     */
    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

   

}

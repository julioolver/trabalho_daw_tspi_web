/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.LivroDAO;

import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Julio Cesar
 */
@ManagedBean(name = "controleLivro")
@ViewScoped

public class ControleLivro implements Serializable{
    
    private LivroDAO<Livro> dao;
    private Livro objeto;
    private IdiomaDAO<Idioma> daoIdioma;
    private FormatoDAO<Formato> daoFormato;
     private AutorDAO<Autor> daoAutor;
     private Autor autor;
   
    
    public ControleLivro(){
        dao = new LivroDAO<>();
        daoIdioma = new IdiomaDAO<>();
        daoFormato = new FormatoDAO<>();
        daoAutor = new AutorDAO<>();
    }
    
    public void adicionarLivroAutor(){
        if (autor != null){ // verificando se o autor foi selecionado
            if(!objeto.getLivroautor().contains(autor)){
                objeto.getLivroautor().add(autor);
                Util.mensagemInformacao("Desejo adicionado com sucesso!");
            } else {
                Util.mensagemErro("Autor já existe na lista de desejos!");
            }
        } else {
            Util.mensagemErro("Selecione um autor!");
        }
    }
    
        public void removerLivroAutor (Autor obj){
        objeto.getLivroautor().remove(obj);
        Util.mensagemInformacao("Autor Fremovido com sucesso!");
    }
    
    
    public String listar(){
        return "/privado/Livro/listar?faces-redirect=true";
    }
    public void novo(){
      //setObjeto(new Livro());
        objeto = new Livro();
    }
public void salvar(){
        boolean persistiu;
        if (getObjeto().getISBN()== null){
            persistiu = getDao().persist(getObjeto());
        } else {
            persistiu = getDao().merge(getObjeto());
        }
        if (persistiu){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }
    
    public void editar(Object ISBN){
        setObjeto(getDao().localizar(ISBN));
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

    /**
     * @return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @return the daoAutor
     */
    public AutorDAO<Autor> getDaoAutor() {
        return daoAutor;
    }

    /**
     * @param daoAutor the daoAutor to set
     */
    public void setDaoAutor(AutorDAO<Autor> daoAutor) {
        this.daoAutor = daoAutor;
    }

}

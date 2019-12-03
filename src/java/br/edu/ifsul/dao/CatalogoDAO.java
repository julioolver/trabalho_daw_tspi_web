/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Catalogo;
import java.io.Serializable;

/**
 *
 * @author Julio Cesar
 * @param <TIPO>
 */
public class CatalogoDAO<TIPO> extends DAOGenerico<Catalogo> implements Serializable{
    
    public CatalogoDAO(){
        super();
        classePersistente = Catalogo.class;
         ordem = "id";
    }
    
}

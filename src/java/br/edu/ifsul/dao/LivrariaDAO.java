/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Livraria;
import java.io.Serializable;

/**
 *
 * @author Julio Cesar
 * @param <TIPO>
 */
public class LivrariaDAO<TIPO> extends DAOGenerico<Livraria> implements Serializable{
    
    public LivrariaDAO(){
        super();
        classePersistente = Livraria.class;
         ordem = "nome";
    }
    
}

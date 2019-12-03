/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author Julio Cesar
 * @param <TIPO>
 */
public class UsuarioDAO<TIPO> extends DAOGenerico<Usuario> implements Serializable {

    public UsuarioDAO() {
        super();
        super.setClassePersistente(Usuario.class);
        super.setOrdem("nome");// ordem padrão
    }

    public boolean login(String usuario, String senha) {
        Query query = super.getEm()
                .createQuery(
                        "from Usuario where upper(nome) = :usuario and "
                        + "upper(senha) = :senha and ativo = true");
        query.setParameter("usuario", usuario.toUpperCase());
        query.setParameter("senha", senha.toUpperCase());
        if (!query.getResultList().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public Usuario localizaUsuarioPorNomeUsuario(String usuario) {
        Query query = em.createQuery("from Usuario where upper(nomeUsuario) = :pusuario");
        query.setParameter("pusuario", usuario.toUpperCase());
        Usuario obj = (Usuario) query.getSingleResult();
        return obj;
    }

}

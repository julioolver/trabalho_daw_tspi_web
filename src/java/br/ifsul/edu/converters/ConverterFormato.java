/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.edu.converters;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Formato;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

/**
 *
 * @author Julio Cesar
 */
@FacesConverter (value = "converterFormato")

public class ConverterFormato implements Serializable, Converter{

    //converte da tela para o controle
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
        return null;
        }
        return EntityManagerUtil.getEntityManager().find(Formato.class, Integer.parseInt(string));
    }
    //converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       if (o == null){
           return null;
       }
       Formato obj = (Formato) o;
       return obj.getId().toString();
    }
    
}

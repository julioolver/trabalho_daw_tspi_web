/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Julio Cesar
 */
public class Util {
    
    public static String getMensagemErro(Exception e){
        while (e.getCause() != null) {

            e = (Exception) e.getCause();
       }
        String retorno = e.getMessage();
        if (retorno.contains("foreing key")) {
            retorno = "Registo não pode ser excluido por possuir referências";
        }
        return retorno;
    }
    
    public static void mensagemInformacao (String msg){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage (FacesMessage.SEVERITY_INFO, msg, "");
        facesContext.addMessage(msg, mensagem);
    }
    
       public static void mensagemErro (String msg){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage (FacesMessage.SEVERITY_ERROR, msg, "");
        facesContext.addMessage(msg, mensagem);
    }
}

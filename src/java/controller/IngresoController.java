/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.util.SessionUtils;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ejb.Usuario;
import facade.AbstractFacade;
import java.util.Collection;
import static javafx.scene.input.KeyCode.T;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PIVARAL
 */
@ManagedBean
@SessionScoped
    public class IngresoController implements Serializable{

    private static final long serialVersionUID = 8708836699317642557L;
    
    private String username;
     
    private String password;
    private Collection<Object> items;
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        if(username.trim().equals(""))
            username = "Sin nombre";
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public String login() {
        
        boolean valid = LoginDAO.validate(username, password);
            if (valid) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("username", username);
                return "index";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                                null,
                                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                "Incorrect Username and Passowrd",
                                                "Please enter correct username and Password"));
                return "login";
            }
    }

    //logout event, invalidate session
    public String logout() {
            HttpSession session = SessionUtils.getSession();
            session.invalidate();
            return "login";
    }
    
}

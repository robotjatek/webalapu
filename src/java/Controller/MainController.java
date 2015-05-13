/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.UserManager;
import Model.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
/**
 *
 * @author Attila
 */
@ManagedBean(name = "mb")
@SessionScoped
public class MainController implements Serializable {

    @EJB
    UserManager um;

    User loggedin;
    User system_user;

    public User getSystem_user() {
	return system_user;
    }

    public void setSystem_user(User system_user) {
	this.system_user = system_user;
    }
    private String inputUsername;
    private String inputPassword;
    private boolean LoginError;

    /**
     * Creates a new instance of MainController
     */
    public MainController() {
	LoginError = false;
    }
    
    @PostConstruct
    public void init()
    {
	 system_user = um.getUser(0);
    }

    public void AuthUser() {
	loggedin = um.getUser(inputUsername, inputPassword);
	LoginError = loggedin == null;
    }

    public void logout() {
	try {
	    loggedin = null;
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    ec.redirect("index.xhtml");
	} catch (IOException ex) {
	    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public User getLoggedin() {
	return loggedin;
    }

    public void setLoggedin(User l) {
	loggedin = l;
    }

    /**
     * @return the inputUsername
     */
    public String getInputUsername() {
	return inputUsername;
    }

    /**
     * @param inputUsername the inputUsername to set
     */
    public void setInputUsername(String inputUsername) {
	this.inputUsername = inputUsername;
    }

    /**
     * @return the inputPassword
     */
    public String getInputPassword() {
	return inputPassword;
    }

    /**
     * @param inputPassword the inputPassword to set
     */
    public void setInputPassword(String inputPassword) {
	this.inputPassword = inputPassword;
    }

    public boolean isLoggedIn() {
	return loggedin != null;
    }

    public boolean getLoginError() {
	return LoginError;
    }

    public void setLoginError(boolean e) {
	LoginError = e;
    }
}

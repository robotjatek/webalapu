/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.EducationManager;
import EJBs.ExperienceManager;
import EJBs.UserManager;
import Model.Education;
import Model.Experience;
import Model.User;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Attila
 */
@ManagedBean
@RequestScoped
public class ProfileController implements Serializable{

    @EJB
    UserManager um;

    @EJB
    ExperienceManager exm;

    @EJB
    EducationManager edm;

    @ManagedProperty(value = "#{mb}")
    MainController mc;

    public MainController getMc() {
	return mc;
    }

    public void setMc(MainController mc) {
	this.mc = mc;
    }

    User requested_user = null;

    /**
     * Creates a new instance of ProfileController
     */
    public ProfileController() {
    }

    @PostConstruct
    public void viewProfile() {
	Integer id;
	Map<String, String> params = FacesContext
		.getCurrentInstance()
		.getExternalContext()
		.getRequestParameterMap();
	try {
	    id = Integer.parseInt(params.get("id"));
	} catch (Exception e) {
	    id = mc.loggedin.getId();
	}

	requested_user = um.getUser(id);
    }

    public User getRequested_user() {
	return requested_user;
    }

    public void setRequested_user(User u) {
	requested_user = u;
    }

    public void delete_experience(Experience e) {

	exm.deleteExperience(e);
    }

    public void delete_eduacation(Education e) {

	edm.deleteEducation(e);
    }

    public List<Experience> getExperienceList() {
	return exm.getExperienceList(requested_user);
    }

    public List<Education> getEducationList() {
	return edm.getEducation(requested_user);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.EducationManager;
import EJBs.ExperienceManager;
import EJBs.SkillManager;
import EJBs.UserManager;
import Model.Education;
import Model.Experience;
import Model.Skill;
import Model.User;
import Model.UserSkill;
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
    
    @EJB
    SkillManager sm;

    @ManagedProperty(value = "#{mb}")
    MainController mc;
    
    String skillstr;
    int skilllvl;

    public String getSkillstr() {
	return skillstr;
    }

    public void setSkillstr(String skillstr) {
	this.skillstr = skillstr;
    }

    public int getSkilllvl() {
	return skilllvl;
    }

    public void setSkilllvl(int skilllvl) {
	this.skilllvl = skilllvl;
    }

    public MainController getMc() {
	return mc;
    }

    public void setMc(MainController mc) {
	this.mc = mc;
    }

    User requested_user = null;

    public UserManager getUm() {
	return um;
    }

    public void setUm(UserManager um) {
	this.um = um;
    }

    public ExperienceManager getExm() {
	return exm;
    }

    public void setExm(ExperienceManager exm) {
	this.exm = exm;
    }

    public EducationManager getEdm() {
	return edm;
    }

    public void setEdm(EducationManager edm) {
	this.edm = edm;
    }

    public SkillManager getSm() {
	return sm;
    }

    public void setSm(SkillManager sm) {
	this.sm = sm;
    }
    
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
    
    public void delete_skill(UserSkill us)
    {
	requested_user.getUserSkillList().remove(us);
	sm.delete_user_skill(us);
    }

    public List<Experience> getExperienceList() {
	return exm.getExperienceList(requested_user);
    }

    public List<Education> getEducationList() {
	return edm.getEducation(requested_user);
    }
    
    public List<UserSkill> getUserSkillList()
    {
	return sm.getUserSkillList(requested_user);
    }
    
    public void addSkill(User u)
    {
	UserSkill us = new UserSkill();
	us.setUserId(u);
	us.setLevel(skilllvl);
	Skill s = sm.getSkillByName(skillstr);
	if(s != null)
	{
	    us.setSkillId(s);
	}
	else
	{
	    s = new Skill();
	    s.setName(skillstr);
	    sm.addSkill(s);
	    us.setSkillId(s);
	}
	//u.getUserSkillList().add(us);
	sm.addUserSkill(us);
    }
 
}

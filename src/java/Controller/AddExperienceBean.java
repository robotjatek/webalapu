/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.ExperienceManager;
import Model.Experience;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Attila
 */
@ManagedBean
@RequestScoped
public class AddExperienceBean implements Serializable{

    @EJB
    ExperienceManager exm;

    @ManagedProperty(value ="#{mb}")
    MainController mc;

    public MainController getMc() {
	return mc;
    }

    public void setMc(MainController mc) {
	this.mc = mc;
    }
    
    String company;
    String job;
    String description;
    int start;
    int end;

    /**
     * Creates a new instance of AddExperienceBean
     */
    public AddExperienceBean() {
    }

    public String getCompany() {
	return company;
    }

    public void setCompany(String company) {
	this.company = company;
    }

    public String getJob() {
	return job;
    }

    public void setJob(String job) {
	this.job = job;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public int getStart() {
	return start;
    }

    public void setStart(int start) {
	this.start = start;
    }

    public int getEnd() {
	return end;
    }

    public void setEnd(int end) {
	this.end = end;
    }

    public String addExperience() throws Exception {
	Experience e = new Experience();
	if(mc.getLoggedin()== null)
	{
	    throw new Exception("KURVA ANYÁD GECI JSF");
	}
	e.setUserId(mc.getLoggedin());
	e.setCompany(company);
	e.setJobTitle(job);
	e.setDescription(description);
	
	Date sd = new Date();
	sd.setYear(start);
	e.setDateFrom(sd);
	
	Date ed = new Date();
	ed.setYear(end);
	e.setDateTo(ed);
	exm.addExperience(e);
	
	return "profile.xhtml";
    }

}

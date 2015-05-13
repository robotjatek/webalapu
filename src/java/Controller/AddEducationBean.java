/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.EducationManager;
import Model.Education;
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
public class AddEducationBean implements Serializable{

    @EJB
    EducationManager edm;

    @ManagedProperty(value ="#{mb}")
    MainController mc;

    public MainController getMc() {
	return mc;
    }

    public void setMc(MainController mc) {
	this.mc = mc;
    }
    
    String school;
    String field;
    String city;
    String country;
    int start;
    int end;

    /**
     * Creates a new instance of AddExperienceBean
     */
    public AddEducationBean() {
    }

    public String getSchool() {
	return school;
    }

    public void setSchool(String school) {
	this.school = school;
    }

    public String getField() {
	return field;
    }

    public void setField(String field) {
	this.field = field;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
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

    public String addEducation() throws Exception {
	Education e = new Education();
	if(mc.getLoggedin()== null)
	{
	    throw new Exception("KURVA ANYÁD GECI JSF");
	}
	e.setUserId(mc.getLoggedin());
	e.setCity(city);
	e.setSchoolName(school);
	e.setCountry(country);
	e.setFieldOfStudy(field);
	
	
	Date sd = new Date();
	sd.setYear(start);
	e.setYearFrom(sd);
	
	Date ed = new Date();
	ed.setYear(end);
	e.setYearTo(ed);
	edm.addEducation(e);
	
	return "profile.xhtml";
    }

}

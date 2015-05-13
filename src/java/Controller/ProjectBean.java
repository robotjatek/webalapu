/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.CommentManager;
import EJBs.ProjectManager;
import Model.Comment;
import Model.Project;
import Model.User;
import Model.UserProject;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Attila
 */
@ManagedBean
@ViewScoped
public class ProjectBean implements Serializable{

    String name;
    int price;
    String details;
    int needed_programmers;
    String start;
    String end;
    int status;
    User owner;
    
    String commenttext;

    @ManagedProperty(value = "#{s}")
    SessionHelper sh;

    @ManagedProperty(value = "#{wm}")
    WriteMailBean wm;

    @EJB
    CommentManager cm;

    Project requested_project;

    @ManagedProperty(value = "#{mb}")
    MainController mc;

    @EJB
    ProjectManager pm;

    public SessionHelper getSh() {
	return sh;
    }

    public void setSh(SessionHelper sh) {
	this.sh = sh;
    }

    public Project getRequested_project() {
	return sh.getRequested_project();
    }

    public void setRequested_project(Project requested_project) {
	this.sh.setRequested_project(requested_project);
	this.requested_project = requested_project;
    }

    public String getCommenttext() {
	return commenttext;
    }

    public void setCommenttext(String commenttext) {
	this.commenttext = commenttext;
    }

    public CommentManager getCm() {
	return cm;
    }

    public void setCm(CommentManager cm) {
	this.cm = cm;
    }

    public ProjectManager getPm() {
	return pm;
    }

    public void setPm(ProjectManager pm) {
	this.pm = pm;
    }

    public WriteMailBean getWm() {
	return wm;
    }

    public void setWm(WriteMailBean wm) {
	this.wm = wm;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public String getDetails() {
	return details;
    }

    public void setDetails(String details) {
	this.details = details;
    }

    public int getNeeded_programmers() {
	return needed_programmers;
    }

    public void setNeeded_programmers(int needed_programmers) {
	this.needed_programmers = needed_programmers;
    }

    public String getStart() {
	return start;
    }

    public void setStart(String start) {
	this.start = start;
    }

    public String getEnd() {
	return end;
    }

    public void setEnd(String end) {
	this.end = end;
    }

    public int getStatus() {
	return status;
    }

    public void setStatus(int status) {
	this.status = status;
    }

    public User getOwner() {
	return owner;
    }

    public void setOwner(User owner) {
	this.owner = owner;
    }

    public MainController getMc() {
	return mc;
    }

    public void setMc(MainController mc) {
	this.mc = mc;
    }

    public String addProject() throws ParseException {
	Project p = new Project();
	p.setUserId(mc.getLoggedin());
	p.setDetails(details);
	p.setName(name);
	DateFormat sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	Date sdate = sformat.parse(start);
	p.setStartDate(sdate);

	DateFormat eformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	Date edate = eformat.parse(end);
	p.setEndDate(edate);

	p.setNeededProgrammers(needed_programmers);
	p.setPrice(price);
	p.setStatus(0);

	pm.addProject(p);

	return "index";
    }

    public ProjectBean() {
    }

    @PostConstruct
    public void ReadRequested() {
	Integer id = 0;
	Map<String, String> params = FacesContext
		.getCurrentInstance()
		.getExternalContext()
		.getRequestParameterMap();
	try {
	    id = Integer.parseInt(params.get("id"));
	} catch (Exception e) {
	    //id = mc.loggedin.getId();
	}

	sh.setRequested_project(pm.getProject(id));
    }

    public void JoinToProject(User u, Project p) throws IOException {
	UserProject up = new UserProject();
	up.setUserId(u);
	up.setProjectId(p);
	up.setAccepted((short) 0);
	u.getUserProjectList().add(up);
	p.getUserProjectList().add(up);
	pm.addUserProject(up);

	wm.setRecipient(p.getUserId().getUsername());
	wm.setText("<a href=\"profile.xhtml?id=" + u.getId() + "\">"
		+ u.getUsername()
		+ " (" + u.getLastName() + " " + u.getFirstName() + ")</a> feljelentkezett a <a href=\"ad.xhtml?id=" + p.getId() + "\">" + p.getName() + "</a> hirdetésre"
	);
	wm.setTopic("Valaki feljelentkezett a " + p.getName() + " hirdetésre");
	wm.sendMail(mc.getSystem_user());
    }

    public boolean isApplied(User u, Project p) {
	for (UserProject up : u.getUserProjectList()) {
	    if (up.getProjectId().getId().equals(p.getId())) {
		return true;
	    }
	}

	return false;
    }

    public void setUserStatus(UserProject p, short status) {
	p.setAccepted(status);
	pm.refreshStatus(p);
	String statstr;
	if (status == 1) {
	    statstr = "Elfogadva";
	} else {
	    statstr = "Elutasítva";
	}

	wm.setRecipient(p.getUserId().getUsername());
	wm.setText(p.getProjectId().getName() + " hirdetésben elfoglalt státusza megváltozott. Új státusz: " + statstr);
	wm.setTopic("Projekt státusz módosult");
	wm.sendMail(mc.getSystem_user());
    }

    public void sendComment(User u, Project p) throws ParseException {
	Comment c = new Comment();
	c.setUserId(u);
	//DateFormat sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	c.setDate(new Date());
	c.setProjectId(p);
	c.setText(commenttext);
	p.getCommentList().add(c);
	cm.addComment(c);
    }

}

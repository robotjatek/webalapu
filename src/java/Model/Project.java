/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Attila
 */
@Entity
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id"),
    @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name"),
    @NamedQuery(name = "Project.findByPrice", query = "SELECT p FROM Project p WHERE p.price = :price"),
    @NamedQuery(name = "Project.findByNeededProgrammers", query = "SELECT p FROM Project p WHERE p.neededProgrammers = :neededProgrammers"),
    @NamedQuery(name = "Project.findByStartDate", query = "SELECT p FROM Project p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Project.findByEndDate", query = "SELECT p FROM Project p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Project.findByStatus", query = "SELECT p FROM Project p WHERE p.status = :status")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //  @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "details")
    private String details;
    @Basic(optional = false)
    @NotNull
    @Column(name = "needed_programmers")
    private int neededProgrammers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private List<UserProject> userProjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private List<ProjectSkills> projectSkillsList;

    public Project() {
    }

    public Project(Integer id) {
	this.id = id;
    }

    public Project(Integer id, String name, int price, String details, int neededProgrammers, Date startDate, Date endDate, int status) {
	this.id = id;
	this.name = name;
	this.price = price;
	this.details = details;
	this.neededProgrammers = neededProgrammers;
	this.startDate = startDate;
	this.endDate = endDate;
	this.status = status;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
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

    public int getNeededProgrammers() {
	return neededProgrammers;
    }

    public void setNeededProgrammers(int neededProgrammers) {
	this.neededProgrammers = neededProgrammers;
    }

    public Date getStartDate() {
	return startDate;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public Date getEndDate() {
	return endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public int getStatus() {
	return status;
    }

    public void setStatus(int status) {
	this.status = status;
    }

    public User getUserId() {
	return userId;
    }

    public void setUserId(User userId) {
	this.userId = userId;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
	return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
	this.commentList = commentList;
    }

    @XmlTransient
    public List<UserProject> getUserProjectList() {
	return userProjectList;
    }

    public void setUserProjectList(List<UserProject> userProjectList) {
	this.userProjectList = userProjectList;
    }

    @XmlTransient
    public List<ProjectSkills> getProjectSkillsList() {
	return projectSkillsList;
    }

    public void setProjectSkillsList(List<ProjectSkills> projectSkillsList) {
	this.projectSkillsList = projectSkillsList;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Project)) {
	    return false;
	}
	Project other = (Project) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Model.Project[ id=" + id + " ]";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Attila
 */
@Entity
@Table(name = "user_project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserProject.findAll", query = "SELECT u FROM UserProject u"),
    @NamedQuery(name = "UserProject.findById", query = "SELECT u FROM UserProject u WHERE u.id = :id"),
    @NamedQuery(name = "UserProject.findByAccepted", query = "SELECT u FROM UserProject u WHERE u.accepted = :accepted")})
public class UserProject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accepted")
    private short accepted;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Project projectId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public UserProject() {
    }

    public UserProject(Integer id) {
	this.id = id;
    }

    public UserProject(Integer id, short accepted) {
	this.id = id;
	this.accepted = accepted;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public short getAccepted() {
	return accepted;
    }

    public void setAccepted(short accepted) {
	this.accepted = accepted;
    }

    public Project getProjectId() {
	return projectId;
    }

    public void setProjectId(Project projectId) {
	this.projectId = projectId;
    }

    public User getUserId() {
	return userId;
    }

    public void setUserId(User userId) {
	this.userId = userId;
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
	if (!(object instanceof UserProject)) {
	    return false;
	}
	UserProject other = (UserProject) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Model.UserProject[ id=" + id + " ]";
    }

}

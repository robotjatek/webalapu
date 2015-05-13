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
@Table(name = "project_skills")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectSkills.findAll", query = "SELECT p FROM ProjectSkills p"),
    @NamedQuery(name = "ProjectSkills.findById", query = "SELECT p FROM ProjectSkills p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectSkills.findByLevel", query = "SELECT p FROM ProjectSkills p WHERE p.level = :level")})
public class ProjectSkills implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "level")
    private int level;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Project projectId;
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Skill skillId;

    public ProjectSkills() {
    }

    public ProjectSkills(Integer id) {
	this.id = id;
    }

    public ProjectSkills(Integer id, int level) {
	this.id = id;
	this.level = level;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public int getLevel() {
	return level;
    }

    public void setLevel(int level) {
	this.level = level;
    }

    public Project getProjectId() {
	return projectId;
    }

    public void setProjectId(Project projectId) {
	this.projectId = projectId;
    }

    public Skill getSkillId() {
	return skillId;
    }

    public void setSkillId(Skill skillId) {
	this.skillId = skillId;
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
	if (!(object instanceof ProjectSkills)) {
	    return false;
	}
	ProjectSkills other = (ProjectSkills) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Model.ProjectSkills[ id=" + id + " ]";
    }
    
}

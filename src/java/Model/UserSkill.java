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
@Table(name = "user_skill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSkill.findAll", query = "SELECT u FROM UserSkill u"),
    @NamedQuery(name = "UserSkill.findById", query = "SELECT u FROM UserSkill u WHERE u.id = :id"),
    @NamedQuery(name = "UserSkill.findByLevel", query = "SELECT u FROM UserSkill u WHERE u.level = :level")})
public class UserSkill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    // @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "level")
    private int level;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Skill skillId;

    public UserSkill() {
    }

    public UserSkill(Integer id) {
	this.id = id;
    }

    public UserSkill(Integer id, int level) {
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

    public User getUserId() {
	return userId;
    }

    public void setUserId(User userId) {
	this.userId = userId;
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
	if (!(object instanceof UserSkill)) {
	    return false;
	}
	UserSkill other = (UserSkill) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Model.UserSkill[ id=" + id + " ]";
    }

}

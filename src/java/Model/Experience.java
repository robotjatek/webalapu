/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Attila
 */
@Entity
@Table(name = "experience")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experience.findAll", query = "SELECT e FROM Experience e"),
    @NamedQuery(name = "Experience.findById", query = "SELECT e FROM Experience e WHERE e.id = :id"),
    @NamedQuery(name = "Experience.findByDateFrom", query = "SELECT e FROM Experience e WHERE e.dateFrom = :dateFrom"),
    @NamedQuery(name = "Experience.findByDateTo", query = "SELECT e FROM Experience e WHERE e.dateTo = :dateTo"),
    @NamedQuery(name = "Experience.findByCompany", query = "SELECT e FROM Experience e WHERE e.company = :company"),
    @NamedQuery(name = "Experience.findByJobTitle", query = "SELECT e FROM Experience e WHERE e.jobTitle = :jobTitle"),
    @NamedQuery(name = "Experience.findByUserId", query = "SELECT e FROM Experience e WHERE e.userId = :userid")

})
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_to")
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "company")
    private String company;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "job_title")
    private String jobTitle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Experience() {
    }

    public Experience(Integer id) {
	this.id = id;
    }

    public Experience(Integer id, Date dateFrom, Date dateTo, String company, String jobTitle, String description) {
	this.id = id;
	this.dateFrom = dateFrom;
	this.dateTo = dateTo;
	this.company = company;
	this.jobTitle = jobTitle;
	this.description = description;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public int getDateFrom() {
	return dateFrom.getYear();
    }

    public void setDateFrom(Date dateFrom) {
	this.dateFrom = dateFrom;
    }

    public int getDateTo() {
	return dateTo.getYear();
    }

    public void setDateTo(Date dateTo) {
	this.dateTo = dateTo;
    }

    public String getCompany() {
	return company;
    }

    public void setCompany(String company) {
	this.company = company;
    }

    public String getJobTitle() {
	return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
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
	if (!(object instanceof Experience)) {
	    return false;
	}
	Experience other = (Experience) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Model.Experience[ id=" + id + " ]";
    }

}

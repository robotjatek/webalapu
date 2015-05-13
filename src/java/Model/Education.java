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
@Table(name = "education")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Education.findAll", query = "SELECT e FROM Education e"),
    @NamedQuery(name = "Education.findById", query = "SELECT e FROM Education e WHERE e.id = :id"),
    @NamedQuery(name = "Education.findByYearFrom", query = "SELECT e FROM Education e WHERE e.yearFrom = :yearFrom"),
    @NamedQuery(name = "Education.findByYearTo", query = "SELECT e FROM Education e WHERE e.yearTo = :yearTo"),
    @NamedQuery(name = "Education.findByCountry", query = "SELECT e FROM Education e WHERE e.country = :country"),
    @NamedQuery(name = "Education.findByCity", query = "SELECT e FROM Education e WHERE e.city = :city"),
    @NamedQuery(name = "Education.findBySchoolName", query = "SELECT e FROM Education e WHERE e.schoolName = :schoolName"),
    @NamedQuery(name = "Education.findByFieldOfStudy", query = "SELECT e FROM Education e WHERE e.fieldOfStudy = :fieldOfStudy"),
    @NamedQuery(name = "Education.findByUser", query = "SELECT e FROM Education e WHERE e.userId = :user"),
})
public class Education implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year_from")
    @Temporal(TemporalType.DATE)
    private Date yearFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year_to")
    @Temporal(TemporalType.DATE)
    private Date yearTo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "school_name")
    private String schoolName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "field_of_study")
    private String fieldOfStudy;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Education() {
    }

    public Education(Integer id) {
	this.id = id;
    }

    public Education(Integer id, Date yearFrom, Date yearTo, String country, String city, String schoolName, String fieldOfStudy) {
	this.id = id;
	this.yearFrom = yearFrom;
	this.yearTo = yearTo;
	this.country = country;
	this.city = city;
	this.schoolName = schoolName;
	this.fieldOfStudy = fieldOfStudy;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public int getYearFrom() {
	return yearFrom.getYear();
    }

    public void setYearFrom(Date yearFrom) {
	this.yearFrom = yearFrom;
    }

    public int getYearTo() {
	return yearTo.getYear();
    }

    public void setYearTo(Date yearTo) {
	this.yearTo = yearTo;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getSchoolName() {
	return schoolName;
    }

    public void setSchoolName(String schoolName) {
	this.schoolName = schoolName;
    }

    public String getFieldOfStudy() {
	return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
	this.fieldOfStudy = fieldOfStudy;
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
	if (!(object instanceof Education)) {
	    return false;
	}
	Education other = (Education) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Model.Education[ id=" + id + " ]";
    }
    
}

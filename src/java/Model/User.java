/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Attila
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByCountry", query = "SELECT u FROM User u WHERE u.country = :country"),
    @NamedQuery(name = "User.findByCity", query = "SELECT u FROM User u WHERE u.city = :city"),
    @NamedQuery(name = "User.findByStreet", query = "SELECT u FROM User u WHERE u.street = :street"),
    @NamedQuery(name = "User.findByPostalCode", query = "SELECT u FROM User u WHERE u.postalCode = :postalCode"),
    //ez itt saját
    @NamedQuery(name = "User.GetByNameAndPass", query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "last_name")
    private String lastName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
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
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "postal_code")
    private String postalCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Education> educationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<UserSkill> userSkillList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Project> projectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<UserProject> userProjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Experience> experienceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderId")
    private List<Message> messageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toId")
    private List<Message> messageList1;

    public User() {
    }

    public User(Integer id) {
	this.id = id;
    }

    public User(Integer id, String username, String password, String firstName, String lastName, String phone, String email, String country, String city, String street, String postalCode) {
	this.id = id;
	this.username = username;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.phone = phone;
	this.email = email;
	this.country = country;
	this.city = city;
	this.street = street;
	this.postalCode = postalCode;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
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

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getPostalCode() {
	return postalCode;
    }

    public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
    }

    @XmlTransient
    public List<Education> getEducationList() {
	return educationList;
    }

    public void setEducationList(List<Education> educationList) {
	this.educationList = educationList;
    }

    @XmlTransient
    public List<UserSkill> getUserSkillList() {
	return userSkillList;
    }

    public void setUserSkillList(List<UserSkill> userSkillList) {
	this.userSkillList = userSkillList;
    }

    @XmlTransient
    public List<Project> getProjectList() {
	return projectList;
    }

    public void setProjectList(List<Project> projectList) {
	this.projectList = projectList;
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
    public List<Experience> getExperienceList() {
	return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
	this.experienceList = experienceList;
    }

    @XmlTransient
    public List<Message> getMessageList() {
	return messageList;
    }

    public void setMessageList(List<Message> messageList) {
	this.messageList = messageList;
    }

    @XmlTransient
    public List<Message> getMessageList1() {
	return messageList1;
    }

    public void setMessageList1(List<Message> messageList1) {
	this.messageList1 = messageList1;
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
	if (!(object instanceof User)) {
	    return false;
	}
	User other = (User) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Model.User[ id=" + id + " ]";
    }
    
}

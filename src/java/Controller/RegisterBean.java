/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.UserManager;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import Model.User;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.transaction.NotSupportedException;

/**
 *
 * @author Attila
 */
@ManagedBean
@RequestScoped
public class RegisterBean implements Serializable{

    String username;
    String pass;
    String pass_second;
    String first_name;
    String last_name;
    String phone;
    String email;
    String country;
    String city;
    String street;
    String postal_code;

    boolean user_exists;
    boolean pass_error;

    @EJB
    UserManager um;

    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPass() {
	return pass;
    }

    public void setPass(String pass) {
	this.pass = pass;
    }

    public String getPass_second() {
	return pass_second;
    }

    public void setPass_second(String pass_second) {
	this.pass_second = pass_second;
    }

    public String getFirst_name() {
	return first_name;
    }

    public void setFirst_name(String first_name) {
	this.first_name = first_name;
    }

    public String getLast_name() {
	return last_name;
    }

    public void setLast_name(String last_name) {
	this.last_name = last_name;
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

    public String getPostal_code() {
	return postal_code;
    }

    public void setPostal_code(String postal_code) {
	this.postal_code = postal_code;
    }

    public void register_user() throws NotSupportedException, IOException {

	if (pass.equals(pass_second)) {
	    User u = new User();
	    u.setUsername(username);
	    u.setPassword(pass);
	    u.setFirstName(first_name);
	    u.setLastName(username);
	    u.setPhone(phone);
	    u.setEmail(email);
	    u.setCountry(country);
	    u.setCity(city);
	    u.setStreet(street);
	    u.setPostalCode(postal_code);
	    pass_error = false;
	    user_exists = !um.addUser(u);
	    if (pass_error == false && user_exists == false) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect("index.xhtml");
	    }
	} else {
	    pass_error = true;
	}
    }

    public boolean isUser_exists() {
	return user_exists;
    }

    public void setUser_exists(boolean user_exists) {
	this.user_exists = user_exists;
    }

    public boolean isPass_error() {
	return pass_error;
    }

    public void setPass_error(boolean pass_error) {
	this.pass_error = pass_error;
    }

}

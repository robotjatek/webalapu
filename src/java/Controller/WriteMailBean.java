/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.MessageManager;
import EJBs.UserManager;
import Model.Message;
import Model.User;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Attila
 */
@ManagedBean(name="wm")
@ViewScoped
public class WriteMailBean implements Serializable {

    @EJB
    MessageManager mm;

    public MessageManager getMm() {
	return mm;
    }

    public void setMm(MessageManager mm) {
	this.mm = mm;
    }

    public UserManager getUm() {
	return um;
    }

    public void setUm(UserManager um) {
	this.um = um;
    }

    @EJB
    UserManager um;

    String recipient;
    String topic;
    String text;

    boolean user_not_found = false;
    
    @PostConstruct
    public void init()
    {
	user_not_found = false;
    }

    public boolean isUser_not_found() {
	return user_not_found;
    }

    public void setUser_not_found(boolean user_not_found) {
	this.user_not_found = user_not_found;
    }

    public String getRecipient() {
	return recipient;
    }

    public void setRecipient(String recipient) {
	this.recipient = recipient;
    }

    public String getTopic() {
	return topic;
    }

    public void setTopic(String topic) {
	this.topic = topic;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String sendMail(User u) {
	User ru = um.getUser(this.recipient);
	if (ru != null) {
	    Message m = new Message();
	    m.setText(text);
	    m.setTopic(topic);
	    m.setSenderId(u);
	    m.setToId(ru);
	    m.setIsDraft(false);
	    m.setDate(new Date());
	    mm.addMessage(m);
	    user_not_found = false;
	    return "mail";
	}
	else
	{
	    user_not_found = true;
	    return "sendmail";
	}
    }

    /**
     * Creates a new instance of WriteMailBean
     */
    public WriteMailBean() {
	user_not_found = false;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.MessageManager;
import Model.Message;
import Model.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Attila
 */
@ManagedBean
@SessionScoped
public class MailBean implements Serializable{
    
    @EJB
    MessageManager mm;
    
    Message message;

    public Message getMessage() {
	return message;
    }

    public void setMessage(Message message) {
	this.message = message;
    }

    /**
     * Creates a new instance of MailBean
     */
    public MailBean() {
    }
    
    public List<Message> getMessageListBySender(User u)
    {
	return mm.getMessagesBySender(u);
    }
    
    public List<Message> getMessageListByRecipient(User u)
    {
	return mm.getMessagesByRecipient(u);
    }
    
    public String readMessage(Message m)
    {
	message = m;
	return "readmail";
    }
}

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
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
    @NamedQuery(name = "Message.findByDate", query = "SELECT m FROM Message m WHERE m.date = :date"),
    @NamedQuery(name = "Message.findByTopic", query = "SELECT m FROM Message m WHERE m.topic = :topic"),
    @NamedQuery(name = "Message.findByIsDraft", query = "SELECT m FROM Message m WHERE m.isDraft = :isDraft"),
    @NamedQuery(name = "Message.findByRecipient", query = "SELECT m FROM Message m WHERE m.toId = :to"),
    @NamedQuery(name = "Message.findBySender", query = "SELECT m FROM Message m WHERE m.senderId = :sender")
})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
  //  @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "topic")
    private String topic;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_draft")
    private boolean isDraft;
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User senderId;
    @JoinColumn(name = "to_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User toId;

    public Message() {
    }

    public Message(Integer id) {
	this.id = id;
    }

    public Message(Integer id, Date date, String topic, String text, boolean isDraft) {
	this.id = id;
	this.date = date;
	this.topic = topic;
	this.text = text;
	this.isDraft = isDraft;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
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

    public boolean getIsDraft() {
	return isDraft;
    }

    public void setIsDraft(boolean isDraft) {
	this.isDraft = isDraft;
    }

    public User getSenderId() {
	return senderId;
    }

    public void setSenderId(User senderId) {
	this.senderId = senderId;
    }

    public User getToId() {
	return toId;
    }

    public void setToId(User toId) {
	this.toId = toId;
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
	if (!(object instanceof Message)) {
	    return false;
	}
	Message other = (Message) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Model.Message[ id=" + id + " ]";
    }

}

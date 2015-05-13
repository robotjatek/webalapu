/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Model.Message;
import Model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Attila
 */
@Stateless
public class MessageManager {

   @PersistenceContext
   EntityManager em;
   
   public List<Message> getMessagesBySender(User u)
   {
       return em.createNamedQuery("Message.findBySender",Message.class).setParameter("sender", u).getResultList();
   }
   
   public List<Message> getMessagesByRecipient(User u)
   {
       return em.createNamedQuery("Message.findByRecipient",Message.class).setParameter("to", u).getResultList();
   }
   
   public void addMessage(Message m)
   {
       em.persist(m);
   }
   
   public Message getMessageById(long id)
   {
       return em.find(Message.class, id);
   }
}

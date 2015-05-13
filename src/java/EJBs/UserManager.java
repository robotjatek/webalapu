/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Model.User;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Attila
 */
@Stateful
public class UserManager {

    @PersistenceContext
    EntityManager em;

    public boolean addUser(User u) {
	List l = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", u.getUsername()).getResultList();

	if (l.isEmpty()) {
	    em.persist(u);
	    return true;
	}
	return false;
    }

    public User getUser(String username, String pass) {
	List<User> l
		= em.createNamedQuery("User.GetByNameAndPass", User.class)
		.setParameter("username", username)
		.setParameter("password", pass)
		.getResultList();

	if (l.isEmpty()) {
	    return null;
	} else {
	    return l.get(0);
	}
    }
    
    public User getUser(int id)
    {
	List<User> userlist
		= em.createNamedQuery("User.findById", User.class)
		.setParameter("id", id)
		.getResultList();
	if (userlist.size() > 0) {
	    return userlist.get(0);
	}
	
	return null;
    }
    
    public User getUser(String username)
    {
	List<User> userlist = em.createNamedQuery("User.findByUsername",User.class).setParameter("username", username).getResultList();

	if(userlist.size() > 0)
	{
	    return userlist.get(0);
	}
	
	return null;
    }
}

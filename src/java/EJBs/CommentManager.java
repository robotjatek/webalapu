/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Model.Comment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Attila
 */
@Stateless
public class CommentManager {
    @PersistenceContext
    EntityManager em;
    
    public void addComment(Comment c)
    {
	em.persist(c);
	em.flush();
    }
}

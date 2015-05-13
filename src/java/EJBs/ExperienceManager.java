/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Model.Experience;
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
public class ExperienceManager {

    @PersistenceContext
    EntityManager em;
    
    public void addExperience(Experience e)
    {
	em.persist(e);
	em.flush();//
    }
    
    public void deleteExperience(Experience e)
    {
	Experience ex = (Experience)em.find(Experience.class, e.getId());
	em.remove(ex);
	em.flush();
    }
    
    public List<Experience> getExperienceList(User u)
    {
	return em.createNamedQuery("Experience.findByUserId",Experience.class).setParameter("userid", u).getResultList();
    }
}

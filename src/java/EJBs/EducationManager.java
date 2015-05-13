/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Model.Education;
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
public class EducationManager {

    @PersistenceContext
    EntityManager em;
    
     public void addEducation(Education e)
    {
	em.persist(e);
	em.flush();//
    }
    
    public void deleteEducation(Education e)
    {
	Education ed = (Education)em.find(Education.class, e.getId());
	em.remove(ed);
	em.flush();
    }
    
    public List<Education> getEducation(User u)
    {
	return em.createNamedQuery("Education.findByUser",Education.class).setParameter("user", u).getResultList();
    }
}

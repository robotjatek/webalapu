/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Model.Skill;
import Model.User;
import Model.UserSkill;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Attila
 */
@Stateless
public class SkillManager {

    @PersistenceContext
    EntityManager em;

    public void delete_user_skill(UserSkill us) {
	UserSkill d = em.find(UserSkill.class, us.getId());
	em.remove(d);
	em.flush();
    }
    
    public Skill getSkillByName(String name)
    {
	List<Skill> l = em.createNamedQuery("Skill.findByName",Skill.class).setParameter("name", name).getResultList();
	if(l.size()>0)
	{
	    return l.get(0);
	}
	
	return null;
    }
    
    public void addSkill(Skill s)
    {
	em.persist(s);
	em.flush();
    }
    
    public void addUserSkill(UserSkill us)
    {
	em.persist(us);
	em.flush();
    }
    
    public List<UserSkill> getUserSkillList(User u)
    {
	return em.createNamedQuery("UserSkill.findByUser", UserSkill.class).setParameter("user_id", u).getResultList();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import Model.Project;
import Model.UserProject;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Attila
 */
@Stateless
public class ProjectManager {
    
    @PersistenceContext
    EntityManager em;

    public void addProject(Project p)
    {
	em.persist(p);
	em.flush();
    }
    
    public Project getProject(int id)
    {
	return em.find(Project.class, id);
    }
    
    public void addUserProject(UserProject up)
    {
	em.persist(up);
	em.flush();
    }
    
    public void refreshStatus(UserProject up)
    {
	em.merge(up);
	em.flush();
    }
    
    public List<Project> getAllProjects()
    {
	return em.createNamedQuery("Project.findAll", Project.class).getResultList();
    }
}

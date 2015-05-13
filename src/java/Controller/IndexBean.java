/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJBs.ProjectManager;
import Model.Project;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Attila
 */
@ManagedBean
@RequestScoped
public class IndexBean implements Serializable{

    @EJB
    ProjectManager pm;

    public IndexBean() {
    }
    
    public List<Project> readAdList()
    {
	return pm.getAllProjects();
    }

}

package Controller;

import Model.Project;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Attila
 */
@ManagedBean(name = "s")
@SessionScoped
public class SessionHelper implements Serializable {

     Project requested_project;

    public Project getRequested_project() {
	return requested_project;
    }

    public void setRequested_project(Project requested_project) {
	this.requested_project = requested_project;
    }
}

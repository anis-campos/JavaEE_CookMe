package cookMe.view;


import cookMe.model.user.UserModelBean;
import cookMe.model.user.UserSubmissionModelBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by anis.dasilvacampos on 31/05/2016.
 */
@ManagedBean
@ViewScoped
public class ViewTools {


    boolean renderPanel;
    private Integer progression;

    public ViewTools() {
    }

    public boolean isRenderuserCreatePanel() {
        return renderPanel;
    }

    public void setAddUserMode() {
        renderPanel = true;
    }

    public int getProgression() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        if (sessionMap.containsKey("Splashed")) {
            progression = 100;
        } else if (progression == null) {
            progression = 0;
        } else {
            progression = progression + (int) (Math.random() * 45);

            if (progression > 100) {
                sessionMap.put("Splashed", true);
                progression = 100;

            }
        }


        return progression;
    }

    public void setUpdateUserMode(UserModelBean user) {

        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
        viewMap.put("userSubmissionModelBean", new UserSubmissionModelBean(user));
        renderPanel = false;
    }

}

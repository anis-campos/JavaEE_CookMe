package cookMe.view;


import cookMe.model.recipe.RecipeModelBean;
import cookMe.model.recipe.RecipeSubmissionModelBean;
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


    private boolean creationMode;
    private Integer progression;

    public ViewTools() {
    }

    public boolean isCreationModeEnabled() {
        return creationMode;
    }

    public void enableCreationMode() {
        creationMode = true;
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        viewMap.remove("userSubmissionModelBean");
        viewMap.remove("recipeSubmissionModelBean");
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

        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        viewMap.put("userSubmissionModelBean", new UserSubmissionModelBean(user));
        creationMode = false;
    }

    public void setUpdateReceipeMode(RecipeModelBean recipe) {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        viewMap.put("recipeSubmissionModelBean", new RecipeSubmissionModelBean(recipe));
        creationMode = false;
    }
}

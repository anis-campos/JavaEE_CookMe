package cookMe.model.recipe;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class RecipeSubmissionModelBean extends RecipeModelBean {

    public RecipeSubmissionModelBean() {
    }

    public RecipeSubmissionModelBean(RecipeModelBean recipe) {
        super(recipe.getTitle(), recipe.getDescription(), recipe.getExpertise(), recipe.getNbpeople(), recipe.getDuration(), recipe.getType(), recipe.getImage(), recipe.getId());
    }
}

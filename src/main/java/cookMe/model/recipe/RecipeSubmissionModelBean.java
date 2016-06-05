package cookMe.model.recipe;

import cookMe.model.ISubmissionModel;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class RecipeSubmissionModelBean extends RecipeModelBean implements ISubmissionModel {

    private UploadedFile file;

    public RecipeSubmissionModelBean() {
    }

    public RecipeSubmissionModelBean(RecipeModelBean recipe) {
        super(recipe.getTitle(), recipe.getDescription(), recipe.getExpertise(), recipe.getNbPeople(), recipe.getDuration(), recipe.getType().name(), recipe.getImage(), recipe.getId());
    }


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }



    @Override
    public boolean isValid() {

        return true;/*
        boolean valid;
        valid = getTitle() != null && getTitle().length() < 50 && Pattern.compile("[A-Za-z]+").matcher(this.getTitle()).matches();
        valid &= getDescription() != null && getDescription().length() < 4000 && Pattern.compile("^(?!\\s*$).+").matcher(this.getDescription()).matches();
        valid &= getType() != null;
        return valid;*/
    }
}

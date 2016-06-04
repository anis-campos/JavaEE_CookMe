package cookMe.model.recipe;

import cookMe.model.ISubmissionModel;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Base64;

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
        super(recipe.getTitle(), recipe.getDescription(), recipe.getExpertise(), recipe.getNbpeople(), recipe.getDuration(), recipe.getType(), recipe.getImage(), recipe.getId());
    }

    public void fileUploadListener(FileUploadEvent e) {
        file = e.getFile();
        String encodedImage = Base64.getEncoder().encodeToString(file.getContents());
        encodedImage = "data:image/jpeg;base64," + encodedImage;
        setImage(encodedImage);
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }


    @Override
    public boolean isValid() {
        return true;
    }
}

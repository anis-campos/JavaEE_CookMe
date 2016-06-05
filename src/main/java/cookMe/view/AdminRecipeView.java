package cookMe.view;

import cookMe.model.recipe.RecipeModelBean;
import cookMe.model.recipe.RecipeSubmissionModelBean;
import cookMe.processing.RecipeControllerBean;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Base64;
import java.util.Map;

/**
 * Created by Anis on 05/06/2016.
 */
@ManagedBean
@SessionScoped
public class AdminRecipeView extends AbstractAdminView<RecipeModelBean, RecipeSubmissionModelBean> {


    @ManagedProperty(value = "#{recipeControllerBean}")
    private RecipeControllerBean controler;

    public AdminRecipeView() {
        super("recipeSubmissionModelBean");
    }

    @Override
    public String getTitle() {
        return isCreationMode() ? "Create Recipe" : "Update Recipe";
    }

    @Override
    public String getButtonFormSubmit() {
        return isCreationMode() ? "Submit New Recipe" : "Update Recipe";

    }

    public void setControler(RecipeControllerBean controler) {
        this.controler = controler;
    }

    @Override
    protected void putIntoSession(RecipeModelBean recipeModelBean) {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        viewMap.put(beanName, new RecipeSubmissionModelBean(recipeModelBean));
    }

    @Override
    public void update() {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        RecipeSubmissionModelBean recipeSubmissionModelBean = (RecipeSubmissionModelBean) sessionMap.get(beanName);
        controler.update(recipeSubmissionModelBean);
        sessionMap.remove(beanName);
    }

    @Override
    public void create(RecipeSubmissionModelBean recipeSubmissionModelBean) {
        controler.addRecipe(recipeSubmissionModelBean);
    }


    public void handleFileUpload(FileUploadEvent e) {
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        RecipeSubmissionModelBean recipeSubmissionModelBean = (RecipeSubmissionModelBean) sessionMap.get(beanName);
        UploadedFile file = e.getFile();
        String encodedImage = Base64.getEncoder().encodeToString(file.getContents());
        encodedImage = "data:image/jpeg;base64," + encodedImage;
        recipeSubmissionModelBean.setImage(encodedImage);
    }

}

package cookMe.view;

import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by Anis on 05/06/2016.
 */
public abstract class AbstractAdminView<Model, Submission extends Model> {

    protected String beanName;
    private boolean creationMode;

    public AbstractAdminView() {
    }

    public AbstractAdminView(String beanName) {
        this.beanName = beanName;
    }

    public boolean isCreationMode() {
        return creationMode;
    }


    public String setCreationMode() {
        this.creationMode = true;
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (viewMap.containsKey(beanName))
            viewMap.remove(beanName);
        return "?faces-redirect=true";
    }

    public void updateMode(Model model) {
        putIntoSession(model);
        creationMode = false;
    }


    public String formSubmit(Submission submission) {
        if (isCreationMode()) {
            create(submission);
        } else {
            update();
        }

        return "";
    }

    public abstract String getTitle();

    public abstract String getButtonFormSubmit();

    protected abstract void putIntoSession(Model model);

    public abstract void update();

    public abstract void create(Submission submission);

}

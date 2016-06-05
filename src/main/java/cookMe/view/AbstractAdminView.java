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


    public void setCreationMode() {
        this.creationMode = true;
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        viewMap.remove(beanName);
    }

    public void updateMode(Model model) {
        putIntoSession(model);
        creationMode = false;
    }


    public void formSubmit(Submission submission) {
        if (isCreationMode()) {
            create(submission);
        } else {
            update();
        }
    }

    public abstract String getTitle();

    protected abstract void putIntoSession(Model model);

    public abstract void update();

    public abstract void create(Submission submission);

}
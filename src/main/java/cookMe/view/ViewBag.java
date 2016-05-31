package cookMe.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by anis.dasilvacampos on 31/05/2016.
 */
@ManagedBean
@ViewScoped
public class ViewBag {


    private String title;

    public ViewBag() {
        title = "Title";
    }

    public ViewBag(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

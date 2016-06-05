package cookMe.view;


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
public class SplashScreenView {


    private Integer progression;

    public SplashScreenView() {

    }


    public int splashProgression() {
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


}

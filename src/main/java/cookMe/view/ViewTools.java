package cookMe.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by anis.dasilvacampos on 31/05/2016.
 */
@ManagedBean
@ViewScoped
public class ViewTools {


    private Integer progression;

    public ViewTools() {
    }


    public int getProgression() {
        if(progression == null) {
            progression = 0;
        }
        else {
            progression = progression + (int)(Math.random() * 45);

            if(progression > 100)
                progression = 100;
        }

        return progression;
    }
}
